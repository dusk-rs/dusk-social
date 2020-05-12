package rs.dusk.social

import org.koin.core.context.startKoin
import org.koin.logger.slf4jLogger
import rs.dusk.social.server.SocialServer
import rs.dusk.social.server.codecRepositoryModule

/**
 * Everything that should load before the network is listening
 */
fun preload() {
	startKoin {
		slf4jLogger()
		modules(codecRepositoryModule)
	}
}

/**
 * Execution
 */
fun main() = with(SocialServer()) {
	preload()
	start()
}