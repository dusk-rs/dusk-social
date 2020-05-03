package rs.dusk.network.server.codec.handshake

import io.netty.channel.Channel
import rs.dusk.core.network.codec.CodecRepository
import rs.dusk.core.network.codec.message.MessageReader
import rs.dusk.core.network.codec.message.decode.OpcodeMessageDecoder
import rs.dusk.core.network.codec.message.encode.GenericMessageEncoder
import rs.dusk.core.network.codec.packet.access.PacketBuilder
import rs.dusk.core.network.codec.packet.decode.SimplePacketDecoder
import rs.dusk.core.network.model.session.Session
import rs.dusk.core.network.model.session.setSession
import rs.dusk.core.network.model.session.type.VerifiableSession
import rs.dusk.network.server.SocialServerConnectionEventHandler
import rs.dusk.network.server.codec.handshake.encode.message.HandshakeBuildVerificationMessage
import rs.dusk.network.server.codec.identification.SocialServerIdentificationCodec
import rs.dusk.network.server.codec.identification.SocialServerIdentificationSession

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
	    val codec = CodecRepository.get(SocialServerIdentificationCodec::class)
	    
        val session = SocialServerIdentificationSession(channel)
        replaceHandler("packet.decoder", SimplePacketDecoder(codec))
        replaceHandler("message.decoder", OpcodeMessageDecoder(codec))
        replaceHandler(
            "message.reader",
            MessageReader(
	            codec
            )
        )
        replaceHandler(
            "message.encoder",
            GenericMessageEncoder(codec, PacketBuilder(sized = true))
        )
        channel.setSession(session)
    }

    override fun onTimeout() {
        TODO("Not yet implemented")
    }

    override fun verificationTimeout(): Long {
        TODO("Not yet implemented")
    }

}