
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


// METHOD : DataClassKt$test$2.invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

// VARIABLE : NAME=$dstr$x_param$y_param TYPE=LData; INDEX=2
// VARIABLE : NAME=x_param TYPE=Ljava/lang/String; INDEX=3
// VARIABLE : NAME=y_param TYPE=I INDEX=4
// VARIABLE : NAME=this TYPE=LDataClassKt$test$2; INDEX=0
// VARIABLE : NAME=$result TYPE=Ljava/lang/Object; INDEX=1

// LOCAL VARIABLES
// test.kt:10 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:4 <init>: x:java.lang.String="A":java.lang.String, y:int=1:int
// test.kt:10 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:7 foo: data:Data=Data, body:kotlin.jvm.functions.Function2=TestKt$box$2, $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// CoroutineUtil.kt:28 getContext:
// test.kt:-1 <init>:
// test.kt:-1 create: value:java.lang.Object=Data, completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:-1 invoke:
// test.kt:10 invokeSuspend:
// test.kt:11 invokeSuspend: $result:java.lang.Object=kotlin.Unit, x_param:java.lang.String="A":java.lang.String, y_param:int=1:int
// test.kt:12 invokeSuspend: $result:java.lang.Object=kotlin.Unit
// test.kt:-1 invoke:
// test.kt:7 foo: data:Data=Data, body:kotlin.jvm.functions.Function2=TestKt$box$2, $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:8 foo: data:Data=Data, body:kotlin.jvm.functions.Function2=TestKt$box$2, $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:10 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation
// test.kt:13 box: $completion:kotlin.coroutines.Continuation=helpers.ResultContinuation