/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.resolve.calls.inference.components


import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystemOperation
import org.jetbrains.kotlin.resolve.calls.inference.model.*
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintKind.*
import org.jetbrains.kotlin.resolve.calls.model.KotlinCallDiagnostic
import org.jetbrains.kotlin.types.*
import org.jetbrains.kotlin.types.checker.KotlinTypeRefiner
import org.jetbrains.kotlin.types.model.*
import org.jetbrains.kotlin.types.refinement.TypeRefinement
import org.jetbrains.kotlin.utils.SmartList
import kotlin.math.max

class ConstraintInjector(
    val constraintIncorporator: ConstraintIncorporator,
    val typeApproximator: AbstractTypeApproximator,
    val kotlinTypeRefiner: KotlinTypeRefiner
) {
    private val ALLOWED_DEPTH_DELTA_FOR_INCORPORATION = 1

    interface Context : TypeSystemInferenceExtensionContext {
        val allTypeVariables: Map<TypeConstructorMarker, TypeVariableMarker>

        var maxTypeDepthFromInitialConstraints: Int
        val notFixedTypeVariables: MutableMap<TypeConstructorMarker, MutableVariableWithConstraints>
        val fixedTypeVariables: MutableMap<TypeConstructorMarker, KotlinTypeMarker>

        fun addInitialConstraint(initialConstraint: InitialConstraint)
        fun addError(error: KotlinCallDiagnostic)
    }

    fun addInitialSubtypeConstraint(c: Context, lowerType: KotlinTypeMarker, upperType: KotlinTypeMarker, position: ConstraintPosition) {
        val initialConstraint = InitialConstraint(lowerType, upperType, UPPER, position).also { c.addInitialConstraint(it) }

        updateAllowedTypeDepth(c, lowerType)
        updateAllowedTypeDepth(c, upperType)

        addSubTypeConstraintAndIncorporateIt(
            c,
            lowerType,
            upperType,
            TypeCheckerContext(c, IncorporationConstraintPosition(position, initialConstraint))
        )
    }

    fun addInitialEqualityConstraint(c: Context, a: KotlinTypeMarker, b: KotlinTypeMarker, position: ConstraintPosition) {
        val initialConstraint = InitialConstraint(a, b, EQUALITY, position).also { c.addInitialConstraint(it) }

        updateAllowedTypeDepth(c, a)
        updateAllowedTypeDepth(c, b)

        val typeCheckerContext = TypeCheckerContext(c, IncorporationConstraintPosition(position, initialConstraint))

        addSubTypeConstraintAndIncorporateIt(c, a, b, typeCheckerContext)
        addSubTypeConstraintAndIncorporateIt(c, b, a, typeCheckerContext)
    }

    private fun addSubTypeConstraintAndIncorporateIt(
        c: Context,
        lowerType: KotlinTypeMarker,
        upperType: KotlinTypeMarker,
        typeCheckerContext: TypeCheckerContext
    ) {
        typeCheckerContext.setConstrainingTypesToPrintDebugInfo(lowerType, upperType)
        typeCheckerContext.runIsSubtypeOf(lowerType, upperType)

        while (typeCheckerContext.hasConstraintsToProcess()) {
            for ((typeVariable, constraint) in typeCheckerContext.extractAllConstraints()!!) {
                if (c.shouldWeSkipConstraint(typeVariable, constraint)) continue

                val constraints =
                    c.notFixedTypeVariables[typeVariable.freshTypeConstructor(c)] ?: typeCheckerContext.fixedTypeVariable(typeVariable)

                // it is important, that we add constraint here(not inside TypeCheckerContext), because inside incorporation we read constraints
                constraints.addConstraint(constraint)?.let {
                    if (!constraint.isNullabilityConstraint) {
                        constraintIncorporator.incorporate(typeCheckerContext, typeVariable, it)
                    }
                }
            }

            val contextOps = c as? ConstraintSystemOperation
            if (!typeCheckerContext.hasConstraintsToProcess() ||
                (contextOps != null && c.notFixedTypeVariables.all { typeVariable ->
                    typeVariable.value.constraints.any { constraint ->
                        constraint.kind == EQUALITY && contextOps.isProperType(constraint.type)
                    }
                })
            ) {
                break
            }
        }
    }

    private fun updateAllowedTypeDepth(c: Context, initialType: KotlinTypeMarker) = with(c) {
        c.maxTypeDepthFromInitialConstraints = max(c.maxTypeDepthFromInitialConstraints, initialType.typeDepth())
    }

    private fun Context.shouldWeSkipConstraint(typeVariable: TypeVariableMarker, constraint: Constraint): Boolean {
        assert(constraint.kind != EQUALITY)

        val constraintType = constraint.type

        if (constraintType.typeConstructor() == typeVariable.freshTypeConstructor()) {
            if (constraintType.lowerBoundIfFlexible().isMarkedNullable() && constraint.kind == LOWER) return false // T? <: T

            return true // T <: T(?!)
        }

        if (constraint.position.from is DeclaredUpperBoundConstraintPosition &&
            constraint.kind == UPPER && constraintType.isNullableAny()
        ) {
            return true // T <: Any?
        }

        return false
    }

    private fun Context.isAllowedType(type: KotlinTypeMarker) =
        type.typeDepth() <= maxTypeDepthFromInitialConstraints + ALLOWED_DEPTH_DELTA_FOR_INCORPORATION

    private inner class TypeCheckerContext(val c: Context, val position: IncorporationConstraintPosition) :
        AbstractTypeCheckerContextForConstraintSystem(), ConstraintIncorporator.Context, TypeSystemInferenceExtensionContext by c {
        // We use `var` intentionally to avoid extra allocations as this property is quite "hot"
        private var possibleNewConstraints: MutableList<Pair<TypeVariableMarker, Constraint>>? = null

        private var baseLowerType = position.initialConstraint.a
        private var baseUpperType = position.initialConstraint.b

        private var isIncorporatingConstraintFromDeclaredUpperBound = false

        fun extractAllConstraints() = possibleNewConstraints.also { possibleNewConstraints = null }

        fun addPossibleNewConstraint(variable: TypeVariableMarker, constraint: Constraint) {
            if (possibleNewConstraints == null) {
                possibleNewConstraints = SmartList()
            }
            possibleNewConstraints!!.add(variable to constraint)
        }

        fun hasConstraintsToProcess() = possibleNewConstraints != null

        fun setConstrainingTypesToPrintDebugInfo(lowerType: KotlinTypeMarker, upperType: KotlinTypeMarker) {
            baseLowerType = lowerType
            baseUpperType = upperType
        }

        val baseContext: AbstractTypeCheckerContext = newBaseTypeCheckerContext(isErrorTypeEqualsToAnything, isStubTypeEqualsToAnything)

        override fun substitutionSupertypePolicy(type: SimpleTypeMarker): SupertypesPolicy {
            return baseContext.substitutionSupertypePolicy(type)
        }

        override fun areEqualTypeConstructors(a: TypeConstructorMarker, b: TypeConstructorMarker): Boolean {
            return baseContext.areEqualTypeConstructors(a, b)
        }

        override fun prepareType(type: KotlinTypeMarker): KotlinTypeMarker {
            return baseContext.prepareType(type)
        }

        @OptIn(TypeRefinement::class)
        override fun refineType(type: KotlinTypeMarker): KotlinTypeMarker {
            return if (type is KotlinType) {
                kotlinTypeRefiner.refineType(type)
            } else {
                type
            }
        }

        fun runIsSubtypeOf(
            lowerType: KotlinTypeMarker,
            upperType: KotlinTypeMarker,
            shouldTryUseDifferentFlexibilityForUpperType: Boolean = false,
            isFromNullabilityConstraint: Boolean = false
        ) {
            fun isSubtypeOf(upperType: KotlinTypeMarker) =
                AbstractTypeChecker.isSubtypeOf(
                    this@TypeCheckerContext as AbstractTypeCheckerContext,
                    lowerType,
                    upperType,
                    isFromNullabilityConstraint
                )

            if (!isSubtypeOf(upperType)) {
                // todo improve error reporting -- add information about base types
                if (shouldTryUseDifferentFlexibilityForUpperType && upperType is SimpleType) {
                    /*
                     * Please don't reuse this logic.
                     * It's necessary to solve constraint systems when flexibility isn't propagated through a type variable.
                     * It's OK in the old inference because it uses already substituted types, that are with the correct flexibility.
                     */
                    val flexibleUpperType = createFlexibleType(upperType, upperType.withNullability(true))
                    if (!isSubtypeOf(flexibleUpperType)) {
                        c.addError(NewConstraintError(lowerType, flexibleUpperType, position))
                    }
                } else {
                    c.addError(NewConstraintError(lowerType, upperType, position))
                }
            }
        }

        // from AbstractTypeCheckerContextForConstraintSystem
        override fun isMyTypeVariable(type: SimpleTypeMarker): Boolean =
            c.allTypeVariables.containsKey(type.typeConstructor())

        override fun addUpperConstraint(typeVariable: TypeConstructorMarker, superType: KotlinTypeMarker) =
            addConstraint(typeVariable, superType, UPPER)

        override fun addLowerConstraint(
            typeVariable: TypeConstructorMarker,
            subType: KotlinTypeMarker,
            isFromNullabilityConstraint: Boolean
        ) = addConstraint(typeVariable, subType, LOWER, isFromNullabilityConstraint)

        private fun isCapturedTypeFromSubtyping(type: KotlinTypeMarker) =
            when ((type as? CapturedTypeMarker)?.captureStatus()) {
                null, CaptureStatus.FROM_EXPRESSION -> false
                CaptureStatus.FOR_SUBTYPING -> true
                CaptureStatus.FOR_INCORPORATION ->
                    error("Captured type for incorporation shouldn't escape from incorporation: $type\n" + renderBaseConstraint())
            }

        private fun addConstraint(
            typeVariableConstructor: TypeConstructorMarker,
            type: KotlinTypeMarker,
            kind: ConstraintKind,
            isFromNullabilityConstraint: Boolean = false
        ) {
            val typeVariable = c.allTypeVariables[typeVariableConstructor]
                ?: error("Should by type variableConstructor: $typeVariableConstructor. ${c.allTypeVariables.values}")

            addNewIncorporatedConstraint(
                typeVariable,
                type,
                ConstraintContext(kind, emptySet(), isNullabilityConstraint = isFromNullabilityConstraint)
            )
        }

        private fun addNewIncorporatedConstraintFromDeclaredUpperBound(runIsSubtypeOf: Runnable) {
            isIncorporatingConstraintFromDeclaredUpperBound = true
            runIsSubtypeOf.run()
            isIncorporatingConstraintFromDeclaredUpperBound = false
        }

        // from ConstraintIncorporator.Context
        override fun addNewIncorporatedConstraint(
            lowerType: KotlinTypeMarker,
            upperType: KotlinTypeMarker,
            shouldTryUseDifferentFlexibilityForUpperType: Boolean,
            isFromNullabilityConstraint: Boolean,
            isFromDeclaredUpperBound: Boolean
        ) {
            if (lowerType === upperType) return
            if (c.isAllowedType(lowerType) && c.isAllowedType(upperType)) {
                fun runIsSubtypeOf() =
                    runIsSubtypeOf(lowerType, upperType, shouldTryUseDifferentFlexibilityForUpperType, isFromNullabilityConstraint)

                if (isFromDeclaredUpperBound) addNewIncorporatedConstraintFromDeclaredUpperBound(::runIsSubtypeOf) else runIsSubtypeOf()
            }
        }

        override fun addNewIncorporatedConstraint(
            typeVariable: TypeVariableMarker,
            type: KotlinTypeMarker,
            constraintContext: ConstraintContext
        ) {
            val (kind, derivedFrom, inputTypePosition, isNullabilityConstraint) = constraintContext

            var targetType = type
            if (targetType.isUninferredParameter()) {
                // there already should be an error, so there is no point in reporting one more
                return
            }

            if (targetType.isError()) {
                c.addError(ConstrainingTypeIsError(typeVariable, targetType, position))
                return
            }

            if (type.contains(this::isCapturedTypeFromSubtyping)) {
                // TypeVariable <: type -> if TypeVariable <: subType => TypeVariable <: type
                if (kind == UPPER) {
                    val subType =
                        typeApproximator.approximateToSubType(type, TypeApproximatorConfiguration.SubtypeCapturedTypesApproximation)
                    if (subType != null) {
                        targetType = subType
                    }
                }

                if (kind == LOWER) {
                    val superType =
                        typeApproximator.approximateToSuperType(type, TypeApproximatorConfiguration.SubtypeCapturedTypesApproximation)
                    if (superType != null) { // todo rethink error reporting for Any cases
                        targetType = superType
                    }
                }

                if (targetType === type) {
                    c.addError(CapturedTypeFromSubtyping(typeVariable, type, position))
                    return
                }
            }

            val position = if (isIncorporatingConstraintFromDeclaredUpperBound) position.copy(isFromDeclaredUpperBound = true) else position

            val newConstraint = Constraint(
                kind, targetType, position,
                derivedFrom = derivedFrom,
                isNullabilityConstraint = isNullabilityConstraint,
                inputTypePositionBeforeIncorporation = inputTypePosition
            )

            addPossibleNewConstraint(typeVariable, newConstraint)
        }

        override val allTypeVariablesWithConstraints: Collection<VariableWithConstraints>
            get() = c.notFixedTypeVariables.values

        override fun getTypeVariable(typeConstructor: TypeConstructorMarker): TypeVariableMarker? {
            val typeVariable = c.allTypeVariables[typeConstructor]
            if (typeVariable != null && !c.notFixedTypeVariables.containsKey(typeConstructor)) {
                fixedTypeVariable(typeVariable)
            }
            return typeVariable
        }

        override fun getConstraintsForVariable(typeVariable: TypeVariableMarker) =
            c.notFixedTypeVariables[typeVariable.freshTypeConstructor()]?.constraints
                ?: fixedTypeVariable(typeVariable)

        fun fixedTypeVariable(variable: TypeVariableMarker): Nothing {
            error(
                "Type variable $variable should not be fixed!\n" +
                        renderBaseConstraint()
            )
        }

        private fun renderBaseConstraint() = "Base constraint: $baseLowerType <: $baseUpperType from position: $position"
    }
}

data class ConstraintContext(
    val kind: ConstraintKind,
    val derivedFrom: Set<TypeVariableMarker>,
    val inputTypePositionBeforeIncorporation: OnlyInputTypeConstraintPosition? = null,
    val isNullabilityConstraint: Boolean
)
