package com.kotdex.internal

data class CommandOptions(
    var prefix: String = "!",
    var notFoundMessage: String = "Command not found"
)