// FILE: test.kt
inline fun inlineFun(s: () -> Unit) {
    s()
}

fun box() {
    var s = 1;
    inlineFun ({
                   var zzz = 2;
                   zzz++
               })
}

// IGNORE_BACKEND: JVM_IR

// LOCAL VARIABLES
// TestKt:7:
// TestKt:8: s:int=1:int
// TestKt:3: s:int=1:int, $i$f$inlineFun:int=0:int
// TestKt:9: s:int=1:int, $i$f$inlineFun:int=0:int, $i$a$-inlineFun-TestKt$box$1:int=0:int
// TestKt:10: s:int=1:int, $i$f$inlineFun:int=0:int, $i$a$-inlineFun-TestKt$box$1:int=0:int, zzz:int=2:int
// TestKt:11: s:int=1:int, $i$f$inlineFun:int=0:int, $i$a$-inlineFun-TestKt$box$1:int=0:int
// TestKt:4: s:int=1:int, $i$f$inlineFun:int=0:int
// TestKt:12: s:int=1:int