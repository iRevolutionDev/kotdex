package com.kotdex.lib

import com.kotdex.internal.JDAOptions
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

fun jda(token: String, options: JDAOptions.() -> Unit): JDA {
    val jdaBuilderOptions = JDAOptions().apply(options)
    val jdaBuilder = JDABuilder.create(jdaBuilderOptions.intents)
        .enableCache(jdaBuilderOptions.caches)
        .setChunkingFilter(jdaBuilderOptions.chunkingFilter)
        .setLargeThreshold(jdaBuilderOptions.largeThreshold)
        .setToken(token)

    return jdaBuilder.build().awaitReady()
}

inline fun <reified Event : GenericEvent> JDA.on(crossinline callback: (context: Event) -> Unit) {
    addEventListener(object : ListenerAdapter() {
        override fun onGenericEvent(event: GenericEvent) {
            if (event !is Event) return

            callback(event)
        }
    })
}

inline fun <reified Event : GenericEvent> JDA.once(crossinline callback: (context: Event) -> Unit) {
    addEventListener(object : ListenerAdapter() {
        override fun onGenericEvent(event: GenericEvent) {
            if (event !is Event) return

            callback(event)
            removeEventListener(this)
        }
    })
}