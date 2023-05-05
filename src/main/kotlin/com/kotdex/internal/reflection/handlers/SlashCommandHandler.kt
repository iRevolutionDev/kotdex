package com.kotdex.internal.reflection.handlers

import com.kotdex.annotations.Option
import com.kotdex.annotations.SlashCommand
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction

object SlashCommandHandler {
    private val commands = mutableMapOf<String, Pair<SlashCommand, (SlashCommandInteraction, Array<out Any>) -> Unit>>()
    private val options = mutableMapOf<String, Option>()

    fun registerCommand(obj: Class<*>) {
        val methods = obj.declaredMethods.filter {
            it.isAnnotationPresent(SlashCommand::class.java)
        }

        methods.forEach { method ->
            val annotation = method.getAnnotation(SlashCommand::class.java)
            val name = annotation.name

            if (commands.containsKey(name)) {
                throw IllegalArgumentException("Command with name $name already exists")
            }

            commands[name] = Pair(annotation) { interaction, args ->
                method.invoke(obj.getConstructor().newInstance(), interaction, *args)
            }

            method.parameters.forEach { parameter ->
                if (parameter.isAnnotationPresent(Option::class.java)) {
                    val option = parameter.getAnnotation(Option::class.java)
                    options[name + option.name] = option
                }
            }
        }
    }

    fun exists(name: String): Boolean {
        return commands.containsKey(name)
    }

    fun getCommand(name: String): Pair<SlashCommand, (SlashCommandInteraction, Array<out Any>) -> Unit> {
        return commands[name] ?: throw IllegalArgumentException("Command with name $name does not exist")
    }

    fun getOptionsByCommand(name: String): List<Option> {
        return options.filter { it.key.startsWith(name) }.map { it.value }
    }

    fun getCommands(): Map<String, Pair<SlashCommand, (SlashCommandInteraction, Array<out Any>) -> Unit>> {
        return commands
    }
}