package com.kotdex.annotations

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SlashCommand(
    val name: String,
    val description: String,
    val guilds: Array<String> = []
)
