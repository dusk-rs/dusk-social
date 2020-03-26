package org.redrune.network.social

import org.redrune.core.network.codec.message.encode.RawMessageEncoder
import org.redrune.core.network.codec.message.encode.SizedMessageEncoder
import org.redrune.core.network.codec.message.handle.NetworkMessageHandler
import org.redrune.core.network.codec.packet.decode.SimplePacketDecoder
import org.redrune.core.network.connection.ConnectionPipeline
import org.redrune.core.network.connection.ConnectionSettings
import org.redrune.core.network.connection.server.NetworkServer
import org.redrune.network.NetworkEventHandler
import org.redrune.network.social.codec.handshake.HandshakeCodec
import org.redrune.utility.SocialConstants

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
class SocialServer {


    fun start() {
        val settings = ConnectionSettings("localhost", SocialConstants.SOCIAL_PORT_ID)
        val server = NetworkServer(settings)
        val pipeline = ConnectionPipeline {
            it.addLast("packet.decoder", SimplePacketDecoder(HandshakeCodec))
            it.addLast("message.decoder", SizedMessageEncoder(HandshakeCodec))
            it.addLast("message.handler", NetworkMessageHandler(HandshakeCodec, NetworkEventHandler()))
            it.addLast("message.encoder", RawMessageEncoder(HandshakeCodec))
        }
        server.configure(pipeline)
        server.start()
    }

}

fun main() {
    val server = SocialServer()
    server.start()
}