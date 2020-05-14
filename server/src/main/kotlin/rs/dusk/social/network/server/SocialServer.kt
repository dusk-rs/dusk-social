package rs.dusk.social.network.server

import com.github.michaelbull.logging.InlineLogger
import org.koin.dsl.module
import rs.dusk.core.network.NetworkServer
import rs.dusk.core.network.codec.CodecRepository
import rs.dusk.core.network.codec.message.MessageReader
import rs.dusk.core.network.codec.message.decode.OpcodeMessageDecoder
import rs.dusk.core.network.codec.message.encode.GenericMessageEncoder
import rs.dusk.core.network.codec.packet.decode.SimplePacketDecoder
import rs.dusk.core.network.codec.setCodec
import rs.dusk.core.network.connection.ConnectionPipeline
import rs.dusk.core.network.connection.ConnectionSettings
import rs.dusk.core.network.connection.event.ChannelEventChain
import rs.dusk.core.network.connection.event.ChannelEventListener
import rs.dusk.core.network.connection.event.ChannelEventType.EXCEPTION
import rs.dusk.core.network.connection.event.type.ChannelExceptionEvent
import rs.dusk.core.network.model.session.setSession
import rs.dusk.social.network.session.HandshakeSession
import rs.dusk.social.network.codec.handshake.HandshakeCodec
import rs.dusk.social.utility.inject

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 07, 2020
 */
class SocialServer(
	settings : ConnectionSettings
) : NetworkServer(settings) {
	
	private val logger = InlineLogger()
	
	fun start() {
		val factory = SocialServerFactory()
		val chain = ChannelEventChain().apply {
			append(EXCEPTION, ChannelExceptionEvent())
		}
		val repository : CodecRepository by inject()
		
		val pipeline = ConnectionPipeline {
			val channel = it.channel()
			
			it.addLast("packet.decoder", SimplePacketDecoder())
			it.addLast("message.decoder", OpcodeMessageDecoder())
			it.addLast("message.reader", MessageReader())
			it.addLast("message.encoder", GenericMessageEncoder())
			it.addLast("channel.listener", ChannelEventListener(chain))
			
			channel.setCodec(repository.get(HandshakeCodec::class))
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