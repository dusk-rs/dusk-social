package rs.dusk.social.model.world

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 07, 2020
 */
data class WorldPlayer(
	/**
	 * The username this player is identifiable by
	 */
	val username: String,
	
	/**
	 * The display name this player is identifiable by
	 */
	val displayName: String,
	
	/**
	 * The rights that this player is identifiable by
	 */
	val rights: Int) {
	
}