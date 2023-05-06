package com.kotdex.lib.builders.interaction.components.modal.components

import com.kotdex.lib.builders.IBuilder
import net.dv8tion.jda.api.interactions.components.text.TextInput
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle


@DslMarker
annotation class TextInputDslMarker

/**
 * A builder for creating text input fields.
 *
 * @property customId a unique identifier for the text input field
 * @property label a label to display alongside the text input field
 * @property style the style of the text input field
 */
@TextInputDslMarker
open class TextInputBuilder(
    protected val customId: String,
    protected val label: String,
    protected val style: TextInputStyle
) : IBuilder<TextInput> {
    var placeholder: String? = null
    var requiredLength: IntRange = 1..1

    /**
     * Builds and returns a new text input field.
     *
     * @return the newly created text input field
     */
    override fun build(): TextInput {
        return TextInput.create(customId, label, style)
            .setPlaceholder(placeholder)
            .setRequiredRange(requiredLength.first, requiredLength.last)
            .build()
    }
}

/**
 * Creates a new text input field using the specified custom ID, label, style, and additional configuration.
 *
 * Example usage:
 *
 * ```kotlin
 * val shortInput = textinput("custom_id", "Enter your name", TextInputStyle.SHORT) {
 *     maxLength = 20
 *     placeholder = "Type your name here"
 * }
 * ```
 *
 * @param customId a unique identifier for the text input field
 * @param label a label to display alongside the text input field
 * @param type the style of the text input field
 * @param block a lambda expression containing additional configuration for the builder
 * @return the newly created text input field
 *
 */
fun textinput(
    customId: String,
    label: String,
    type: TextInputStyle,
    block: TextInputBuilder.() -> Unit
): TextInput {
    return TextInputBuilder(customId, label, type).apply(block).build()
}