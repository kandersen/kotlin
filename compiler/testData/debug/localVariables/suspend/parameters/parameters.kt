
// WITH_COROUTINES
// FILE: test.kt
data class Data(val x: String, val y: Int, val z: Int = 0)

suspend fun foo(data: Data, body: suspend Long.(String, Data, Int) -> Unit) {
    1L.body("OK", data, 1)
}

suspend fun box() {
    foo(Data("A", 1)) { str, (x, _, z), i ->
        println(str + x + z + i + this)
    }
}

// Discrepancies:
// - The IR backend does not add LVT to:
//   + plain lambda constructor
//   + plain lambda invoke
// - the old backend invokes create

// LOCAL VARIABLES
// test.kt:11 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:4 <init>: x:java.lang.String="A":java.lang.String, y:int=1:int, z:int=0:int
// test.kt:11 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:7 foo: data:Data=Data, body:kotlin.jvm.functions.Function5=TestKt$box$2, $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// CoroutineUtil.kt:28 getContext:

// LOCAL VARIABLES JVM
// test.kt:-1 <init>:
// test.kt:-1 create: $this$create:long=1:long, str:java.lang.String="OK":java.lang.String, $dstr$x$_u24__u24$z:Data=Data, i:int=1:int, continuation:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:-1 invoke:
// LOCAL VARIABLES JVM_IR
// test.kt:-1 <init>: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:-1 invoke: p1:long=1:long, p2:java.lang.String="OK":java.lang.String, p3:Data=Data, p4:int=1:int, p5:kotlin.coroutines.Continuation=helpers.ResultContinuation

// LOCAL VARIABLES
// test.kt:11 invokeSuspend:
// test.kt:12 invokeSuspend: $result:java.lang.Object=kotlin.Unit, $this$foo:long=1:long, str:java.lang.String="OK":java.lang.String, i:int=1:int, x:java.lang.String="A":java.lang.String, z:int=0:int
// test.kt:13 invokeSuspend: $result:java.lang.Object=kotlin.Unit

// LOCAL VARIABLES JVM
// test.kt:-1 invoke:
// LOCAL VARIABLES JVM_IR
// test.kt:-1 invoke: p1:long=1:long, p2:java.lang.String="OK":java.lang.String, p3:Data=Data, p4:int=1:int, p5:kotlin.coroutines.Continuation=helpers.ResultContinuation

// LOCAL VARIABLES
// test.kt:7 foo: data:Data=Data, body:kotlin.jvm.functions.Function5=TestKt$box$2, $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:8 foo: data:Data=Data, body:kotlin.jvm.functions.Function5=TestKt$box$2, $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:11 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:14 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation