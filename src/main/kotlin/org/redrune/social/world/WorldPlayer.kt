package org.redrune.social.world

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
data class WorldPlayer(

    /**
     * The name of the player
     */
    val name: String,

    /**
     * The world the player is connected to
     */
    val worldId: Int
)