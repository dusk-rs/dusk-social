package rs.dusk.network.client.codec.identification

import io.netty.channel.Channel
import rs.dusk.core.network.codec.CodecRepository
import rs.dusk.core.network.codec.message.MessageReader
import rs.dusk.core.network.codec.message.decode.OpcodeMessageDecoder
import rs.dusk.core.network.codec.message.encode.GenericMessageEncoder
import rs.dusk.core.network.codec.packet.access.PacketBuilder
import rs.dusk.core.network.codec.packet.decode.SimplePacketDecoder
import rs.dusk.core.network.model.session.Session
import rs.dusk.core.network.model.session.type.VerifiableSession
import rs.dusk.network.client.codec.relay.SocialClientRelayCodec

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 08, 2020
 */
class SocialClientIdentificationSession(private val channel: Channel) : Session(channel), VerifiableSession {

    override fun onSuccession() {
	    // update to identification codec
	    val codec = CodecRepository.get(SocialClientRelayCodec::class)
	    
        // update to identification codec
        replaceHandler("packet.decoder", SimplePacketDecoder(codec))
        replaceHandler("message.decoder", OpcodeMessageDecoder(codec))
        replaceHandler("message.reader", MessageReader(codec))
        replaceHandler(
            "message.encoder", GenericMessageEncoder(codec, PacketBuilder(sized = true))
        )

    }

    override fun onTimeout() {
        TODO("Not yet implemented")
    }

    override fun verificationTimeout(): Long {
        TODO("Not yet implemented")
    }


}