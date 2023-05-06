package com.kotdex.lib.builders.interaction.components

import com.kotdex.lib.builders.IBuilder
import net.dv8tion.jda.api.interactions.components.selections.EntitySelectMenu
import net.dv8tion.jda.api.interactions.components.selections.EntitySelectMenu.SelectTarget

@DslMarker
annotation class EntitySelectMenuDslMarker

/**
 * A builder for creating [EntitySelectMenu]s.
 *
 * @property customId The custom ID to use for the menu.
 * @property type The select target to use for the menu.
 */
@EntitySelectMenuDslMarker
class EntitySelectMenuBuilder(
    private val customId: String,
    private val type: SelectTarget
) : IBuilder<EntitySelectMenu> {
    var placeholder: String? = null
    var requiredRange: IntRange = 1..1

    /**
     * Builds an [EntitySelectMenu] with the properties set on this builder.
     */
    override fun build(): EntitySelectMenu {
        return EntitySelectMenu.create(customId, type)
            .setPlaceholder(placeholder)
            .setRequiredRange(requiredRange.first, requiredRange.last)
            .build()
    }
}

/**
 * Creates an [EntitySelectMenu] with the given custom ID and select target.
 * The placeholder and required range can also be set using the builder methods.
 *
 * Example usage:
 *
 * ```kotlin
 * val selectMenu = entityselectmenu("menu-1", SelectTarget.USERS) {
 *     placeholder = "Select a user"
 *     requiredRange = 1..3
 * }
 * ```
 *
 * @param customId The custom ID to use for the menu.
 * @param type The select target to use for the menu.
 * @param block A lambda that takes an [EntitySelectMenuBuilder] as its receiver, allowing
 * the builder methods to be called to set additional properties for the menu.
 * @return The created [EntitySelectMenu].
 */
fun entityselectmenu(
    customId: String,
    type: SelectTarget,
    block: EntitySelectMenuBuilder.() -> Unit
): EntitySelectMenu {
    return EntitySelectMenuBuilder(customId, type).apply(block).build()
}
