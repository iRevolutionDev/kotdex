package com.kotdex.lib.builders.interaction.components.modal.components

import com.kotdex.lib.builders.IBuilder
import net.dv8tion.jda.api.interactions.components.text.TextInput
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle

open class TextInputBuilder(
    protected val customId: String,
    protected val label: String,
    protected val style: TextInputStyle
) : IBuilder<TextInput> {
    var placeholder: String? = null
    var requiredLength: IntRange = 1..1

    override fun build(): TextInput {
        return TextInput.create(customId, label, style)
            .setPlaceholder(placeholder)
            .setRequiredRange(requiredLength.first, requiredLength.last)
            .build()
    }
}

fun textinput(
    customId: String,
    label: String,
    type: TextInputStyle,
    block: TextInputBuilder.() -> Unit
): TextInput {
    return TextInputBuilder(customId, label, type).apply(block).build()
}