

//FILE: test.kt
data class A(val x: Double = 1.0, val y: String = "", val z: Char = '0')

val arrayOfA: Array<A> = arrayOf(A())

fun box(): String {
    for ((_, q) in arrayOfA) {
        return "O" + q + "K"
    }
    return "FAIL"
}

// LOCAL VARIABLES
// test.kt:9 box:
// test.kt:10 box: q:java.lang.String="":java.lang.String
