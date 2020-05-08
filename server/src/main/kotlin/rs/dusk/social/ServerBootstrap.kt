package rs.dusk.social

import rs.dusk.social.server.SocialServer

/**
 * Everything that should load before the network is listening
 */
fun preload() {

}

/**
 * Execution
 */
fun main() {
	val server = SocialServer()
	
	preload()
	server.preload()
	server.bind()
}