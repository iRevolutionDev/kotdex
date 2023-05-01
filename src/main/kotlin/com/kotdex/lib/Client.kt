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

    fun parseCommand(prefix: String, message: Message): SimpleCommandResult {
        val prefixRegex = Regex("^$prefix")
        val content = message.contentRaw

        val isCommand = prefixRegex.containsMatchIn(content)
        if (!isCommand) return SimpleCommandResult.NOT_COMMAND

        val command = content.replace(prefixRegex, "").split(" ")[0]

        if (command.isEmpty()) return SimpleCommandResult.NOT_COMMAND

        return if (SimpleCommandHandler.commandExists(command)) SimpleCommandResult.SUCCESS else SimpleCommandResult.NOT_FOUND
    }

    fun executeCommand(message: Message) {
        val logger by SLF4J

        val command = parseCommand(clientOptions.commandOptions.prefix, message)
        if (command == SimpleCommandResult.NOT_COMMAND) return

        if (command == SimpleCommandResult.NOT_FOUND) {
            message.channel.sendMessage(clientOptions.commandOptions.notFoundMessage).queue()
        }

        val commandName = message.contentRaw.replace(Regex("^${clientOptions.commandOptions.prefix}"), "").split(" ")[0]

        val simpleCommand = SimpleCommandHandler.getCommandMethodByName(commandName)

        try {
            simpleCommand.a?.invoke(simpleCommand.b, message)
        } catch (e: Exception) {
            logger.error(e.message)
            message.channel.sendMessage("An error occurred while executing the command.").queue()
        }
    }

    fun login(token: String) {
        jda = jdaBuilder
            .setToken(token)
            .build()
            .awaitReady()
    }
}