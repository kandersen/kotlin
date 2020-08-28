//FILE: test.kt

// WITH_RUNTIME

fun box() {
    val map: Map<String, String> = mapOf("1" to "23")
    for ((a, b)
        in map) {
        a + b
    }
}

// LOCAL VARIABLES
// TestKt:6:
// TestKt:8: map:java.util.Map=java.util.Collections$SingletonMap
// TestKt:7: map:java.util.Map=java.util.Collections$SingletonMap

// LOCAL VARIABLES JVM_IR
// TestKt:8: map:java.util.Map=java.util.Collections$SingletonMap, a:java.lang.String="1":java.lang.String
// TestKt:7: map:java.util.Map=java.util.Collections$SingletonMap, a:java.lang.String="1":java.lang.String

// LOCAL VARIABLES
// TestKt:9: map:java.util.Map=java.util.Collections$SingletonMap, a:java.lang.String="1":java.lang.String, b:java.lang.String="23":java.lang.String

// LOCAL VARIABLES JVM
// TestKt:7: map:java.util.Map=java.util.Collections$SingletonMap
// LOCAL VARIABLES JVM_IR
// TestKt:8: map:java.util.Map=java.util.Collections$SingletonMap

// LOCAL VARIABLES
// TestKt:11: map:java.util.Map=java.util.Collections$SingletonMap
