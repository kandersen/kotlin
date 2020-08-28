// FILE: test.kt
data class A(val x: Double = 1.0, val y: String = "", val z: Char = '0')

fun foo(a: A, block: (A, String, Int) -> String): String = block(a, "", 1)

val arrayOfA: Array<A> = Array(1) { A() }

fun box() {

    foo(A()) {
        (x, _, y), _, w ->

        val (a, _, c) = A()
        val (_, `_`, d) = A()

        for ((_, q) in arrayOfA) {

        }

        ""
    }
}

// METHOD : UnderscoreNamesKt$box$1.invoke(LA;Ljava/lang/String;I)Ljava/lang/String;
// VARIABLE : NAME=x TYPE=D INDEX=4
// VARIABLE : NAME=y TYPE=C INDEX=6
// VARIABLE : NAME=q TYPE=Ljava/lang/String; INDEX=16
// VARIABLE : NAME=d TYPE=C INDEX=11
// VARIABLE : NAME=_ TYPE=Ljava/lang/String; INDEX=10
// VARIABLE : NAME=c TYPE=C INDEX=9
// VARIABLE : NAME=a TYPE=D INDEX=7
// VARIABLE : NAME=this TYPE=LUnderscoreNamesKt$box$1; INDEX=0
// VARIABLE : NAME=$dstr$x$_u24__u24$y TYPE=LA; INDEX=1
// VARIABLE : NAME=$noName_1 TYPE=Ljava/lang/String; INDEX=2
// VARIABLE : NAME=w TYPE=I INDEX=3

// IGNORE_BACKEND: JVM_IR

// LOCAL VARIABLES
// TestKt:10:
// A:2:
// A:2: x:double=1.0:double, y:java.lang.String="":java.lang.String, z:char=0:char
// A:2:
// TestKt:10:
// TestKt:4: a:A=A, block:kotlin.jvm.functions.Function3=TestKt$box$1
// TestKt$box$1:13: $dstr$x$_u24__u24$y:A=A, $noName_1:java.lang.String="":java.lang.String, w:int=1:int
// A:2:
// A:2: x:double=1.0:double, y:java.lang.String="":java.lang.String, z:char=0:char
// A:2:
// TestKt$box$1:13: $dstr$x$_u24__u24$y:A=A, $noName_1:java.lang.String="":java.lang.String, w:int=1:int, x:double=1.0:double, y:char=0:char
// TestKt$box$1:14: $dstr$x$_u24__u24$y:A=A, $noName_1:java.lang.String="":java.lang.String, w:int=1:int, x:double=1.0:double, y:char=0:char, a:double=1.0:double, c:char=0:char
// A:2:
// A:2: x:double=1.0:double, y:java.lang.String="":java.lang.String, z:char=0:char
// A:2:
// TestKt$box$1:14: $dstr$x$_u24__u24$y:A=A, $noName_1:java.lang.String="":java.lang.String, w:int=1:int, x:double=1.0:double, y:char=0:char, a:double=1.0:double, c:char=0:char
// TestKt$box$1:16: $dstr$x$_u24__u24$y:A=A, $noName_1:java.lang.String="":java.lang.String, w:int=1:int, x:double=1.0:double, y:char=0:char, a:double=1.0:double, c:char=0:char, _:java.lang.String="":java.lang.String, d:char=0:char
// TestKt:6:
// TestKt$box$1:16: $dstr$x$_u24__u24$y:A=A, $noName_1:java.lang.String="":java.lang.String, w:int=1:int, x:double=1.0:double, y:char=0:char, a:double=1.0:double, c:char=0:char, _:java.lang.String="":java.lang.String, d:char=0:char
// TestKt$box$1:20: $dstr$x$_u24__u24$y:A=A, $noName_1:java.lang.String="":java.lang.String, w:int=1:int, x:double=1.0:double, y:char=0:char, a:double=1.0:double, c:char=0:char, _:java.lang.String="":java.lang.String, d:char=0:char
// TestKt$box$1.invoke(java.lang.Object, java.lang.Object, java.lang.Object)+19:
// TestKt:4: a:A=A, block:kotlin.jvm.functions.Function3=TestKt$box$1
// TestKt:10:
// TestKt:22: