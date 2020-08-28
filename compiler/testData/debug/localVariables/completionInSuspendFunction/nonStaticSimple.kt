// FILE: test.kt
// WITH_RUNTIME
// IGNORE_BACKEND: JVM, JVM_IR
class A {
    suspend fun foo() {}
}

fun box() {
    A().foo()
}

// METHOD : A.foo(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
// VARIABLE : NAME=this TYPE=LA; INDEX=0
// VARIABLE : NAME=$completion TYPE=Lkotlin/coroutines/Continuation; INDEX=1
