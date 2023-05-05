package com.kotdex.lib.builders.interaction.components.modal.components

import net.dv8tion.jda.api.interactions.components.text.TextInput
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle

class ShortTextInputBuilder(
    customId: String,
    label: String
) : TextInputBuilder(customId, label, TextInputStyle.SHORT)

fun shorttextinput(
    customId: String,
    label: String,
    block: ShortTextInputBuilder.() -> Unit
): TextInput {
    return ShortTextInputBuilder(customId, label).apply(block).build()
}