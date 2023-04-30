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
    var intents: List<GatewayIntent> = listOf()
    fun intents(block: List<GatewayIntent>.() -> Unit) {
        intents.apply(block)
    }

    var caches: List<CacheFlag> = listOf()
    fun caches(block: List<CacheFlag>.() -> Unit) {
        caches.apply(block)
    }
}