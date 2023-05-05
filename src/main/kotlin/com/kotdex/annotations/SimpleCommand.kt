package com.kotdex.annotations

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SimpleCommand(
    val name: String,
    val description: String,
    val aliases: Array<String> = [],
    val usage: String = "",
)
