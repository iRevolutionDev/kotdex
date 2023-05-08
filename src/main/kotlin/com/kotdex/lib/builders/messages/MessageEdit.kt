package com.kotdex.lib.builders.messages

import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder
import net.dv8tion.jda.api.utils.messages.MessageEditData

/**
 * This class is a builder for creating Discord message edit data to be sent through the Discord API using the JDA library.
 * It extends the abstract class MessageDataBuilder and implements the build() function to create an instance of MessageEditData.
 */
class MessageEdit : MessageData<MessageEditData>() {

    /**
     * This function builds the MessageEditData instance using the builder pattern.
     * It creates a new MessageCreateBuilder object and sets the content, embeds, files, components, mentions, and TTS (text-to-speech) properties.
     * It then builds a MessageCreateData instance using the MessageCreateBuilder object and converts it to a MessageEditData instance.
     *
     * @return The MessageEditData instance built by this builder.
     */
    override fun build(): MessageEditData {
        val builder = MessageCreateBuilder()

        content?.let { builder.setContent(content) }
        embeds?.let { builder.setEmbeds(*embeds!!.toTypedArray()) }
        files?.let { builder.setFiles(*files!!.toTypedArray()) }
        components?.let { builder.setComponents(*components!!.toTypedArray()) }
        mentions?.let { builder.setAllowedMentions(mentions) }
        builder.setTTS(tts)

        val messageCreateData = builder.build()
        return MessageEditData.fromCreateData(messageCreateData)
    }
}

/**
 * This function creates and returns a MessageEditData instance using the builder pattern.
 * It takes a block of code to configure the MessageEditBuilder object.
 *
 * @param block A lambda function that takes a MessageEditBuilder object and configures it.
 * @return The MessageEditData instance built by the MessageEditBuilder object.
 */
fun MessageEdit(block: MessageEdit.() -> Unit): MessageEditData {
    return MessageEdit().apply(block).build()
}
