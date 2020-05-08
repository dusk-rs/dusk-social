package rs.dusk.social

import rs.dusk.social.client.SocialClient
import rs.dusk.social.client.SocialClientFactory

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 07, 2020
 */
class ClientBootstrap {


}

/**
 * Everything that should load before the network is listening
 */
fun preload() {

}

/**
 * Execution
 */
fun main() {
	val server = SocialClient()
	
	preload()
	server.preload()
	server.start()
}