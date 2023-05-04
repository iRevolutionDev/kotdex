package com.kotdex.lib.metadata

import com.kotdex.annotations.SimpleCommand
import com.kotdex.internal.reflection.handlers.SimpleCommandHandler

object DiscordMetadata {
    val simpleCommands: List<SimpleCommand>
        get() = SimpleCommandHandler.getCommands().values.map { it.first }
}