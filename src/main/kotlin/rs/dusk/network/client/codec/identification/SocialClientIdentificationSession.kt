package rs.dusk.network.client.codec.identification

import io.netty.channel.Channel
import rs.dusk.core.network.codec.message.decode.OpcodeMessageDecoder
import rs.dusk.core.network.codec.message.encode.GenericMessageEncoder
import rs.dusk.core.network.codec.message.handle.NetworkMessageHandler
import rs.dusk.core.network.codec.packet.access.PacketBuilder
import rs.dusk.core.network.codec.packet.decode.SimplePacketDecoder
import rs.dusk.core.network.model.session.Session
import rs.dusk.core.network.model.session.type.VerifiableSession
import rs.dusk.network.client.SocialClientConnectionEventHandler
import rs.dusk.network.client.codec.relay.SocialClientRelayCodec
import rs.dusk.network.client.codec.relay.SocialClientRelaySession

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 08, 2020
 */
class SocialClientIdentificationSession(private val channel: Channel) : Session(channel), VerifiableSession {

    override fun onSuccession() {
        // update to identification codec
        replaceHandler("packet.decoder", SimplePacketDecoder(SocialClientRelayCodec))
        replaceHandler("message.decoder", OpcodeMessageDecoder(SocialClientRelayCodec))
        replaceHandler(
            "message.handler",
            NetworkMessageHandler(
                SocialClientRelayCodec,
                SocialClientConnectionEventHandler(
                    SocialClientRelaySession(
                        channel
                    )
                )
            )
        )
        replaceHandler(
            "message.encoder",
            GenericMessageEncoder(SocialClientRelayCodec, PacketBuilder(sized = true))
        )

    }

    override fun onTimeout() {
        TODO("Not yet implemented")
    }

    override fun verificationTimeout(): Long {
        TODO("Not yet implemented")
    }


}