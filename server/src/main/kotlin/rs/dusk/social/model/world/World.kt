package rs.dusk.social.model.world

/**
 * This class represents a world in the social system.
 *
 * @author Tyluur <contact@kiaira.tech>
 * @since May 07, 2020
 */
data class World(
	/**
	 * The id of the world
	 */
	val id : Int
) {
	
	/**
	 * The players in the world
	 */
	val players = mutableListOf<WorldPlayer>()
	
	/**
	 * Gets a [player][WorldPlayer] by their usernanme from [the list][players]
	 */
	fun getPlayerByUsername(username : String) : WorldPlayer? {
		return players.find { it.username == username }
	}
}