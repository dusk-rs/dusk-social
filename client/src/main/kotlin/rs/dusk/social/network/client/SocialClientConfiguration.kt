package rs.dusk.social.network.client

/**
 * Configurations
 *
 * @author Tyluur <contact@kiaira.tech>
 * @since May 11, 2020
 */
class SocialClientConfiguration {
	
	/**
	 * The id of the world the social client is representing.
	 * Positive values between [0..300] only.
	 */
	var worldId : Int = 1
		set(value) {
			require(field in WORLD_ID_RANGE) { "Invalid worldId provided, it must be in range $WORLD_ID_RANGE " }
			field = value
		}
	
	companion object {
		
		/**
		 * The acceptable range of world id input
		 */
		private val WORLD_ID_RANGE = 1..300
		
	}
	
}