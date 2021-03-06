/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.analysis.checkers.extended

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.fir.FirFakeSourceElementKind
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirBasicExpresionChecker
import org.jetbrains.kotlin.fir.analysis.diagnostics.DiagnosticReporter
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors.REDUNDANT_SINGLE_EXPRESSION_STRING_TEMPLATE
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall
import org.jetbrains.kotlin.fir.expressions.FirStatement
import org.jetbrains.kotlin.fir.psi
import org.jetbrains.kotlin.fir.symbols.StandardClassIds
import org.jetbrains.kotlin.fir.types.classId
import org.jetbrains.kotlin.fir.types.coneType
import org.jetbrains.kotlin.psi.KtStringTemplateExpression

object RedundantSingleExpressionStringTemplateChecker : FirBasicExpresionChecker() {
    override fun check(functionCall: FirStatement, context: CheckerContext, reporter: DiagnosticReporter) {
        if (functionCall.source?.kind != FirFakeSourceElementKind.GeneratedToStringCallOnTemplateEntry) return
        if (functionCall !is FirFunctionCall) return
        if (
            functionCall.explicitReceiver?.typeRef?.coneType?.classId == StandardClassIds.String
            && functionCall.psi?.findStringParent()?.children?.size == 1 // there is no more children in original string template
        ) {
            reporter.report(functionCall.source, REDUNDANT_SINGLE_EXPRESSION_STRING_TEMPLATE)
        }
    }

    private fun PsiElement.findStringParent(): KtStringTemplateExpression? {
        if (this is KtStringTemplateExpression) return this
        return this.parent?.findStringParent()
    }
}
