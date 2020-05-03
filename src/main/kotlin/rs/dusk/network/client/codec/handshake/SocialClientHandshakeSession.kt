package rs.dusk.network.client.codec.handshake

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
import rs.dusk.network.client.codec.identification.SocialClientIdentificationCodec
import rs.dusk.network.client.codec.identification.SocialClientIdentificationSession
import rs.dusk.network.client.codec.identification.encode.message.WorldIdentificationMessage
import rs.dusk.social.client.SocialClientManager
import rs.dusk.utility.inject
import java.lang.IllegalStateException

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class SocialClientHandshakeSession(private val channel: Channel) : Session(channel), VerifiableSession {

    private val manager: SocialClientManager by inject()

    override fun onSuccession() {
        val session = SocialClientIdentificationSession(channel)

        // update to identification codec
	    val codec = CodecRepository.get(SocialClientIdentificationCodec::class)
	    
	    replaceHandler("packet.decoder", SimplePacketDecoder(codec))
        replaceHandler("message.decoder", OpcodeMessageDecoder(codec))
        replaceHandler("message.reader", MessageReader(codec))
        replaceHandler(
            "message.encoder",
            GenericMessageEncoder(codec, PacketBuilder(sized = true))
        )
        channel.setSession(session)

        // send identification information
        send(WorldIdentificationMessage(manager.worldId!!.toByte(), manager.worldType!!.ordinal.toByte()))
    }

    override fun onTimeout() {
        TODO("Not yet implemented")
    }

    override fun verificationTimeout(): Long {
        TODO("Not yet implemented")
    }


}

