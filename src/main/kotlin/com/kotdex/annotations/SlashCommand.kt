package com.kotdex.annotations

annotation class SlashCommand(
    val name: String,
    val description: String,
    val guilds: Array<String> = []
)
