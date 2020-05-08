package rs.dusk.social.client

import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.logger.slf4jLogger
import rs.dusk.core.network.NetworkClient
import rs.dusk.core.network.codec.CodecRepository
import rs.dusk.core.network.codec.message.MessageReader
import rs.dusk.core.network.connection.ConnectionPipeline
import rs.dusk.core.network.connection.ConnectionSettings
import rs.dusk.core.network.connection.event.ConnectionEventChain
import rs.dusk.core.network.connection.event.ConnectionEventListener
import rs.dusk.social.client.codec.handshake.HandshakeCodec
import rs.dusk.social.utility.SocialConstants.SOCIAL_PORT_ID
import rs.dusk.social.utility.inject

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 07, 2020
 */
class SocialClient(
	settings : ConnectionSettings = ConnectionSettings(
		host = "127.0.0.1",
		port = SOCIAL_PORT_ID
	)
) : NetworkClient(settings) {
	
	fun preload() {
		startKoin {
			slf4jLogger()
			modules(codecRepositoryModule)
		}
//		val repository : CodecRepository by inject()
//		repository.registerAll()
	
	
	}
	
	fun start() {
		val factory = SocialClientFactory()
		val chain = ConnectionEventChain()
		val repository : CodecRepository by inject()
		
		val pipeline = ConnectionPipeline {
			it.addLast("message.handler", MessageReader(repository.get(HandshakeCodec::class)))
			it.addLast("connection.listener", ConnectionEventListener(chain))
		}
		
		factory.connect(this, chain, pipeline)
	}
}

val codecRepositoryModule = module {
	single { CodecRepository() }
}
