//FILE: test.kt

data class A(val x: String, val y: Int)

fun foo(a: A, block: (A) -> String): String = block(a)

fun box() {
    foo(A("O", 123)) { (x, y) -> x + y }
}

// METHOD : DestructuringInLambdasKt$box$1.invoke(LA;)Ljava/lang/String;
// VARIABLE : NAME=x TYPE=Ljava/lang/String; INDEX=2
// VARIABLE : NAME=y TYPE=I INDEX=3
// VARIABLE : NAME=this TYPE=LDestructuringInLambdasKt$box$1; INDEX=0
// VARIABLE : NAME=$dstr$x$y TYPE=LA; INDEX=1

// LOCAL VARIABLES
// TestKt:8:
// A:3: x:java.lang.String="O":java.lang.String, y:int=123:int
// TestKt:8:
// TestKt:5: a:A=A, block:kotlin.jvm.functions.Function1=TestKt$box$1
// TestKt$box$1:8: $dstr$x$y:A=A

// LOCAL VARIABLES JVM
// TestKt$box$1.invoke(java.lang.Object)+8:

// LOCAL VARIABLES JVM_IR
// A:3:
// TestKt$box$1:8: $dstr$x$y:A=A
// A:3:
// TestKt$box$1:8: $dstr$x$y:A=A, x:java.lang.String="O":java.lang.String
// TestKt$box$1.invoke(java.lang.Object)+8: p1:java.lang.Object=A

// LOCAL VARIABLES
// TestKt:5: a:A=A, block:kotlin.jvm.functions.Function1=TestKt$box$1
// TestKt:8:
// TestKt:9:
