package rs.dusk.social.server

import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.logger.slf4jLogger
import rs.dusk.core.network.NetworkServer
import rs.dusk.core.network.codec.CodecRepository
import rs.dusk.core.network.codec.message.MessageReader
import rs.dusk.core.network.connection.ConnectionPipeline
import rs.dusk.core.network.connection.ConnectionSettings
import rs.dusk.core.network.connection.event.ConnectionEventChain
import rs.dusk.core.network.connection.event.ConnectionEventListener
import rs.dusk.social.server.codec.handshake.HandshakeCodec
import rs.dusk.social.utility.SocialConstants.SOCIAL_PORT_ID
import rs.dusk.social.utility.inject

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 07, 2020
 */
class SocialServer(
	settings : ConnectionSettings = ConnectionSettings(
		host = "127.0.0.1",
		port = SOCIAL_PORT_ID
	)
) : NetworkServer(settings) {
	
	fun preload() {
		startKoin {
			slf4jLogger()
			modules(codecRepositoryModule)
		}
		
	}
	
	fun bind() {
		val factory = SocialServerFactory()
		val chain = ConnectionEventChain()
		val repository : CodecRepository by inject()
		
		val pipeline = ConnectionPipeline {
			it.addLast("message.handler", MessageReader(repository.get(HandshakeCodec::class)))
			it.addLast("connection.listener", ConnectionEventListener(chain))
		}
		
		factory.bind(this, chain, pipeline)
	}
	
}

val codecRepositoryModule = module {
	single { CodecRepository() }
}