package rs.dusk

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import org.koin.core.context.startKoin
import org.koin.logger.slf4jLogger
import rs.dusk.core.network.connection.ConnectionSettings
import rs.dusk.social.network.client.SocialClient
import rs.dusk.social.network.client.codecRepositoryModule
import rs.dusk.social.utility.constant.SocialConstants.SOCIAL_PORT_ID

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 07, 2020
 */
class SocialClientBootstrap : CliktCommand() {
	
	/**
	 * The id of the world the client is representing, parsed from arguments
	 */
	private val worldId by option("-worldId", help = "Sets the id of the world to boot").int().default(0)
	
	/**
	 * The settings to use for creating a connection
	 */
	private val settings = ConnectionSettings(
		host = "127.0.0.1",
		port = SOCIAL_PORT_ID
	)
	
	/**
	 * The [social client][SocialClient] instance
	 */
	private val client = SocialClient(settings)
	
	/**
	 * Everything that should load before the network is listening
	 */
	private fun preload() {
		client.configuration.worldId = worldId
		
		startKoin {
			slf4jLogger()
			modules(codecRepositoryModule)
		}
	}
	
	override fun run() {
		preload()
		client.start()
	}
}

/**
 * Execution
 */
fun main(args : Array<String>) = SocialClientBootstrap().main(args)