package rs.dusk.social.network.client

import com.github.michaelbull.logging.InlineLogger
import org.koin.dsl.module
import rs.dusk.core.network.NetworkClient
import rs.dusk.core.network.codec.CodecRepository
import rs.dusk.core.network.codec.message.MessageReader
import rs.dusk.core.network.codec.message.decode.OpcodeMessageDecoder
import rs.dusk.core.network.codec.message.encode.GenericMessageEncoder
import rs.dusk.core.network.codec.packet.access.PacketBuilder
import rs.dusk.core.network.codec.packet.decode.SimplePacketDecoder
import rs.dusk.core.network.codec.setCodec
import rs.dusk.core.network.connection.ConnectionPipeline
import rs.dusk.core.network.connection.ConnectionSettings
import rs.dusk.core.network.connection.event.ChannelEventChain
import rs.dusk.core.network.connection.event.ChannelEventListener
import rs.dusk.core.network.connection.event.ChannelEventType.*
import rs.dusk.core.network.connection.event.type.ChannelExceptionEvent
import rs.dusk.core.network.connection.event.type.ReestablishmentEvent
import rs.dusk.core.network.model.session.setSession
import rs.dusk.core.network.setClient
import rs.dusk.social.network.session.HandshakeSession
import rs.dusk.social.network.codec.handshake.HandshakeCodec
import rs.dusk.social.network.connection.event.HandshakeInitializationEvent
import rs.dusk.social.utility.inject

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 07, 2020
 */
class SocialClient(
	
	/**
	 * The [connection settings][ConnectionSettings] of the client
	 */
	settings : ConnectionSettings

) : NetworkClient(settings) {
	
	private val logger = InlineLogger()
	
	/**
	 * The configuration settings of the client
	 */
	val configuration = SocialClientConfiguration()
	
	fun start() {
		val factory = SocialClientFactory()
		val repository : CodecRepository by inject()
		
		val chain = ChannelEventChain().apply {
			append(ACTIVE, HandshakeInitializationEvent())
			append(INACTIVE, ReestablishmentEvent(this@SocialClient, limit = 10, delay = 1000))
			append(EXCEPTION, ChannelExceptionEvent())
		}
		
		val pipeline = ConnectionPipeline {
			val channel = it.channel()
			
			it.addLast("packet.decoder", SimplePacketDecoder())
			it.addLast("message.decoder", OpcodeMessageDecoder())
			it.addLast("message.reader", MessageReader())
			it.addLast("message.encoder", GenericMessageEncoder(builder = PacketBuilder(sized = true)))
			it.addLast("channel.listener", ChannelEventListener(chain))
			
			channel.setClient(this)
			channel.setCodec(repository.get(HandshakeCodec::class))
			channel.setSession(HandshakeSession(channel))
		}
		
		factory.connect(this, chain, pipeline)
	}
	
	override fun onConnect() {
		logger.info { "Successfully connected to server, to register world ${configuration.worldId}" }
	}
	
	override fun onDisconnect() {
		logger.info { "Disconnected from server" }
	}
}

val codecRepositoryModule = module {
	single { CodecRepository() }
}