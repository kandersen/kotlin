// FILE: test.kt
inline fun foo(x: (Int, Station) -> Unit) {
    x(1, Station(null, "", 1))
}

data class Station(
        val id: String?,
        val name: String,
        val distance: Int)

fun box(): String {
    foo { i, (a1, a2, a3) -> i + a3 }
    return "OK"
}

// IGNORE_BACKEND: JVM_IR
// METHOD : DestructuringInlineLambdaKt.box()Ljava/lang/String;
// VARIABLE : NAME=a1 TYPE=Ljava/lang/String; INDEX=4
// VARIABLE : NAME=a2 TYPE=Ljava/lang/String; INDEX=5
// VARIABLE : NAME=a3 TYPE=I INDEX=6
// VARIABLE : NAME=i TYPE=I INDEX=2
// VARIABLE : NAME=$dstr$a1$a2$a3 TYPE=LStation; INDEX=1
// VARIABLE : NAME=$i$a$-foo-DestructuringInlineLambdaKt$box$1 TYPE=I INDEX=3
// VARIABLE : NAME=$i$f$foo TYPE=I INDEX=0

// LOCAL VARIABLES
// TestKt:12:
// TestKt:3: $i$f$foo:int=0:int
// Station:6: id:java.lang.String=null, name:java.lang.String="":java.lang.String, distance:int=1:int
// TestKt:3: $i$f$foo:int=0:int
// TestKt:12: $i$f$foo:int=0:int, $dstr$a1$a2$a3:Station=Station, i:int=1:int, $i$a$-foo-TestKt$box$1:int=0:int, a1:java.lang.String=null, a2:java.lang.String="":java.lang.String, a3:int=1:int
// TestKt:4: $i$f$foo:int=0:int
// TestKt:13: