package rs.dusk.network.client

import com.github.michaelbull.logging.InlineLogger
import com.google.common.base.Stopwatch
import org.koin.core.context.startKoin
import rs.dusk.core.network.codec.message.decode.OpcodeMessageDecoder
import rs.dusk.core.network.codec.message.encode.GenericMessageEncoder
import rs.dusk.core.network.codec.message.handle.NetworkMessageHandler
import rs.dusk.core.network.codec.packet.access.PacketBuilder
import rs.dusk.core.network.codec.packet.decode.SimplePacketDecoder
import rs.dusk.core.network.connection.ConnectionPipeline
import rs.dusk.core.network.connection.ConnectionSettings
import rs.dusk.core.network.connection.ReconnectionAdapter
import rs.dusk.core.network.connection.client.NetworkClient
import rs.dusk.core.tools.function.NetworkUtils.Companion.loadCodecs
import rs.dusk.network.client.codec.handshake.SocialClientHandshakeCodec
import rs.dusk.network.client.codec.handshake.SocialClientHandshakeSession
import rs.dusk.network.client.codec.identification.SocialClientIdentificationCodec
import rs.dusk.network.client.codec.relay.SocialClientRelayCodec
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
        val pipeline = ConnectionPipeline {
            val session = SocialClientHandshakeSession(it.channel())

            it.addLast("packet.decoder", SimplePacketDecoder(SocialClientHandshakeCodec))
            it.addLast("message.decoder", OpcodeMessageDecoder(SocialClientHandshakeCodec))
            it.addLast(
                "message.handler",
                NetworkMessageHandler(
                    SocialClientHandshakeCodec,
                    SocialClientConnectionEventHandler(session)
                )
            )
            it.addLast(
                "message.encoder",

                GenericMessageEncoder(SocialClientHandshakeCodec, PacketBuilder(sized = true))
            )
            it.addLast("connection.listener", ReconnectionAdapter(client, 10_000L, 5))
        }
        loadCodecs(SocialClientHandshakeCodec, SocialClientIdentificationCodec, SocialClientRelayCodec)
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

fun main(args: Array<String>) {
    try {
        val id = args[0].toInt()
        val type = WorldType.valueOf(args[1])

        val client = SocialClient()
        client.run()

        val manager: SocialClientManager by inject()

        manager.worldId = id
        manager.worldType = type
    } catch (e: Exception) {
        System.err.println("Unable to configure the client, ensure parameters configured appropriately [id, type] (e.g. 1 LOBBY)")
        e.printStackTrace()
    }
}