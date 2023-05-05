package com.kotdex.lib.builders.interaction.components

import com.kotdex.lib.builders.IBuilder
import net.dv8tion.jda.api.entities.emoji.Emoji
import net.dv8tion.jda.api.interactions.components.selections.SelectOption
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu

class StringSelectMenuBuilder(private val customId: String = "") : IBuilder<StringSelectMenu> {
    var placeholder: String? = null
    var requiredRange: IntRange = 1..1
    var defaultValue: String = ""
    private val options: MutableList<SelectOption> = mutableListOf()

    fun option(
        label: String,
        value: String,
        default: Boolean = false
    ) {
        option(label, value, null, null, default)
    }

    fun option(
        label: String,
        value: String,
        emoji: Emoji,
        default: Boolean = false
    ) {
        option(label, value, null, emoji, default)
    }

    fun option(
        label: String,
        value: String,
        description: String,
        default: Boolean = false
    ) {
        option(label, value, description, null, default)
    }

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

fun stringselectmenu(customId: String, block: StringSelectMenuBuilder.() -> Unit): StringSelectMenu {
    return StringSelectMenuBuilder(customId).apply(block).build()
}