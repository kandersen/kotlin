

//FILE: test.kt
data class A(val x: Char = 'X', val y: String = "", val z: Char = 'Z')

fun foo(block: (A, String, Int) -> String): String {
    return block(A(x = 'O', z = 'K'), "FAIL", 0)
}

fun box(): String {
    return foo { (o, _, k), _, w ->
        "$o$k"
    }
}


// LOCAL VARIABLES
// test.kt:11 box:
// test.kt:7 foo: block:kotlin.jvm.functions.Function3=TestKt$box$1
// test.kt:4 <init>: x:char=O:char, y:java.lang.String="":java.lang.String, z:char=K:char
// test.kt:7 foo: block:kotlin.jvm.functions.Function3=TestKt$box$1
// test.kt:12 invoke: $dstr$o$_u24__u24$k:A=A, $noName_1:java.lang.String="FAIL":java.lang.String, w:int=0:int
// test.kt:7 foo: block:kotlin.jvm.functions.Function3=TestKt$box$1
// test.kt:11 box:
