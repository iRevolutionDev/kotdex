package com.kotdex.lib.builders.messages

import com.kotdex.lib.builders.IBuilder
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.interactions.components.LayoutComponent
import net.dv8tion.jda.api.utils.FileUpload
import net.dv8tion.jda.api.utils.messages.MessageData

/**
 * Abstract class representing a builder for creating a message data object.
 *
 * @param T the type of message data that this builder creates
 */
abstract class MessageData<T : MessageData> : IBuilder<T> {
    var content: String? = null
    var embeds: List<MessageEmbed>? = null
    var files: List<FileUpload>? = null
    var components: List<LayoutComponent>? = null
    var mentions: List<Message.MentionType>? = null
    var tts: Boolean = false

    /**
     * Builds and returns the message data object created by this builder.
     *
     * @return the message data object created by this builder
     */
    abstract override fun build(): T
}
