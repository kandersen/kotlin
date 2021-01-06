

//FILE: test.kt

fun foo(block: () -> String): String {
    return block()
}

fun box(): String {
    return foo {
        "OK"
    }
}

// LINENUMBERS
// test.kt:10 box
// test.kt:6 foo
// test.kt:11 invoke
// test.kt:6 foo
// test.kt:10 box