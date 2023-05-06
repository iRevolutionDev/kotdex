package com.kotdex.lib.builders.interaction.components.modal

import com.kotdex.lib.builders.IBuilder
import com.kotdex.lib.builders.interaction.components.modal.components.TextInputBuilder
import com.kotdex.lib.builders.interaction.components.row
import net.dv8tion.jda.api.interactions.components.text.TextInput
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle
import net.dv8tion.jda.api.interactions.modals.Modal

@DslMarker
annotation class ModalDslBuilder

/**
 * A builder used to create a [Modal] object.
 *
 * @property customId the unique identifier for the modal.
 * @property label the title of the modal.
 */

@ModalDslBuilder
class ModalBuilder(
    var customId: String,
    var label: String
) : IBuilder<Modal> {
    private val textInputs: MutableList<TextInput> = mutableListOf()

    /**
     * Adds a [TextInput] object to the modal.
     *
     * @param customId the unique identifier for the text input.
     * @param label the label for the text input.
     * @param type the [TextInputStyle] for the text input.
     * @param block a lambda expression used to configure the [TextInputBuilder].
     */
    fun textinput(
        customId: String,
        label: String,
        type: TextInputStyle,
        block: TextInputBuilder.() -> Unit
    ) {
        val textInput = TextInputBuilder(customId, label, type).apply(block).build()

        textInputs.add(textInput)
    }

    /**
     * Adds a short [TextInput] object to the modal.
     *
     * @param customId the unique identifier for the text input.
     * @param label the label for the text input.
     * @param block a lambda expression used to configure the [TextInputBuilder].
     */
    fun shorttextinput(
        customId: String,
        label: String,
        block: TextInputBuilder.() -> Unit
    ) {
        textinput(customId, label, TextInputStyle.SHORT, block)
    }

    /**
     * Adds a paragraph [TextInput] object to the modal.
     *
     * @param customId the unique identifier for the text input.
     * @param label the label for the text input.
     * @param block a lambda expression used to configure the [TextInputBuilder].
     */
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

/**
 * Creates a [ModalBuilder] object with the specified [customId] and [label].
 *
 * Example usage:
 * ```kotlin
 *  val myModal = modal("modal1", "My Modal") {
 *     shorttextinput("name", "Name") {
 *         setPlaceholder("Enter your name")
 *     }
 *     paragraphtextinput("desc", "Description") {
 *         setPlaceholder("Enter a description")
 *     }
 * }
 * ```
 *
 * @param customId the unique identifier for the modal.
 * @param label the title of the modal.
 * @param block a lambda expression used to configure the [ModalBuilder].
 * @return the created [Modal] object.
 */
fun modal(
    customId: String,
    label: String,
    block: ModalBuilder.() -> Unit
): Modal {
    return ModalBuilder(customId, label).apply(block).build()
}

val modal = modal("a", "tem nao") {
    textinput("a", "b", TextInputStyle.PARAGRAPH) {

    }
}