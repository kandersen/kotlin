package nonprimitiveDependencies

fun main(args: Array<String>) {
    val a = A()
    //Breakpoint!
    args.size
}

class A {
    val a = 123
}

// EXPRESSION: a
// RESULT: instance of A

// EXPRESSION: a.a
// RESULT: 123: I
