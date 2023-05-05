package com.kotdex.lib.builders.interaction.components

import net.dv8tion.jda.api.interactions.components.ActionRow
import net.dv8tion.jda.api.interactions.components.ItemComponent

fun row(vararg components: ItemComponent): ActionRow {
    return ActionRow.of(*components)
}

fun Collection<ItemComponent>.row() = row(*this.toTypedArray())

