package com.kotdex.internal.reflection.handlers

import com.kotdex.annotations.SimpleCommand
import com.kotdex.internal.proxies.SLF4J
import net.dv8tion.jda.api.entities.Message

object SimpleCommandHandler {
    private val commands = mutableMapOf<String, Pair<SimpleCommand, (message: Message) -> Unit>>()

    private val logger by SLF4J

    fun registerCommand(obj: Class<*>) {
        val methods = obj.declaredMethods.filter {
            it.isAnnotationPresent(SimpleCommand::class.java)
        }

        methods.forEach { method ->
            val annotation = method.getAnnotation(SimpleCommand::class.java)
            val name = annotation.name

            if (commands.containsKey(name)) {
                throw IllegalArgumentException("Command with name $name already exists")
            }

            commands[name] = Pair(annotation) { message: Message ->
                method.invoke(obj.getConstructor().newInstance(), message)
            }

            logger.info("Registered command $name")
        }
    }

    fun exists(name: String): Boolean {
        return commands.containsKey(name) || commands.filter { it.value.first.aliases.contains(name) }.isNotEmpty()
    }

    fun getCommand(name: String): Pair<SimpleCommand, (message: Message) -> Unit> {
        return commands[name] ?: commands.filter { it.value.first.aliases.contains(name) }.values.first()
    }

    fun getCommands(): Map<String, Pair<SimpleCommand, (message: Message) -> Unit>> {
        return commands
    }
}