// FILE: test.kt
// WITH_RUNTIME
// IGNORE_BACKEND: JVM, JVM_IR
suspend fun foo() {}

fun box() {
    runBlocking { foo() }
}

// METHOD : StaticSimpleKt.foo(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
// VARIABLE : NAME=$completion TYPE=Lkotlin/coroutines/Continuation; INDEX=0
