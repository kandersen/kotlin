
// WITH_COROUTINES
// FILE: test.kt
data class A(val x: String, val y: String)

suspend inline fun foo(a: A, block: suspend (A) -> String): String = block(a)

suspend fun box(): String {
    return foo(A("O", "K")) { (x_param, y_param) -> x_param + y_param }
}

// TODO: Fix this after other issues in inlining suspend lambdas are resolved.

// METHOD : InlineKt.test(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
// VARIABLE : NAME=x_param TYPE=Ljava/lang/String; INDEX=6
// VARIABLE : NAME=y_param TYPE=Ljava/lang/String; INDEX=7
// VARIABLE : NAME=$dstr$x_param$y_param TYPE=LA; INDEX=4
// VARIABLE : NAME=continuation TYPE=Lkotlin/coroutines/Continuation; INDEX=3
// VARIABLE : NAME=$i$a$-foo-InlineKt$test$2 TYPE=I INDEX=5
// VARIABLE : NAME=a$iv TYPE=LA; INDEX=1
// VARIABLE : NAME=$i$f$foo TYPE=I INDEX=2
// VARIABLE : NAME=$completion TYPE=Lkotlin/coroutines/Continuation; INDEX=0

// LOCAL VARIABLES
// test.kt:9 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:4 <init>: x:java.lang.String="O":java.lang.String, y:java.lang.String="K":java.lang.String
// test.kt:9 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:6 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation, a$iv:A=A, $i$f$foo:int=0:int
// test.kt:9 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation, a$iv:A=A, $i$f$foo:int=0:int, continuation:kotlin.coroutines.Continuation=helpers.ResultContinuation, $dstr$x_param$y_param:A=A, $i$a$-foo-TestKt$box$2:int=0:int, x_param:java.lang.String="O":java.lang.String, y_param:java.lang.String="K":java.lang.String