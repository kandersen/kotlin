// FILE: test.kt
fun box() {

    try {
        var a = 1
        a--
        a /= a
    }
    catch(e : Throwable) {
        e.printStackTrace()
    }
}

// LOCAL VARIABLES
// TestKt:4:
// TestKt:5:
// TestKt:6: a:int=1:int
// TestKt:7: a:int=0:int
// TestKt:9:
// TestKt:10: e:java.lang.Throwable=java.lang.ArithmeticException
// TestKt:12: