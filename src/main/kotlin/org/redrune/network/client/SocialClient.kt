package org.redrune.network.client

import com.github.michaelbull.logging.InlineLogger
import com.google.common.base.Stopwatch
import org.redrune.core.network.codec.message.decode.OpcodeMessageDecoder
import org.redrune.core.network.codec.message.encode.SizedMessageEncoder
import org.redrune.core.network.codec.message.handle.NetworkMessageHandler
import org.redrune.core.network.codec.packet.decode.SimplePacketDecoder
import org.redrune.core.network.connection.ConnectionPipeline
import org.redrune.core.network.connection.ConnectionSettings
import org.redrune.core.network.connection.ReconnectionAdapter
import org.redrune.core.network.connection.client.NetworkClient
import org.redrune.core.tools.function.NetworkUtils.Companion.loadCodecs
import org.redrune.network.ClientNetworkEventHandler
import org.redrune.network.client.codec.handshake.SocialClientHandshakeCodec
import org.redrune.network.client.codec.handshake.SocialClientHandshakeSession
import org.redrune.network.client.codec.identification.SocialClientIdentificationCodec
import org.redrune.social.world.WorldType
import org.redrune.utility.SocialConstants.SOCIAL_PORT_ID
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
            it.addLast("message.encoder", SizedMessageEncoder(SocialClientHandshakeCodec))
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