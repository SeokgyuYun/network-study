package kr.hs.dgsw

open class Person {
    constructor(name: String) {
        println("[Person] name: $name")
    }
    constructor(name: String, age: Int) {
        println("[Person] name: $name, $age")
    }
}

class Developer : Person {
    constructor(name: String) : this(name, 10) {
        println("[Developer] $name")
    }
    constructor(name: String, age: Int) : super(name, age) {
        println("[Developer] $name, $age")
    }
}

fun main() {
    val sean = Developer("Sean")
}