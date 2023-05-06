package com.kotdex.lib.builders.interaction.components

import net.dv8tion.jda.api.entities.emoji.Emoji
import net.dv8tion.jda.api.interactions.components.buttons.Button
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle

fun button(
    id: String,
    label: String?,
    emoji: Emoji?,
    style: ButtonStyle = ButtonStyle.SECONDARY,
    disabled: Boolean = false
): Button {
    return Button.of(style, id, label, emoji).withDisabled(disabled)
}

fun primary(id: String, label: String, emoji: Emoji? = null): Button {
    return button(id, label, emoji, ButtonStyle.PRIMARY)
}

fun secondary(id: String, label: String, emoji: Emoji? = null): Button {
    return button(id, label, emoji, ButtonStyle.SECONDARY)
}

fun success(id: String, label: String, emoji: Emoji? = null): Button {
    return button(id, label, emoji, ButtonStyle.SUCCESS)
}

fun danger(id: String, label: String, emoji: Emoji? = null): Button {
    return button(id, label, emoji, ButtonStyle.DANGER)
}

fun link(url: String, label: String, emoji: Emoji? = null): Button {
    return button(url, label, emoji, ButtonStyle.LINK)
}