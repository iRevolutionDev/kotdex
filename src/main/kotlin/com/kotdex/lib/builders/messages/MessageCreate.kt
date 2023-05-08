package com.kotdex.lib.builders.messages

import net.dv8tion.jda.api.utils.messages.MessageCreateData

/**
 * This class is a builder for creating Discord message data to be sent through the Discord API using the JDA library.
 * It extends the abstract class MessageDataBuilder and implements the build() function to create an instance of MessageCreateData.
 */
class MessageCreate : MessageData<MessageCreateData>() {

    /**
     * This function builds the MessageCreateData instance using the builder pattern.
     * It creates a new MessageCreateBuilder object and sets the content, embeds, files, components, mentions, and TTS (text-to-speech) properties.
     * It then returns the built MessageCreateData instance.
     *
     * @return The MessageCreateData instance built by this builder.
     */
    override fun build(): MessageCreateData {
        val builder = net.dv8tion.jda.api.utils.messages.MessageCreateBuilder()

        content?.let { builder.setContent(content) }
        embeds?.let { builder.setEmbeds(*embeds!!.toTypedArray()) }
        files?.let { builder.setFiles(*files!!.toTypedArray()) }
        components?.let { builder.setComponents(*components!!.toTypedArray()) }
        mentions?.let { builder.setAllowedMentions(mentions) }
        builder.setTTS(tts)

        return builder.build()
    }
}

/**
 * This function creates and returns a MessageCreateData instance using the builder pattern.
 * It takes a block of code to configure the MessageCreateBuilder object.
 *
 * @param block A lambda function that takes a MessageCreateBuilder object and configures it.
 * @return The MessageCreateData instance built by the MessageCreateBuilder object.
 */
fun MessageCreate(block: MessageCreate.() -> Unit): MessageCreateData {
    return MessageCreate().apply(block).build()
}
