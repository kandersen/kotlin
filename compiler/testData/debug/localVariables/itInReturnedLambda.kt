// FILE: test.kt
fun box() {
    fun bar() : (Int) -> Unit {
        return {
            1
        }
    }
    bar()(4) // TODO: Without a statement here, the test hangs on the IR backend - probably at fault of the harness.
}

// Names of local functions are used in names of local classes for IR, but only indices are used for non-IR.

// JVM
// METHOD : ItInReturnedLambdaKt$foo$1$1.invoke(I)V
// VARIABLE : NAME=this TYPE=LItInReturnedLambdaKt$foo$1$1; INDEX=0
// VARIABLE : NAME=it TYPE=I INDEX=1

// JVM_IR
// METHOD : ItInReturnedLambdaKt$foo$bar$1.invoke(I)V
// VARIABLE : NAME=this TYPE=LItInReturnedLambdaKt$foo$bar$1; INDEX=0
// VARIABLE : NAME=it TYPE=I INDEX=1

// IGNORE_BACKEND: JVM_IR

// LOCAL VARIABLES
// TestKt:3:
// TestKt:8: $fun$bar$1:TestKt$box$1=TestKt$box$1
// TestKt$box$1:4:
// TestKt:8: $fun$bar$1:TestKt$box$1=TestKt$box$1
// TestKt$box$1$1:6: it:int=4:int
// TestKt$box$1$1.invoke(java.lang.Object)+11:
// TestKt$box$1$1.invoke(java.lang.Object)+14:
// TestKt:8: $fun$bar$1:TestKt$box$1=TestKt$box$1
// TestKt:9: $fun$bar$1:TestKt$box$1=TestKt$box$1