package com.kotdex.internal.reflection.handlers

import com.kotdex.annotations.SimpleCommand
import com.kotdex.internal.Tuple
import com.kotdex.internal.reflection.ReflectionUtils
import java.lang.reflect.Method

object SimpleCommandHandler {
    fun getCommandByName(name: String): SimpleCommand? {
        ReflectionUtils.discordClasses.forEach { discordClass ->
            discordClass.declaredMethods.forEach { method ->
                val simpleCommand = method.getAnnotation(SimpleCommand::class.java) ?: return null

                if (simpleCommand.name == name) return simpleCommand
            }
        }
        return null
    }

    fun getCommandMethodByName(name: String): Tuple<out Method?, out Any?> {
        ReflectionUtils.discordClasses.forEach { discordClass ->
            discordClass.declaredMethods.forEach { method ->
                val simpleCommand = method.getAnnotation(SimpleCommand::class.java) ?: return Tuple(method, null)

                if (simpleCommand.name == name) return Tuple(method, discordClass.getConstructor().newInstance())
            }
        }
        return Tuple(null, null)
    }

    fun commandExists(name: String): Boolean {
        return getCommandByName(name) != null
    }
}