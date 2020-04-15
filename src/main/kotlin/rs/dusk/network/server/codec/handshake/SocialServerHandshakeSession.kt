package rs.dusk.network.server.codec.handshake

import io.netty.channel.Channel
import org.redrune.core.network.codec.message.decode.OpcodeMessageDecoder
import org.redrune.core.network.codec.message.encode.SizedMessageEncoder
import org.redrune.core.network.codec.message.handle.NetworkMessageHandler
import org.redrune.core.network.codec.packet.decode.SimplePacketDecoder
import org.redrune.core.network.model.session.Session
import org.redrune.core.network.model.session.setSession
import org.redrune.core.network.model.session.type.VerifiableSession
import org.redrune.network.server.codec.handshake.encode.message.HandshakeBuildVerificationMessage
import org.redrune.network.server.codec.identification.SocialServerIdentificationCodec
import org.redrune.network.server.codec.identification.SocialServerIdentificationSession

/**
 * This session represents a session between the social server and the social client, in the point of view of the social server.
 *
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
class SocialServerHandshakeSession(private val channel: Channel) : Session(channel), VerifiableSession {

    override fun onSuccession() {
        // send the message using handshake codec
        send(HandshakeBuildVerificationMessage(true))

        // update codec to identification
        val session = SocialServerIdentificationSession(channel)
        replaceHandler("packet.decoder", SimplePacketDecoder(SocialServerIdentificationCodec))
        replaceHandler("message.decoder", OpcodeMessageDecoder(SocialServerIdentificationCodec))
        replaceHandler(
            "message.handler",
            NetworkMessageHandler(
                SocialServerIdentificationCodec,
                _root_ide_package_.rs.dusk.network.ServerNetworkEventHandler(session)
            )
        )
        replaceHandler("message.encoder", SizedMessageEncoder(SocialServerIdentificationCodec))
        channel.setSession(session)
    }

    override fun onTimeout() {
        TODO("Not yet implemented")
    }

    override fun verificationTimeout(): Long {
        TODO("Not yet implemented")
    }

}