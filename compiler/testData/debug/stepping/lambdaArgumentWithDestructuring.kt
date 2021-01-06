

//FILE: test.kt

fun foo(block: (Pair<Char,Char>) -> String): String {
    return block('O' to 'K')
}

fun box(): String {
    return foo { (O, K) ->
        "$O$K"
    }
}

// LINENUMBERS
// test.kt:10 box
// test.kt:6 foo
// LINENUMBERS JVM_IR
// test.kt:10 invoke
// LINENUMBERS
// test.kt:11 invoke
// test.kt:6 foo
// test.kt:10 box