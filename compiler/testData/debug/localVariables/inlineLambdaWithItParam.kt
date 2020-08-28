// FILE: test.kt
class A() {
    inline fun inlineFun(s: (s: Int) -> Unit) {
        s(11)
    }

    fun foo() {
        inlineFun({
                      var zzz = it;
                      zzz++
                  })
    }
}

fun box() {
    A().foo()
}

// IGNORE_BACKEND: JVM_IR

// LOCAL VARIABLES
// TestKt:16:
// A:2:
// TestKt:16:
// A:8:
// A:4: this_$iv:A=A, $i$f$inlineFun:int=0:int
// A:9: this_$iv:A=A, $i$f$inlineFun:int=0:int, it:int=11:int, $i$a$-inlineFun-A$foo$1:int=0:int
// A:10: this_$iv:A=A, $i$f$inlineFun:int=0:int, it:int=11:int, $i$a$-inlineFun-A$foo$1:int=0:int, zzz:int=11:int
// A:11: this_$iv:A=A, $i$f$inlineFun:int=0:int, it:int=11:int, $i$a$-inlineFun-A$foo$1:int=0:int
// A:5: this_$iv:A=A, $i$f$inlineFun:int=0:int
// A:12:
// TestKt:17: