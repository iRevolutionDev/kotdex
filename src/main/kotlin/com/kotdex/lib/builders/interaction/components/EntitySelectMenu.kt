package com.kotdex.lib.builders.interaction.components

import com.kotdex.lib.builders.IBuilder
import net.dv8tion.jda.api.interactions.components.selections.EntitySelectMenu
import net.dv8tion.jda.api.interactions.components.selections.EntitySelectMenu.SelectTarget

class EntitySelectMenuBuilder(
    private val customId: String,
    private val type: SelectTarget
) : IBuilder<EntitySelectMenu> {
    var placeholder: String? = null
    var requiredRange: IntRange = 1..1

    override fun build(): EntitySelectMenu {
        return EntitySelectMenu.create(customId, type)
            .setPlaceholder(placeholder)
            .setRequiredRange(requiredRange.first, requiredRange.last)
            .build()
    }
}

fun entityselectmenu(
    customId: String,
    type: SelectTarget,
    block: EntitySelectMenuBuilder.() -> Unit
): EntitySelectMenu {
    return EntitySelectMenuBuilder(customId, type).apply(block).build()
}