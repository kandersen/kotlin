// FILE: test.kt
fun String.foo(a: Int) {}

fun box() {
    "OK".foo(42)
}

// LOCAL VARIABLES
// TestKt:5:
// TestKt:2: $this$foo:java.lang.String, a:int
// TestKt:6: