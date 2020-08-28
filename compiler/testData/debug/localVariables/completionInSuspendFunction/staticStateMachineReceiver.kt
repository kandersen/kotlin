// FILE: test.kt
// WITH_RUNTIME
// IGNORE_BACKEND: JVM, JVM_IR
class A

suspend fun A.foo() {}
suspend fun A.foo1(l: Long) {
    foo()
    foo()
    val dead = l
}

fun box() {
    A().foo1(4L)
}
// METHOD : StaticStateMachineReceiverKt.foo1(LA;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;
// VARIABLE : NAME=$this$foo1 TYPE=LA; INDEX=0
// VARIABLE : NAME=l TYPE=J INDEX=1
// VARIABLE : NAME=$continuation TYPE=Lkotlin/coroutines/Continuation; INDEX=7
// VARIABLE : NAME=$result TYPE=Ljava/lang/Object; INDEX=6