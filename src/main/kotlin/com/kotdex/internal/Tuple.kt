package com.kotdex.internal

class Tuple<A, B>(val a: A, val b: B) {
    override fun toString(): String {
        return "Tuple(a=$a, b=$b)"
    }
}