package rs.dusk.network.client

import com.github.michaelbull.logging.InlineLogger
import com.google.common.base.Stopwatch
import org.koin.core.context.startKoin
import rs.dusk.core.network.codec.CodecRepository
import rs.dusk.core.network.codec.message.MessageReader
import rs.dusk.core.network.codec.message.decode.OpcodeMessageDecoder
import rs.dusk.core.network.codec.message.encode.GenericMessageEncoder
import rs.dusk.core.network.codec.packet.access.PacketBuilder
import rs.dusk.core.network.codec.packet.decode.SimplePacketDecoder
import rs.dusk.core.network.connection.ConnectionPipeline
import rs.dusk.core.network.connection.ConnectionSettings
import rs.dusk.core.network.connection.client.NetworkClient
import rs.dusk.core.network.connection.event.ConnectionEventChain
import rs.dusk.core.network.connection.event.ConnectionEventListener
import rs.dusk.core.network.connection.event.ConnectionEventType
import rs.dusk.core.network.connection.event.type.ReestablishmentEvent
import rs.dusk.network.client.codec.handshake.SocialClientHandshakeCodec
import rs.dusk.network.client.codec.handshake.SocialClientHandshakeSession
import rs.dusk.social.client.SocialClientManager
import rs.dusk.social.client.clientManagerModule
import rs.dusk.social.world.WorldType
import rs.dusk.utility.SocialConstants.SOCIAL_PORT_ID
import rs.dusk.utility.inject
import java.util.concurrent.TimeUnit

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 31, 2020
 */
class SocialClient {
	
	private val logger = InlineLogger()
	
	private fun connect() {
		val settings = ConnectionSettings("127.0.0.1", SOCIAL_PORT_ID)
		val client = NetworkClient(settings)
		val chain = ConnectionEventChain().apply {
			append(ConnectionEventType.DEREGISTER, ReestablishmentEvent(client, limit = 5, delay = 10))
		}
		val pipeline = ConnectionPipeline {
			val session = SocialClientHandshakeSession(it.channel())
			
			val codec = SocialClientHandshakeCodec()
			
			it.addLast("packet.decoder", SimplePacketDecoder(codec))
			it.addLast("message.decoder", OpcodeMessageDecoder(codec))
			it.addLast("message.reader", MessageReader(codec))
			it.addLast(
				"message.encoder",
				GenericMessageEncoder(codec, PacketBuilder(sized = true))
			)
			it.addLast("connection.listener", ConnectionEventListener(chain))
		}
		CodecRepository.registerAll()
		client.configure(pipeline)
		client.start()
	}
	
	fun preload() {
		startKoin {
			modules(clientManagerModule)
		}
	}
	
	fun run() {
		val watch = Stopwatch.createStarted()
		preload()
		connect()
		logger.info { "Social client started in ${watch.elapsed(TimeUnit.MILLISECONDS)}ms" }
	}
	
}

fun main(args : Array<String>) {
	try {
		val id = args[0].toInt()
		val type = WorldType.valueOf(args[1])
		
		val client = SocialClient()
		client.run()
		
		val manager : SocialClientManager by inject()
		
		manager.worldId = id
		manager.worldType = type
	} catch (e : Exception) {
		System.err.println("Unable to configure the client, ensure parameters configured appropriately [id, type] (e.g. 1 LOBBY)")
		e.printStackTrace()
	}
}