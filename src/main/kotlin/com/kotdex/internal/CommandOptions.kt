package com.kotdex.internal

data class CommandOptions(
    var prefix: String = "!",
    var showErrors: Boolean = true,
    var notFoundMessage: String = "Command not found"
)