//FILE: test.kt

fun foo() {
    fun bar()  {
    }
}

fun box() {
    foo()
}

// LOCAL VARIABLES
// LOCAL VARIABLES JVM
// TestKt:9:
// TestKt:4:
// TestKt:6: $fun$bar$1:TestKt$foo$1=TestKt$foo$1
// TestKt:10:

// LOCAL VARIABLES JVM_IR
// TestKt:9:
// TestKt:6:
// TestKt:10: