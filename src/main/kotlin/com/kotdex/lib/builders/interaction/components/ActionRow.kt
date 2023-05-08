package com.kotdex.lib.builders.interaction.components

import net.dv8tion.jda.api.interactions.components.ActionRow
import net.dv8tion.jda.api.interactions.components.ItemComponent

/**
 * Creates an [ActionRow] containing the provided [components].
 *
 * Example usage:
 *
 * ```kotlin
 * val button1 = primary("button1", "Click me")
 * val button2 = secondary("button2", "No, click me!")
 * val actionRow = row(button1, button2)
 * ```
 *
 * @param components the [ItemComponent] objects to add to the row.
 * @return the created [ActionRow] object.
 */
fun row(vararg components: ItemComponent): ActionRow {
    return ActionRow.of(*components)
}

/**
 * Creates an [ActionRow] containing the [ItemComponent] objects in this collection.
 *
 * Example usage:
 *
 * ```kotlin
 * val buttons = listOf(
 *     primary("button1", "Button 1"),
 *     secondary("button2", "Button 2"),
 *     success("button3", "Button 3")
 * ).row()
 * ```
 *
 * @return the created [ActionRow] object.
 */
fun Collection<ItemComponent>.row() = row(*this.toTypedArray())


