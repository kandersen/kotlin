

//FILE: test.kt

fun foo(block: (Char) -> String): String {
    return block('O')
}

fun box(): String {
    return foo { O ->
        "${O}K"
    }
}

// LINENUMBERS
// test.kt:10 box
// test.kt:6 foo
// LINENUMBERS JVM_IR
// test.kt:11 invoke
// test.kt:6 foo
// LINENUMBERS
// test.kt:10 box