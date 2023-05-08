package com.kotdex.lib.builders

import com.kotdex.lib.theme.Theme
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import java.awt.Color
import java.time.temporal.TemporalAccessor

@DslMarker
annotation class EmbedDslMarker

@EmbedDslMarker
data class EmbedAuthorInfo(
    var name: String? = null,
    var url: String? = null,
    var iconUrl: String? = null
)

@EmbedDslMarker
data class EmbedFieldInfo(
    var name: String,
    var value: String,
    var inline: Boolean = false
)

@EmbedDslMarker
class EmbedBuilder(
    var title: String,
    var description: String,
    var color: Color = Color.decode(Theme.success),
    var thumbnailUrl: String? = null,
    var imageUrl: String? = null,
    var timestamp: TemporalAccessor? = null
) : IBuilder<MessageEmbed> {
    private val embedBuilder = EmbedBuilder()

    fun author(block: EmbedAuthorInfo.() -> Unit) {
        val authorInfo = EmbedAuthorInfo().apply(block)

        embedBuilder.setAuthor(authorInfo.name, authorInfo.url, authorInfo.iconUrl)
    }

    fun field(block: EmbedFieldInfo.() -> Unit) {
        val fieldInfo = EmbedFieldInfo("", "").apply(block)

        embedBuilder.addField(fieldInfo.name, fieldInfo.value, fieldInfo.inline)
    }

    fun footer(text: String, iconUrl: String? = null) {
        embedBuilder.setFooter(text, iconUrl)
    }

    override fun build(): MessageEmbed {
        return embedBuilder.run {
            setTitle(title)
            setDescription(description)
            setColor(color)
            setThumbnail(thumbnailUrl)
            setImage(imageUrl)
            setTimestamp(timestamp)
        }.build()
    }
}

fun Embed(block: com.kotdex.lib.builders.EmbedBuilder.() -> Unit): MessageEmbed {
    return EmbedBuilder("", "").apply(block).build()
}