package com.kotdex.internal.reflection

import com.kotdex.annotations.Discord
import com.kotdex.internal.proxies.SLF4J
import org.reflections.Reflections

val logger by SLF4J("Importer")

fun import(packagePath: String) {
    val reflections = Reflections(packagePath)

    val classes = reflections.getTypesAnnotatedWith(Discord::class.java)
    ReflectionUtils.discordClasses.addAll(classes)

    logger.debug("Imported ${classes.size} classes from $packagePath")
}