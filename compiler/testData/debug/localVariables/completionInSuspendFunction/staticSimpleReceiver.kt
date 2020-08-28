// FILE: test.kt
// WITH_RUNTIME
// IGNORE_BACKEND: JVM, JVM_IR
class A

suspend fun A.foo() {}

fun box() {
    A().foo()
}

// METHOD : StaticSimpleReceiverKt.foo(LA;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
// VARIABLE : NAME=$this$foo TYPE=LA; INDEX=0
// VARIABLE : NAME=$completion TYPE=Lkotlin/coroutines/Continuation; INDEX=1