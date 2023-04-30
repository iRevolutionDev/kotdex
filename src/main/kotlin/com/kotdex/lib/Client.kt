package com.kotdex.lib

import com.kotdex.internal.ClientOptions
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class Client(options: ClientOptions.() -> Unit) {
    var jdaBuilder: JDABuilder? = null

    init {
        val clientOptions = ClientOptions().apply(options)
        jdaBuilder = JDABuilder.create(clientOptions.intents)
            .enableCache(clientOptions.caches)
            .setChunkingFilter(clientOptions.chunkingFilter)
            .setLargeThreshold(clientOptions.largeThreshold)
    }

    inline fun <reified Event : GenericEvent> on(crossinline callback: (event: Event) -> Unit) {
        jdaBuilder?.addEventListeners(object : ListenerAdapter() {
            override fun onGenericEvent(event: GenericEvent) {
                if (event !is Event) return

                callback(event)
            }
        })
    }

    inline fun <reified Event : GenericEvent> once(crossinline callback: (event: Event) -> Unit) {
        jdaBuilder?.addEventListeners(object : ListenerAdapter() {
            override fun onGenericEvent(event: GenericEvent) {
                if (event !is Event) return

                callback(event)
                jdaBuilder?.removeEventListeners(this)
            }
        })
    }

    fun login(token: String) {
        jdaBuilder?.setToken(token)?.build()
    }
}