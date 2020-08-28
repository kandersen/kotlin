// FILE: test.kt
public fun <T> Iterable<T>.myforEach(operation: (T) -> Unit) : Unit {
    for (element in this) operation(element)
}

public fun println(v: Any?) {}

fun box() {
    (1..5).myforEach {
        println(it)
    }
}

// METHOD : ItInLambdaKt$foo1$1.invoke(I)V
// VARIABLE : NAME=this TYPE=LItInLambdaKt$foo1$1; INDEX=0
// VARIABLE : NAME=it TYPE=I INDEX=1

// IGNORE_BACKEND: JVM

// LOCAL VARIABLES
// TestKt:9:
// TestKt:3: $this$myforEach:java.lang.Iterable=kotlin.ranges.IntRange, operation:kotlin.jvm.functions.Function1=TestKt$box$1
// TestKt$box$1:10: it:int=1:int
// TestKt:6: v:java.lang.Object=java.lang.Integer
// TestKt$box$1:11: it:int=1:int
// TestKt$box$1.invoke(java.lang.Object)+11: p1:java.lang.Object=java.lang.Integer
// TestKt$box$1.invoke(java.lang.Object)+14: p1:java.lang.Object=java.lang.Integer
// TestKt:3: $this$myforEach:java.lang.Iterable=kotlin.ranges.IntRange, operation:kotlin.jvm.functions.Function1=TestKt$box$1, element:java.lang.Object=java.lang.Integer
// TestKt$box$1:10: it:int=2:int
// TestKt:6: v:java.lang.Object=java.lang.Integer
// TestKt$box$1:11: it:int=2:int
// TestKt$box$1.invoke(java.lang.Object)+11: p1:java.lang.Object=java.lang.Integer
// TestKt$box$1.invoke(java.lang.Object)+14: p1:java.lang.Object=java.lang.Integer
// TestKt:3: $this$myforEach:java.lang.Iterable=kotlin.ranges.IntRange, operation:kotlin.jvm.functions.Function1=TestKt$box$1, element:java.lang.Object=java.lang.Integer
// TestKt$box$1:10: it:int=3:int
// TestKt:6: v:java.lang.Object=java.lang.Integer
// TestKt$box$1:11: it:int=3:int
// TestKt$box$1.invoke(java.lang.Object)+11: p1:java.lang.Object=java.lang.Integer
// TestKt$box$1.invoke(java.lang.Object)+14: p1:java.lang.Object=java.lang.Integer
// TestKt:3: $this$myforEach:java.lang.Iterable=kotlin.ranges.IntRange, operation:kotlin.jvm.functions.Function1=TestKt$box$1, element:java.lang.Object=java.lang.Integer
// TestKt$box$1:10: it:int=4:int
// TestKt:6: v:java.lang.Object=java.lang.Integer
// TestKt$box$1:11: it:int=4:int
// TestKt$box$1.invoke(java.lang.Object)+11: p1:java.lang.Object=java.lang.Integer
// TestKt$box$1.invoke(java.lang.Object)+14: p1:java.lang.Object=java.lang.Integer
// TestKt:3: $this$myforEach:java.lang.Iterable=kotlin.ranges.IntRange, operation:kotlin.jvm.functions.Function1=TestKt$box$1, element:java.lang.Object=java.lang.Integer
// TestKt$box$1:10: it:int=5:int
// TestKt:6: v:java.lang.Object=java.lang.Integer
// TestKt$box$1:11: it:int=5:int
// TestKt$box$1.invoke(java.lang.Object)+11: p1:java.lang.Object=java.lang.Integer
// TestKt$box$1.invoke(java.lang.Object)+14: p1:java.lang.Object=java.lang.Integer
// TestKt:3: $this$myforEach:java.lang.Iterable=kotlin.ranges.IntRange, operation:kotlin.jvm.functions.Function1=TestKt$box$1, element:java.lang.Object=java.lang.Integer
// TestKt:4: $this$myforEach:java.lang.Iterable=kotlin.ranges.IntRange, operation:kotlin.jvm.functions.Function1=TestKt$box$1
// TestKt:12: