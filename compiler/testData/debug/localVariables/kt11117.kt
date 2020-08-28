// FILE: test.kt
class A(val value: String)

fun A.test(): String {
    val o = object  {
        val z: String
        init {
            val x = value + "K"
            z = x
        }
    }
    return o.z
}

fun box() {
    A("O").test()
}

// IGNORE_BACKEND: JVM_IR
// After `TestKt$test$o$1:10`, there's an extra step to `TestKt$test$o$1:5`

// LOCAL VARIABLES
// TestKt:16:
// A:2: value:java.lang.String="O":java.lang.String
// TestKt:16:
// TestKt:5: $this$test:A=A
// TestKt$test$o$1:5: $receiver:A=A
// TestKt$test$o$1:7: $receiver:A=A
// TestKt$test$o$1:8: $receiver:A=A
// A:2:
// TestKt$test$o$1:8: $receiver:A=A
// TestKt$test$o$1:9: $receiver:A=A, x:java.lang.String="OK":java.lang.String
// TestKt$test$o$1:10: $receiver:A=A
// TestKt:5: $this$test:A=A
// TestKt:12: $this$test:A=A, o:TestKt$test$o$1=TestKt$test$o$1
// TestKt$test$o$1:6:
// TestKt:12: $this$test:A=A, o:TestKt$test$o$1=TestKt$test$o$1
// TestKt:16:
// TestKt:17: