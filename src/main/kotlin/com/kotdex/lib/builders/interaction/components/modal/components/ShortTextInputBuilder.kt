package com.kotdex.lib.builders.interaction.components.modal.components

import net.dv8tion.jda.api.interactions.components.text.TextInput
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle

/**
 * A builder for creating short text input fields.
 *
 * @property customId a unique identifier for the text input field
 * @property label a label to display alongside the text input field
 */
class ShortTextInputBuilder(
    customId: String,
    label: String
) : TextInputBuilder(customId, label, TextInputStyle.SHORT) {

    /**
     * Applies additional configuration to the short text input builder.
     *
     * @param block a lambda expression containing additional configuration for the builder
     * @return the modified builder instance
     */
    fun apply(block: ShortTextInputBuilder.() -> Unit): ShortTextInputBuilder {
        return this.apply(block)
    }
}

/**
 * Creates a new short text input field using the specified custom ID and label,
 * and applies additional configuration using the specified block.
 *
 * Example usage:
 *
 * ```kotlin
 * val shortInput = shorttextinput("custom_id", "Enter your name") {
 *     maxLength = 20
 *     placeholder = "Type your name here"
 * }
 * ```
 *
 * @param customId a unique identifier for the text input field
 * @param label a label to display alongside the text input field
 * @param block a lambda expression containing additional configuration for the builder
 * @return the newly created text input field
 *
 */
fun shorttextinput(
    customId: String,
    label: String,
    block: ShortTextInputBuilder.() -> Unit
): TextInput {
    return ShortTextInputBuilder(customId, label).apply(block).build()
}