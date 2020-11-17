

// FILE: test.kt

fun box(): String {
    var s = "OK"
    s = strChanger(s) { character ->
        character != 'a'
    }
    return s
}

fun strChanger(str: String, pred: (Char) -> Boolean): String {
    var result = ""
    str.forEach {
        if (pred(it)) {
            result += it
        }
    }
    return result
}

// LOCAL VARIABLES
// test.kt:6 box:
// test.kt:7 box: s:java.lang.String="OK":java.lang.String
// test.kt:14 strChanger: str:java.lang.String="OK":java.lang.String, pred:kotlin.jvm.functions.Function1=TestKt$box$1
// test.kt:15 strChanger: str:java.lang.String="OK":java.lang.String, pred:kotlin.jvm.functions.Function1=TestKt$box$1, result:java.lang.Object="":java.lang.String
// _Strings.kt:1139 strChanger: str:java.lang.String="OK":java.lang.String, pred:kotlin.jvm.functions.Function1=TestKt$box$1, result:java.lang.Object="":java.lang.String, $this$forEach$iv:java.lang.CharSequence="OK":java.lang.String, $i$f$forEach:int=0:int
// test.kt:16 strChanger: str:java.lang.String="OK":java.lang.String, pred:kotlin.jvm.functions.Function1=TestKt$box$1, result:java.lang.Object="":java.lang.String, $this$forEach$iv:java.lang.CharSequence="OK":java.lang.String, $i$f$forEach:int=0:int, element$iv:char=O:char, it:char=O:char, $i$a$-forEach-TestKt$strChanger$1:int=0:int
// test.kt:8 invoke: character:char=O:char
// test.kt:16 strChanger: str:java.lang.String="OK":java.lang.String, pred:kotlin.jvm.functions.Function1=TestKt$box$1, result:java.lang.Object="":java.lang.String, $this$forEach$iv:java.lang.CharSequence="OK":java.lang.String, $i$f$forEach:int=0:int, element$iv:char=O:char, it:char=O:char, $i$a$-forEach-TestKt$strChanger$1:int=0:int
// test.kt:17 strChanger: str:java.lang.String="OK":java.lang.String, pred:kotlin.jvm.functions.Function1=TestKt$box$1, result:java.lang.Object="":java.lang.String, $this$forEach$iv:java.lang.CharSequence="OK":java.lang.String, $i$f$forEach:int=0:int, element$iv:char=O:char, it:char=O:char, $i$a$-forEach-TestKt$strChanger$1:int=0:int
// test.kt:19 strChanger: str:java.lang.String="OK":java.lang.String, pred:kotlin.jvm.functions.Function1=TestKt$box$1, result:java.lang.Object="O":java.lang.String, $this$forEach$iv:java.lang.CharSequence="OK":java.lang.String, $i$f$forEach:int=0:int, element$iv:char=O:char, it:char=O:char, $i$a$-forEach-TestKt$strChanger$1:int=0:int
// _Strings.kt:1139 strChanger: str:java.lang.String="OK":java.lang.String, pred:kotlin.jvm.functions.Function1=TestKt$box$1, result:java.lang.Object="O":java.lang.String, $this$forEach$iv:java.lang.CharSequence="OK":java.lang.String, $i$f$forEach:int=0:int
// test.kt:16 strChanger: str:java.lang.String="OK":java.lang.String, pred:kotlin.jvm.functions.Function1=TestKt$box$1, result:java.lang.Object="O":java.lang.String, $this$forEach$iv:java.lang.CharSequence="OK":java.lang.String, $i$f$forEach:int=0:int, element$iv:char=K:char, it:char=K:char, $i$a$-forEach-TestKt$strChanger$1:int=0:int
// test.kt:8 invoke: character:char=K:char
// test.kt:16 strChanger: str:java.lang.String="OK":java.lang.String, pred:kotlin.jvm.functions.Function1=TestKt$box$1, result:java.lang.Object="O":java.lang.String, $this$forEach$iv:java.lang.CharSequence="OK":java.lang.String, $i$f$forEach:int=0:int, element$iv:char=K:char, it:char=K:char, $i$a$-forEach-TestKt$strChanger$1:int=0:int
// test.kt:17 strChanger: str:java.lang.String="OK":java.lang.String, pred:kotlin.jvm.functions.Function1=TestKt$box$1, result:java.lang.Object="O":java.lang.String, $this$forEach$iv:java.lang.CharSequence="OK":java.lang.String, $i$f$forEach:int=0:int, element$iv:char=K:char, it:char=K:char, $i$a$-forEach-TestKt$strChanger$1:int=0:int
// test.kt:19 strChanger: str:java.lang.String="OK":java.lang.String, pred:kotlin.jvm.functions.Function1=TestKt$box$1, result:java.lang.Object="OK":java.lang.String, $this$forEach$iv:java.lang.CharSequence="OK":java.lang.String, $i$f$forEach:int=0:int, element$iv:char=K:char, it:char=K:char, $i$a$-forEach-TestKt$strChanger$1:int=0:int
// _Strings.kt:1139 strChanger: str:java.lang.String="OK":java.lang.String, pred:kotlin.jvm.functions.Function1=TestKt$box$1, result:java.lang.Object="OK":java.lang.String, $this$forEach$iv:java.lang.CharSequence="OK":java.lang.String, $i$f$forEach:int=0:int
// _Strings.kt:1140 strChanger: str:java.lang.String="OK":java.lang.String, pred:kotlin.jvm.functions.Function1=TestKt$box$1, result:java.lang.Object="OK":java.lang.String, $this$forEach$iv:java.lang.CharSequence="OK":java.lang.String, $i$f$forEach:int=0:int
// test.kt:20 strChanger: str:java.lang.String="OK":java.lang.String, pred:kotlin.jvm.functions.Function1=TestKt$box$1, result:java.lang.Object="OK":java.lang.String
// test.kt:7 box: s:java.lang.String="OK":java.lang.String
// test.kt:10 box: s:java.lang.String="OK":java.lang.String