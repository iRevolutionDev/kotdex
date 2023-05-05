package com.kotdex.lib.builders.interaction.components.modal.components

import net.dv8tion.jda.api.interactions.components.text.TextInput
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle

class ParagraphTextInputBuilder(
    customId: String,
    label: String
) : TextInputBuilder(customId, label, TextInputStyle.PARAGRAPH)

fun paragraphtextinput(
    customId: String,
    label: String,
    block: ParagraphTextInputBuilder.() -> Unit
): TextInput {
    return ParagraphTextInputBuilder(customId, label).apply(block).build()
}