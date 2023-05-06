package com.kotdex.lib.builders.interaction.components

import com.kotdex.lib.builders.IBuilder
import net.dv8tion.jda.api.entities.emoji.Emoji
import net.dv8tion.jda.api.interactions.components.selections.SelectOption
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu

/**
 * Builder for creating a [StringSelectMenu] component for use in a Discord bot application.
 *
 * @param customId The custom ID for the select menu.
 */
class StringSelectMenuBuilder(private val customId: String = "") : IBuilder<StringSelectMenu> {
    var placeholder: String? = null
    var requiredRange: IntRange = 1..1
    var defaultValue: String = ""
    private val options: MutableList<SelectOption> = mutableListOf()

    /**
     * Adds a new option to the select menu with a label and value.
     *
     * @param label The label to display for the option.
     * @param value The value to be returned when the option is selected.
     * @param default Whether this option should be selected by default.
     */
    fun option(
        label: String,
        value: String,
        default: Boolean = false
    ) {
        option(label, value, null, null, default)
    }

    /**
     * Adds a new option to the select menu with a label, value and emoji.
     *
     * @param label The label to display for the option.
     * @param value The value to be returned when the option is selected.
     * @param emoji The emoji to display for the option.
     * @param default Whether this option should be selected by default.
     */
    fun option(
        label: String,
        value: String,
        emoji: Emoji,
        default: Boolean = false
    ) {
        option(label, value, null, emoji, default)
    }

    /**
     * Adds a new option to the select menu with a label, value and description.
     *
     * @param label The label to display for the option.
     * @param value The value to be returned when the option is selected.
     * @param description The description to display for the option.
     * @param default Whether this option should be selected by default.
     */
    fun option(
        label: String,
        value: String,
        description: String,
        default: Boolean = false
    ) {
        option(label, value, description, null, default)
    }

    /**
     * Adds a new option to the select menu with a label, value, description and emoji.
     *
     * @param label The label to display for the option.
     * @param value The value to be returned when the option is selected.
     * @param description The description to display for the option.
     * @param emoji The emoji to display for the option.
     * @param default Whether this option should be selected by default.
     */
    fun option(
        label: String,
        value: String,
        description: String?,
        emoji: Emoji?,
        default: Boolean = false
    ) {
        val option = SelectOption.of(label, value)
            .withDescription(description)
            .withDefault(default)
            .withEmoji(emoji)

        options.add(option)
    }

    override fun build(): StringSelectMenu {
        return StringSelectMenu.create(customId)
            .setPlaceholder(placeholder)
            .setRequiredRange(requiredRange.first, requiredRange.last)
            .addOptions(*options.toTypedArray())
            .setDefaultValues(defaultValue)
            .build()
    }

}

/**
 * Creates a [StringSelectMenu] with the given custom ID and options.
 * The placeholder, required range and default value can also be set using the builder methods.
 *
 * Example usage:
 * ```
 * val selectMenu = stringselectmenu("myCustomId") {
 *     placeholder = "Select an option"
 *     requiredRange = 1..2
 *     defaultValue = "option2"
 *     option("Option 1", "option1")
 *     option("Option 2", "option2", true)
 *     option("Option 3", "option3", "This is option 3")
 *     option("Option 4", "option4", "This is option 4", Emoji.fromUnicode("🌟"))
 * }
 * ```
 */
fun stringselectmenu(customId: String, block: StringSelectMenuBuilder.() -> Unit): StringSelectMenu {
    return StringSelectMenuBuilder(customId).apply(block).build()
}
