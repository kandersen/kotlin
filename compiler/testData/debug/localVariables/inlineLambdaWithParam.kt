// FILE: test.kt
inline fun inlineFun(s: (s: Int) -> Unit, p : Int) {
    s(11)

    s(p)
}

fun box() {
    inlineFun ({ l ->
                   var zzz = l;
                   zzz++
               }, 11)
}

// IGNORE_BACKEND: JVM_IR

// LOCAL VARIABLES
// TestKt:12:
// TestKt:9: p$iv:int=11:int
// TestKt:3: p$iv:int=11:int, $i$f$inlineFun:int=0:int
// TestKt:10: p$iv:int=11:int, $i$f$inlineFun:int=0:int, l:int=11:int, $i$a$-inlineFun-TestKt$box$1:int=0:int
// TestKt:11: p$iv:int=11:int, $i$f$inlineFun:int=0:int, l:int=11:int, $i$a$-inlineFun-TestKt$box$1:int=0:int, zzz:int=11:int
// TestKt:12: p$iv:int=11:int, $i$f$inlineFun:int=0:int, l:int=11:int, $i$a$-inlineFun-TestKt$box$1:int=0:int
// TestKt:5: p$iv:int=11:int, $i$f$inlineFun:int=0:int
// TestKt:10: p$iv:int=11:int, $i$f$inlineFun:int=0:int, l:int=11:int, $i$a$-inlineFun-TestKt$box$1:int=0:int
// TestKt:11: p$iv:int=11:int, $i$f$inlineFun:int=0:int, l:int=11:int, $i$a$-inlineFun-TestKt$box$1:int=0:int, zzz:int=11:int
// TestKt:12: p$iv:int=11:int, $i$f$inlineFun:int=0:int, l:int=11:int, $i$a$-inlineFun-TestKt$box$1:int=0:int
// TestKt:6: p$iv:int=11:int, $i$f$inlineFun:int=0:int
// TestKt:13: