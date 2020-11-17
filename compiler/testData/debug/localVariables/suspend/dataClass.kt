
// WITH_COROUTINES
// FILE: test.kt
data class Data(val x: String, val y: Int)

suspend fun foo(data: Data, body: suspend (Data) -> Unit) {
    body(data)
}

suspend fun box() {
    foo(Data("A", 1)) { (x_param, y_param) ->
        "$x_param / $y_param"
    }
}

// LOCAL VARIABLES
// test.kt:11 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:4 <init>: x:java.lang.String="A":java.lang.String, y:int=1:int
// test.kt:11 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:7 foo: data:Data=Data, body:kotlin.jvm.functions.Function2=TestKt$box$2, $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// CoroutineUtil.kt:28 getContext:
// test.kt:-1 <init>:
// test.kt:-1 create: value:java.lang.Object=Data, completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:-1 invoke:
// test.kt:11 invokeSuspend:
// test.kt:12 invokeSuspend: $result:java.lang.Object=kotlin.Unit, x_param:java.lang.String="A":java.lang.String, y_param:int=1:int
// test.kt:13 invokeSuspend: $result:java.lang.Object=kotlin.Unit
// test.kt:-1 invoke:
// test.kt:7 foo: data:Data=Data, body:kotlin.jvm.functions.Function2=TestKt$box$2, $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:8 foo: data:Data=Data, body:kotlin.jvm.functions.Function2=TestKt$box$2, $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:11 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:14 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
