//FILE: test.kt

data class someClass(val a: Double, val b: Double)

fun box() {
    val a = someClass(1.0, 2.0)
    val b = a.copy(b = 3.0)
}

// LOCAL VARIABLES
// TestKt:6:
// someClass:3: a:double=1.0:double, b:double=2.0:double
// TestKt:6:
// TestKt:7: a:someClass=someClass

// LOCAL VARIABLES JVM_IR
// someClass:3:
// someClass:3: a:double=1.0:double, b:double=3.0:double
// someClass:3: a:double=1.0:double, b:double=3.0:double
// someClass:3: a:double=1.0:double, b:double=3.0:double
// someClass:3:

// LOCAL VARIABLES JVM
// someClass:3: a:double=1.0:double, b:double=3.0:double
// someClass.copy(double, double)+9: a:double=1.0:double, b:double=3.0:double
// someClass.copy$default(someClass, double, double, int, java.lang.Object)+30:

// LOCAL VARIABLES
// TestKt:7: a:someClass=someClass
// TestKt:8: a:someClass=someClass, b:someClass=someClass