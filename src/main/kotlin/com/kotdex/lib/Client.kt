package com.kotdex.lib

import com.kotdex.internal.ClientOptions
import com.kotdex.internal.proxies.SLF4J
import com.kotdex.internal.reflection.handlers.SimpleCommandHandler
import com.kotdex.lib.commands.SimpleCommandResult
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.Interaction

class Client(options: ClientOptions.() -> Unit) {
    private val jdaBuilder: JDABuilder
    private var clientOptions: ClientOptions
    lateinit var jda: JDA

    private val logger by SLF4J

    init {
        clientOptions = ClientOptions().apply(options)
        jdaBuilder = JDABuilder.create(clientOptions.intents)
            .enableCache(clientOptions.caches)
            .setChunkingFilter(clientOptions.chunkingFilter)
            .setLargeThreshold(clientOptions.largeThreshold)
    }

    inline fun <reified Event : GenericEvent> on(crossinline callback: (event: Event) -> Unit) {
        jda.addEventListener(object : ListenerAdapter() {
            override fun onGenericEvent(event: GenericEvent) {
                if (event !is Event) return

                callback(event)
            }
        })
    }

    inline fun <reified Event : GenericEvent> once(crossinline callback: (event: Event) -> Unit) {
        jda.addEventListener(object : ListenerAdapter() {
            override fun onGenericEvent(event: GenericEvent) {
                if (event !is Event) return

                callback(event)
                jda.removeEventListener(this)
            }
        })
    }

    fun initApplicationCommands() {
        TODO()
    }

    fun executeInteraction(interaction: Interaction) {
        TODO()
    }

    private fun parseCommand(
        prefix: String,
        message: Message
    ): Pair<(Message, List<String>) -> Unit, SimpleCommandResult> {
        val content = message.contentRaw
        val command = content.removePrefix(prefix).split(" ")[0]

        if (!content.startsWith(prefix)) return Pair({ _, _ -> }, SimpleCommandResult.INVALID_PREFIX)

        if (command.isEmpty()) {
            return Pair({ _, _ -> }, SimpleCommandResult.NOT_COMMAND)
        }

        if (!SimpleCommandHandler.exists(command)) {
            return Pair({ _, _ -> }, SimpleCommandResult.NOT_FOUND)
        }

        val commandPair = SimpleCommandHandler.getCommand(command)
        val commandMethod = commandPair.second

        return Pair(commandMethod, SimpleCommandResult.SUCCESS)
    }

    fun executeCommand(message: Message) {
        val prefix = clientOptions.commandOptions.prefix
        val (commandMethod, result) = parseCommand(prefix, message)
        val args = message.contentRaw.removePrefix(prefix).split(" ").drop(1)

        if (message.author.isBot) return

        if (result == SimpleCommandResult.NOT_COMMAND) {
            message.reply("Not a command").queue()
            return
        }

        if (result == SimpleCommandResult.NOT_FOUND) {
            message.reply(clientOptions.commandOptions.notFoundMessage).queue()
            return
        }

        try {
            commandMethod(message, args)
        } catch (e: Exception) {
            logger.error("An error occurred while executing the command", e)

            if (!clientOptions.commandOptions.showErrors) return
            message.reply("An error occurred while executing the command").queue()
            message.reply("```${e.message ?: "No message provided"}: ${e.cause?.message ?: "No cause provided"}```")
                .queue()
        }
    }

    fun login(token: String) {
        jda = jdaBuilder
            .setToken(token)
            .build()
            .awaitReady()
    }
}