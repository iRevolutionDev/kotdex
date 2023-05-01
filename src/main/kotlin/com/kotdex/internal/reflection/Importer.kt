package com.kotdex.internal.reflection

import com.kotdex.annotations.Discord
import org.reflections.Reflections

fun import(packagePath: String) {
    val reflections = Reflections(packagePath)

    val classes = reflections.getTypesAnnotatedWith(Discord::class.java)
    ReflectionUtils.discordClasses.addAll(classes)
}