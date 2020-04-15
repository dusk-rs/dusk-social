package rs.dusk.network.client

import com.github.michaelbull.logging.InlineLogger
import com.google.common.base.Stopwatch
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
import rs.dusk.network.ClientNetworkEventHandler
import rs.dusk.network.client.codec.handshake.SocialClientHandshakeCodec
import rs.dusk.network.client.codec.handshake.SocialClientHandshakeSession
import rs.dusk.network.client.codec.identification.SocialClientIdentificationCodec
import rs.dusk.social.world.WorldType
import rs.dusk.utility.SocialConstants.SOCIAL_PORT_ID
import java.util.concurrent.TimeUnit

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 31, 2020
 */
class SocialClient(val id: Int, val type: WorldType) {

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
                    ClientNetworkEventHandler(session)
                )
            )
            it.addLast(
                "message.encoder",
                GenericMessageEncoder(SocialClientHandshakeCodec, PacketBuilder(sized = true))
            )
            it.addLast("connection.listener", ReconnectionAdapter(client, 10_000L, 5))
        }
        loadCodecs(SocialClientHandshakeCodec, SocialClientIdentificationCodec)
        client.configure(pipeline)
        client.start()
    }

    fun run() {
        val watch = Stopwatch.createStarted()
        connect()
        logger.info { "Social client started in ${watch.elapsed(TimeUnit.MILLISECONDS)}ms" }
    }

}

fun main(args: Array<String>) {
    try {
        val id = args[0].toInt()
        val type = WorldType.valueOf(args[1])
        val client = SocialClient(id, type)
        client.run()
    } catch (e: Exception) {
        System.err.println("Unable to configure the client, ensure parameters configured appropriately [id, type] (e.g. 1 LOBBY)")
        e.printStackTrace()
    }
}