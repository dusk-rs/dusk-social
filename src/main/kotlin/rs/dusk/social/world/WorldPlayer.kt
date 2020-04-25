package rs.dusk.social.world

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
     * The world pair details of the world the player is connected to
     */
    val worldPair: Pair<Int, WorldType>
)