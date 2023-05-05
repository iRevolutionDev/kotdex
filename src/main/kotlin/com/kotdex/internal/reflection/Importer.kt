package com.kotdex.internal.reflection

import com.kotdex.annotations.Discord
import com.kotdex.internal.proxies.SLF4J
import com.kotdex.internal.reflection.handlers.SimpleCommandHandler
import com.kotdex.internal.reflection.handlers.SlashCommandHandler
import org.reflections.Reflections

val logger by SLF4J("Importer")

fun import(packagePath: String) {
    val reflections = Reflections(packagePath)

    val classes = reflections.getTypesAnnotatedWith(Discord::class.java)

    // Setup handlers
    classes.forEach {
        SlashCommandHandler.registerCommand(it)
        SimpleCommandHandler.registerCommand(it)
    }

    ReflectionUtils.discordClasses.addAll(classes)
}