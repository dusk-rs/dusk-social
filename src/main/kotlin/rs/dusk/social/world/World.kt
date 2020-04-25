package rs.dusk.social.world

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
     * The players on the world
     */
    val players = mutableListOf<WorldPlayer>()

    /**
     * The pair of the id and type used as a unique identifier
     */
    val pair = Pair(id, type)

}