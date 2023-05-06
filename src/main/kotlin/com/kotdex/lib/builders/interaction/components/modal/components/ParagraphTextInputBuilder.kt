package com.kotdex.lib.builders.interaction.components.modal.components

import net.dv8tion.jda.api.interactions.components.text.TextInput
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle

/**
 * A builder for creating paragraph text input fields.
 *
 * @property customId a unique identifier for the text input field
 * @property label a label to display alongside the text input field
 */
class ParagraphTextInputBuilder(
    customId: String,
    label: String
) : TextInputBuilder(customId, label, TextInputStyle.PARAGRAPH) {

    /**
     * Applies additional configuration to the paragraph text input builder.
     *
     * @param block a lambda expression containing additional configuration for the builder
     * @return the modified builder instance
     */
    fun apply(block: ParagraphTextInputBuilder.() -> Unit): ParagraphTextInputBuilder {
        return this.apply(block)
    }
}

/**
 * Creates a new paragraph text input field using the specified custom ID and label,
 * and applies additional configuration using the specified block.
 *
 * Example usage:
 *
 * ```kotlin
 * val paragraphInput = paragraphtextinput("custom_id", "Enter your feedback") {
 *     maxLength = 500
 *     placeholder = "Type your feedback here"
 * }
 * ```
 *
 * @param customId a unique identifier for the text input field
 * @param label a label to display alongside the text input field
 * @param block a lambda expression containing additional configuration for the builder
 * @return the newly created text input field
 *
 */
fun paragraphtextinput(
    customId: String,
    label: String,
    block: ParagraphTextInputBuilder.() -> Unit
): TextInput {
    return ParagraphTextInputBuilder(customId, label).apply(block).build()
}
