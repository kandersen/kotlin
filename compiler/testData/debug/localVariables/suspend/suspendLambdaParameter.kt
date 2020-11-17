
// WITH_COROUTINES
// FILE: test.kt

suspend fun box(): String {
    var s = "OK"
    s = strChanger(s) { character ->
        character != 'a'
    }
    return s
}

suspend fun strChanger(str: String, pred: suspend (Char) -> Boolean): String {
    var result = ""
    str.forEach {
        if (pred(it)) {
            result += it
        }
    }
    return result
}

// LOCAL VARIABLES
// test.kt:5 box:
// CoroutineUtil.kt:28 getContext:
// test.kt:-1 <init>:
// test.kt:5 box:
// test.kt:6 box: $continuation:kotlin.coroutines.Continuation=TestKt$box$1, $result:java.lang.Object=null
// test.kt:7 box: $continuation:kotlin.coroutines.Continuation=TestKt$box$1, $result:java.lang.Object=null, s:java.lang.String="OK":java.lang.String
// test.kt:13 strChanger:
// test.kt:14 strChanger: $continuation:kotlin.coroutines.Continuation=TestKt$strChanger$1, $result:java.lang.Object=null, str:java.lang.String="OK":java.lang.String, pred:kotlin.jvm.functions.Function2=TestKt$box$2
// test.kt:15 strChanger: $continuation:kotlin.coroutines.Continuation=TestKt$strChanger$1, $result:java.lang.Object=null, str:java.lang.String="OK":java.lang.String, pred:kotlin.jvm.functions.Function2=TestKt$box$2, result:kotlin.jvm.internal.Ref$ObjectRef=kotlin.jvm.internal.Ref$ObjectRef
// _Strings.kt:1139 strChanger: $continuation:kotlin.coroutines.Continuation=TestKt$strChanger$1, $result:java.lang.Object=null, pred:kotlin.jvm.functions.Function2=TestKt$box$2, result:kotlin.jvm.internal.Ref$ObjectRef=kotlin.jvm.internal.Ref$ObjectRef, $this$forEach$iv:java.lang.CharSequence="OK":java.lang.String, $i$f$forEach:int=0:int
// test.kt:16 strChanger: $continuation:kotlin.coroutines.Continuation=TestKt$strChanger$1, $result:java.lang.Object=null, pred:kotlin.jvm.functions.Function2=TestKt$box$2, result:kotlin.jvm.internal.Ref$ObjectRef=kotlin.jvm.internal.Ref$ObjectRef, $i$f$forEach:int=0:int, it:char=O:char, $i$a$-forEach-TestKt$strChanger$2:int=0:int
// test.kt:17 strChanger: $continuation:kotlin.coroutines.Continuation=TestKt$strChanger$1, $result:java.lang.Object=null, pred:kotlin.jvm.functions.Function2=TestKt$box$2, result:kotlin.jvm.internal.Ref$ObjectRef=kotlin.jvm.internal.Ref$ObjectRef, $i$f$forEach:int=0:int, it:char=O:char, $i$a$-forEach-TestKt$strChanger$2:int=0:int
// test.kt:19 strChanger: $continuation:kotlin.coroutines.Continuation=TestKt$strChanger$1, $result:java.lang.Object=null, pred:kotlin.jvm.functions.Function2=TestKt$box$2, result:kotlin.jvm.internal.Ref$ObjectRef=kotlin.jvm.internal.Ref$ObjectRef, $i$f$forEach:int=0:int, $i$a$-forEach-TestKt$strChanger$2:int=0:int
// _Strings.kt:1139 strChanger: $continuation:kotlin.coroutines.Continuation=TestKt$strChanger$1, $result:java.lang.Object=null, pred:kotlin.jvm.functions.Function2=TestKt$box$2, result:kotlin.jvm.internal.Ref$ObjectRef=kotlin.jvm.internal.Ref$ObjectRef, $i$f$forEach:int=0:int
// test.kt:16 strChanger: $continuation:kotlin.coroutines.Continuation=TestKt$strChanger$1, $result:java.lang.Object=null, pred:kotlin.jvm.functions.Function2=TestKt$box$2, result:kotlin.jvm.internal.Ref$ObjectRef=kotlin.jvm.internal.Ref$ObjectRef, $i$f$forEach:int=0:int, it:char=K:char, $i$a$-forEach-TestKt$strChanger$2:int=0:int
// test.kt:17 strChanger: $continuation:kotlin.coroutines.Continuation=TestKt$strChanger$1, $result:java.lang.Object=null, pred:kotlin.jvm.functions.Function2=TestKt$box$2, result:kotlin.jvm.internal.Ref$ObjectRef=kotlin.jvm.internal.Ref$ObjectRef, $i$f$forEach:int=0:int, it:char=K:char, $i$a$-forEach-TestKt$strChanger$2:int=0:int
// test.kt:19 strChanger: $continuation:kotlin.coroutines.Continuation=TestKt$strChanger$1, $result:java.lang.Object=null, pred:kotlin.jvm.functions.Function2=TestKt$box$2, result:kotlin.jvm.internal.Ref$ObjectRef=kotlin.jvm.internal.Ref$ObjectRef, $i$f$forEach:int=0:int, $i$a$-forEach-TestKt$strChanger$2:int=0:int
// _Strings.kt:1139 strChanger: $continuation:kotlin.coroutines.Continuation=TestKt$strChanger$1, $result:java.lang.Object=null, pred:kotlin.jvm.functions.Function2=TestKt$box$2, result:kotlin.jvm.internal.Ref$ObjectRef=kotlin.jvm.internal.Ref$ObjectRef, $i$f$forEach:int=0:int
// _Strings.kt:1140 strChanger: $continuation:kotlin.coroutines.Continuation=TestKt$strChanger$1, $result:java.lang.Object=null, result:kotlin.jvm.internal.Ref$ObjectRef=kotlin.jvm.internal.Ref$ObjectRef, $i$f$forEach:int=0:int
// test.kt:20 strChanger: $continuation:kotlin.coroutines.Continuation=TestKt$strChanger$1, $result:java.lang.Object=null, result:kotlin.jvm.internal.Ref$ObjectRef=kotlin.jvm.internal.Ref$ObjectRef
// test.kt:7 box: $continuation:kotlin.coroutines.Continuation=TestKt$box$1, $result:java.lang.Object=null, s:java.lang.String="OK":java.lang.String
// test.kt:5 box: $continuation:kotlin.coroutines.Continuation=TestKt$box$1, $result:java.lang.Object=null, s:java.lang.String=null
// test.kt:10 box: $continuation:kotlin.coroutines.Continuation=TestKt$box$1, $result:java.lang.Object=null, s:java.lang.String="OK":java.lang.String