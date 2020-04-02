package org.redrune.social.world

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
data class World(

    /**
     * The id of the world.
     */
    val id: Int,

    /**
     * The [WorldType] of the world
     */
    val type: WorldType

) {

    /**
     * If the server has been verified yet.
     * This is necessary for the processing of interactive packets.
     */
    val verified = false

    /**
     * The players on the world
     */
    val players = mutableListOf<WorldPlayer>()

    /**
     * The pair of the id and type used as a unique identifier
     */
    val pair = Pair(id, type)

}