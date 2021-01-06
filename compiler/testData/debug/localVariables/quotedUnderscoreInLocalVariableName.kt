

//FILE: test.kt
data class A(val x: Double = 1.0, val y: String = "", val z: Char = '0')

fun box(): String {
    val (a, `_`, k) = A(z = 'K')
    return "O" + k
}

// LOCAL VARIABLES
// test.kt:7 box:
// test.kt:4 <init>: x:double=1.0:double, y:java.lang.String="":java.lang.String, z:char=K:char
// test.kt:7 box:
// test.kt:8 box: a:double=1.0:double, _:java.lang.String="":java.lang.String, k:char=K:char