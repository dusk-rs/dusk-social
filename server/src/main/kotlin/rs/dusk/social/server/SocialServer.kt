package rs.dusk.social.server

import com.github.michaelbull.logging.InlineLogger
import org.koin.dsl.module
import rs.dusk.core.network.NetworkServer
import rs.dusk.core.network.codec.CodecRepository
import rs.dusk.core.network.codec.message.MessageReader
import rs.dusk.core.network.codec.message.decode.OpcodeMessageDecoder
import rs.dusk.core.network.codec.message.encode.GenericMessageEncoder
import rs.dusk.core.network.codec.packet.decode.SimplePacketDecoder
import rs.dusk.core.network.connection.ConnectionPipeline
import rs.dusk.core.network.connection.ConnectionSettings
import rs.dusk.core.network.connection.event.ChannelEventChain
import rs.dusk.core.network.connection.event.ChannelEventListener
import rs.dusk.core.network.model.session.setSession
import rs.dusk.social.network.codec.handshake.HandshakeCodec
import rs.dusk.social.network.client.session.HandshakeSession
import rs.dusk.social.utility.constant.SocialConstants.SOCIAL_PORT_ID
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
	
	private val logger = InlineLogger()
	
	fun start() {
		val factory = SocialServerFactory()
		val chain = ChannelEventChain().apply {
		
		}
		val repository : CodecRepository by inject()
		
		val pipeline = ConnectionPipeline {
			val channel = it.channel()
			val codec = HandshakeCodec::class
			
			it.addLast("packet.decoder", SimplePacketDecoder(repository.get(codec)))
			it.addLast("message.decoder", OpcodeMessageDecoder(repository.get(codec)))
			it.addLast("message.reader", MessageReader(repository.get(codec)))
			it.addLast("message.encoder", GenericMessageEncoder(repository.get(codec)))
			it.addLast("channel.listener", ChannelEventListener(chain))
			
			channel.setSession(HandshakeSession(channel))
		}
		
		factory.bind(this, chain, pipeline)
	}
	
	override fun onConnect() {
		logger.info { "Connected to client" }
	}
	
	override fun onDisconnect() {
		logger.info { "Disconnected from a client" }
	}
}

val codecRepositoryModule = module {
	single { CodecRepository() }
}