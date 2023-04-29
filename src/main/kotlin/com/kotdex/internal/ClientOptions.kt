package com.kotdex.internal

import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.ChunkingFilter
import net.dv8tion.jda.api.utils.MemberCachePolicy
import net.dv8tion.jda.api.utils.cache.CacheFlag

class ClientOptions(
    var prefix: String = "!",
    var chunkingFilter: ChunkingFilter = ChunkingFilter.NONE,
    var largeThreshold: Int = 250,
    var policy: MemberCachePolicy = MemberCachePolicy.DEFAULT
) {
    var intents: MutableList<GatewayIntent> = mutableListOf()
    fun intents(block: MutableList<GatewayIntent>.() -> Unit) {
        intents.apply(block)
    }

    var caches: MutableList<CacheFlag> = mutableListOf()
    fun caches(block: MutableList<CacheFlag>.() -> Unit) {
        caches.apply(block)
    }
}