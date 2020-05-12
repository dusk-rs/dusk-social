package rs.dusk

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import org.koin.core.context.startKoin
import org.koin.logger.slf4jLogger
import rs.dusk.core.network.connection.ConnectionSettings
import rs.dusk.social.network.client.SocialClient
import rs.dusk.social.network.client.SocialClientSettings
import rs.dusk.social.network.client.codecRepositoryModule
import rs.dusk.social.utility.constant.SocialConstants

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 07, 2020
 */
class ClientBootstrap : CliktCommand() {
	
	/**
	 * The id of the world the client is representing
	 */
	private val worldId by option("-worldId", help = "Sets the id of the world to boot").int().default(0)
	
	override fun run() {
		val connectionSettings = ConnectionSettings(
			host = "127.0.0.1",
			port = SocialConstants.SOCIAL_PORT_ID
		)
		
		// the settings of the client
		val settings = SocialClientSettings(worldId)
		
		// composing the client
		val client = SocialClient(connectionSettings = connectionSettings, clientSettings = settings)
		
		preload()
		client.start()
	}
}

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
fun main(args : Array<String>) = ClientBootstrap().main(args)