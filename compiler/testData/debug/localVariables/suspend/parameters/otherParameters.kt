
// WITH_COROUTINES
// FILE: test.kt
data class A(val x: String, val y: String)

suspend fun foo(a: A, block: suspend (Int, A, String) -> String): String = block(1, a, "#")

suspend fun box() = foo(A("O", "K")) { i_param, (x_param, y_param), v_param ->
    i_param.toString() + x_param + y_param + v_param
}

// METHOD : OtherParametersKt$test$2.invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

// VARIABLE : NAME=i_param TYPE=I INDEX=2
// VARIABLE : NAME=$dstr$x_param$y_param TYPE=LA; INDEX=3
// VARIABLE : NAME=v_param TYPE=Ljava/lang/String; INDEX=4
// VARIABLE : NAME=x_param TYPE=Ljava/lang/String; INDEX=5
// VARIABLE : NAME=y_param TYPE=Ljava/lang/String; INDEX=6
// VARIABLE : NAME=this TYPE=LOtherParametersKt$test$2; INDEX=0
// VARIABLE : NAME=$result TYPE=Ljava/lang/Object; INDEX=1

// LOCAL VARIABLES
// test.kt:8 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:4 <init>: x:java.lang.String="O":java.lang.String, y:java.lang.String="K":java.lang.String
// test.kt:8 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:6 foo: a:A=A, block:kotlin.jvm.functions.Function4=TestKt$box$2, $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// CoroutineUtil.kt:28 getContext:
// test.kt:-1 <init>:
// test.kt:-1 create: i_param:int=1:int, $dstr$x_param$y_param:A=A, v_param:java.lang.String="#":java.lang.String, continuation:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:-1 invoke:
// test.kt:8 invokeSuspend:
// test.kt:9 invokeSuspend: $result:java.lang.Object=kotlin.Unit, i_param:int=1:int, v_param:java.lang.String="#":java.lang.String, x_param:java.lang.String="O":java.lang.String, y_param:java.lang.String="K":java.lang.String
// test.kt:-1 invoke:
// test.kt:6 foo: a:A=A, block:kotlin.jvm.functions.Function4=TestKt$box$2, $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:10 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation