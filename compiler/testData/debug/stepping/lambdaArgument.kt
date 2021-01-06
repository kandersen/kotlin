

//FILE: test.kt

fun foo(block: (String) -> String): String {
    return block("OK")
}

fun box(): String {
    return foo { _ ->
        "OK"
    }
}

// LINENUMBERS
// test.kt:10 box
// test.kt:6 foo
// test.kt:11 invoke
// test.kt:6 foo
// test.kt:10 box