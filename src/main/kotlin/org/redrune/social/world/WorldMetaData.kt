package org.redrune.social.world

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
data class WorldMetaData(

    /**
     * The id of the world.
     * When paired with the [type], this id represents a unique combination
     */
    val id: Int,

    /**
     * The [WorldType] of the world
     */
    val type: WorldType,

    /**
     * The players on the world
     */
    val players: MutableList<WorldPlayer>

)