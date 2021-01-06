
// WITH_COROUTINES
// FILE: test.kt
data class A<T, F>(val x: T, val y: F)

suspend fun <X, Y> foo(a: A<X, Y>, block: suspend (A<X, Y>) -> String) = block(a)

suspend fun box(): String {
    return foo(A("OK", 1)) { (x_param, y_param) ->
        x_param + (y_param.toString())
    }
}

// METHOD : GenericKt$test$2.invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

// VARIABLE : NAME=$dstr$x_param$y_param TYPE=LA; INDEX=2
// VARIABLE : NAME=x_param TYPE=Ljava/lang/String; INDEX=3
// VARIABLE : NAME=y_param TYPE=I INDEX=4
// VARIABLE : NAME=this TYPE=LGenericKt$test$2; INDEX=0
// VARIABLE : NAME=$result TYPE=Ljava/lang/Object; INDEX=1

// LOCAL VARIABLES
// test.kt:9 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:4 <init>: x:java.lang.Object="OK":java.lang.String, y:java.lang.Object=java.lang.Integer
// test.kt:9 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:6 foo: a:A=A, block:kotlin.jvm.functions.Function2=TestKt$box$2, $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// CoroutineUtil.kt:28 getContext:
// test.kt:-1 <init>:
// test.kt:-1 create: value:java.lang.Object=A, completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:-1 invoke:
// test.kt:9 invokeSuspend:
// test.kt:10 invokeSuspend: $result:java.lang.Object=kotlin.Unit, x_param:java.lang.String="OK":java.lang.String, y_param:int=1:int
// test.kt:-1 invoke:
// test.kt:6 foo: a:A=A, block:kotlin.jvm.functions.Function2=TestKt$box$2, $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:9 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation