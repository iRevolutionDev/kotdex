package com.kotdex

import com.kotdex.internal.ClientOptions
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class Client(clientOptions: ClientOptions.() -> Unit) {
    var jdaBuilder: JDABuilder? = null

    init {
        val options = ClientOptions().apply(clientOptions)
        jdaBuilder = JDABuilder.create(options.intents)
            .enableCache(options.caches)
            .setChunkingFilter(options.chunkingFilter)
            .setLargeThreshold(options.largeThreshold)
    }

    inline fun <reified Event : GenericEvent> on(crossinline callback: (event: Event) -> Unit) {
        jdaBuilder?.addEventListeners(object : ListenerAdapter() {
            override fun onGenericEvent(event: GenericEvent) {
                if (event is Event) {
                    callback(event)
                }
            }
        })
    }

    inline fun <reified Event : GenericEvent> once(crossinline callback: (event: Event) -> Unit) {
        jdaBuilder?.addEventListeners(object : ListenerAdapter() {
            override fun onGenericEvent(event: GenericEvent) {
                if (event is Event) {
                    callback(event)
                    jdaBuilder?.removeEventListeners(this)
                }
            }
        })
    }

    fun login(token: String) {
        jdaBuilder?.setToken(token)?.build()
    }
}