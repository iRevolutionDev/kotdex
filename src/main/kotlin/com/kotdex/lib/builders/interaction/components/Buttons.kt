package com.kotdex.lib.builders.interaction.components

import net.dv8tion.jda.api.entities.emoji.Emoji
import net.dv8tion.jda.api.interactions.components.buttons.Button
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle

/**
 * Returns a new [Button] instance with the provided properties.
 *
 * @param id the unique identifier of the button
 * @param label the label text of the button
 * @param emoji the emoji icon to display next to the label
 * @param style the visual style of the button
 * @param disabled whether the button is disabled or not
 * @return a new [Button] instance
 */
fun button(
    id: String,
    label: String?,
    emoji: Emoji? = null,
    style: ButtonStyle = ButtonStyle.SECONDARY,
    disabled: Boolean = false
): Button {
    return Button.of(style, id, label, emoji).withDisabled(disabled)
}

/**
 * Returns a new primary [Button] instance with the provided properties.
 *
 * @param id the unique identifier of the button
 * @param label the label text of the button
 * @param emoji the emoji icon to display next to the label
 * @return a new primary [Button] instance
 */
fun primary(id: String, label: String, emoji: Emoji? = null): Button {
    return button(id, label, emoji, ButtonStyle.PRIMARY)
}

/**
 * Returns a new secondary [Button] instance with the provided properties.
 *
 * @param id the unique identifier of the button
 * @param label the label text of the button
 * @param emoji the emoji icon to display next to the label
 * @return a new secondary [Button] instance
 */
fun secondary(id: String, label: String, emoji: Emoji? = null): Button {
    return button(id, label, emoji, ButtonStyle.SECONDARY)
}

/**
 * Returns a new success [Button] instance with the provided properties.
 *
 * @param id the unique identifier of the button
 * @param label the label text of the button
 * @param emoji the emoji icon to display next to the label
 * @return a new success [Button] instance
 */
fun success(id: String, label: String, emoji: Emoji? = null): Button {
    return button(id, label, emoji, ButtonStyle.SUCCESS)
}

/**
 * Returns a new danger [Button] instance with the provided properties.
 *
 * @param id the unique identifier of the button
 * @param label the label text of the button
 * @param emoji the emoji icon to display next to the label
 * @return a new danger [Button] instance
 */
fun danger(id: String, label: String, emoji: Emoji? = null): Button {
    return button(id, label, emoji, ButtonStyle.DANGER)
}

/**
 * Returns a new link [Button] instance with the provided properties.
 *
 * @param url the URL to link to when the button is clicked
 * @param label the label text of the button
 * @param emoji the emoji icon to display next to the label
 * @return a new link [Button] instance
 */
fun link(url: String, label: String, emoji: Emoji? = null): Button {
    return button(url, label, emoji, ButtonStyle.LINK)
}
