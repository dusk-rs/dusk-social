package rs.dusk.social.network.client

import com.github.michaelbull.logging.InlineLogger
import org.koin.dsl.module
import rs.dusk.core.network.NetworkClient
import rs.dusk.core.network.codec.CodecRepository
import rs.dusk.core.network.codec.message.MessageReader
import rs.dusk.core.network.codec.message.decode.OpcodeMessageDecoder
import rs.dusk.core.network.codec.message.encode.GenericMessageEncoder
import rs.dusk.core.network.codec.packet.decode.SimplePacketDecoder
import rs.dusk.core.network.connection.ConnectionPipeline
import rs.dusk.core.network.connection.ConnectionSettings
import rs.dusk.core.network.connection.event.ChannelEventChain
import rs.dusk.core.network.connection.event.ChannelEventListener
import rs.dusk.core.network.connection.event.ChannelEventType.ACTIVE
import rs.dusk.core.network.connection.event.ChannelEventType.INACTIVE
import rs.dusk.core.network.connection.event.type.ReestablishmentEvent
import rs.dusk.core.network.model.session.setSession
import rs.dusk.social.network.codec.handshake.HandshakeCodec
import rs.dusk.social.network.connection.event.HandshakeInitializationEvent
import rs.dusk.social.network.client.session.HandshakeSession
import rs.dusk.social.utility.inject

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 07, 2020
 */
class SocialClient(
	
	/**
	 * The [connection settings][ConnectionSettings] of the client
	 */
	private val connectionSettings : ConnectionSettings,
	
	/**
	 * The [client settings][SocialClientSettings] of the client
	 */
	private val clientSettings : SocialClientSettings

) : NetworkClient(connectionSettings) {
	
	private val logger = InlineLogger()
	
	fun start() {
		val factory = SocialClientFactory()
		val repository : CodecRepository by inject()
		
		val chain = ChannelEventChain().apply {
			append(ACTIVE, HandshakeInitializationEvent())
			append(INACTIVE, ReestablishmentEvent(this@SocialClient, limit = 10, delay = 1000))
		}
		
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
		
		factory.connect(this, chain, pipeline)
	}
	
	override fun onConnect() {
		logger.info { "Successfully connected to server, to register world ${clientSettings.worldId}" }
	}
	
	override fun onDisconnect() {
		logger.info { "Disconnected from server" }
	}
}

val codecRepositoryModule = module {
	single { CodecRepository() }
}