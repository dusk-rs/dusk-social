package rs.dusk

import org.koin.core.context.startKoin
import org.koin.logger.slf4jLogger
import rs.dusk.core.network.codec.CodecRepository
import rs.dusk.core.network.connection.ConnectionSettings
import rs.dusk.social.network.server.SocialServer
import rs.dusk.social.network.server.codecRepositoryModule
import rs.dusk.social.network.server.socialServerFactory
import rs.dusk.social.repositoryModule
import rs.dusk.social.utility.constant.SocialConstants.SOCIAL_PORT_ID
import rs.dusk.social.utility.get

class SocialServerBootstrap {
	
	private val server = SocialServer(
		ConnectionSettings(
			host = "127.0.0.1",
			port = SOCIAL_PORT_ID
		)
	)
	
	/**
	 * Everything that should load before the network is listening
	 */
	fun preload() {
		startKoin {
			slf4jLogger()
			modules(codecRepositoryModule, repositoryModule, socialServerFactory)
		}
		val repository : CodecRepository = get()
		repository.registerAll()
	}
	
	fun run() {
		preload()
		server.start()
	}
	
}

fun main() {
	SocialServerBootstrap().run()
}