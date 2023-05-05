package com.kotdex.lib.builders.interaction.components.modal

import com.kotdex.lib.builders.IBuilder
import com.kotdex.lib.builders.interaction.components.modal.components.TextInputBuilder
import com.kotdex.lib.builders.interaction.components.row
import net.dv8tion.jda.api.interactions.components.text.TextInput
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle
import net.dv8tion.jda.api.interactions.modals.Modal

class ModalBuilder(
    var customId: String,
    var label: String
) : IBuilder<Modal> {
    private val textInputs: MutableList<TextInput> = mutableListOf()

    fun textinput(
        customId: String,
        label: String,
        type: TextInputStyle,
        block: TextInputBuilder.() -> Unit
    ) {
        val textInput = TextInputBuilder(customId, label, type).apply(block).build()

        textInputs.add(textInput)
    }

    fun shorttextinput(
        customId: String,
        label: String,
        block: TextInputBuilder.() -> Unit
    ) {
        textinput(customId, label, TextInputStyle.SHORT, block)
    }

    fun paragraphtextinput(
        customId: String,
        label: String,
        block: TextInputBuilder.() -> Unit
    ) {
        textinput(customId, label, TextInputStyle.PARAGRAPH, block)
    }

    override fun build(): Modal {
        val rows = textInputs.map { textInput -> row(textInput) }.toTypedArray()

        return Modal.create(customId, label)
            .addComponents(*rows)
            .build()
    }
}

fun modal(
    customId: String,
    label: String,
    block: ModalBuilder.() -> Unit
): Modal {
    return ModalBuilder(customId, label).apply(block).build()
}