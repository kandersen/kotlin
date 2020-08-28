// FILE: test.kt
inline fun inlineFun(s: () -> Unit) {
    s()
}

fun box() {
    var s = 0;
    inlineFun {
        var z = 1;
        z++

        inlineFun {
            var zz2 = 2;
            zz2++
        }
    }
}

// IGNORE_BACKEND: JVM_IR

// LOCAL VARIABLES
// TestKt:7:
// TestKt:8: s:int=0:int
// TestKt:3: s:int=0:int, $i$f$inlineFun:int=0:int
// TestKt:9: s:int=0:int, $i$f$inlineFun:int=0:int, $i$a$-inlineFun-TestKt$box$1:int=0:int
// TestKt:10: s:int=0:int, $i$f$inlineFun:int=0:int, $i$a$-inlineFun-TestKt$box$1:int=0:int, z:int=1:int
// TestKt:12: s:int=0:int, $i$f$inlineFun:int=0:int, $i$a$-inlineFun-TestKt$box$1:int=0:int, z:int=2:int
// TestKt:3: s:int=0:int, $i$a$-inlineFun-TestKt$box$1:int=0:int, z:int=2:int, $i$f$inlineFun:int=0:int
// TestKt:13: s:int=0:int, $i$a$-inlineFun-TestKt$box$1:int=0:int, z:int=2:int, $i$f$inlineFun:int=0:int, $i$a$-inlineFun-TestKt$box$1$1:int=0:int
// TestKt:14: s:int=0:int, $i$a$-inlineFun-TestKt$box$1:int=0:int, z:int=2:int, $i$f$inlineFun:int=0:int, $i$a$-inlineFun-TestKt$box$1$1:int=0:int, zz2:int=2:int
// TestKt:15: s:int=0:int, $i$a$-inlineFun-TestKt$box$1:int=0:int, z:int=2:int, $i$f$inlineFun:int=0:int, $i$a$-inlineFun-TestKt$box$1$1:int=0:int
// TestKt:4: s:int=0:int, $i$a$-inlineFun-TestKt$box$1:int=0:int, z:int=2:int, $i$f$inlineFun:int=0:int
// TestKt:16: s:int=0:int, $i$f$inlineFun:int=0:int, $i$a$-inlineFun-TestKt$box$1:int=0:int
// TestKt:4: s:int=0:int, $i$f$inlineFun:int=0:int
// TestKt:17: s:int=0:int