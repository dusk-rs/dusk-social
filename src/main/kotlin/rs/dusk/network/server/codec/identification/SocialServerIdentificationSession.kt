package rs.dusk.network.server.codec.identification

import io.netty.channel.Channel
import rs.dusk.core.network.codec.message.decode.OpcodeMessageDecoder
import rs.dusk.core.network.codec.message.encode.GenericMessageEncoder
import rs.dusk.core.network.codec.message.handle.NetworkMessageHandler
import rs.dusk.core.network.codec.packet.access.PacketBuilder
import rs.dusk.core.network.codec.packet.decode.SimplePacketDecoder
import rs.dusk.core.network.model.session.Session
import rs.dusk.core.network.model.session.type.VerifiableSession
import rs.dusk.network.ServerNetworkEventHandler
import rs.dusk.network.server.codec.relay.SocialServerRelayCodec

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 08, 2020
 */
class SocialServerIdentificationSession(private val channel: Channel) : Session(channel), VerifiableSession {

    override fun onSuccession() {
        // update codec to relay
        replaceHandler("packet.decoder", SimplePacketDecoder(SocialServerRelayCodec))
        replaceHandler("message.decoder", OpcodeMessageDecoder(SocialServerRelayCodec))
        replaceHandler(
            "message.handler",
            NetworkMessageHandler(
                SocialServerIdentificationCodec,
                ServerNetworkEventHandler(SocialServerIdentificationSession(channel))
            )
        )
        replaceHandler("message.encoder", GenericMessageEncoder(SocialServerRelayCodec, PacketBuilder(sized = true)))
    }

    override fun onTimeout() {
        TODO("Not yet implemented")
    }

    override fun verificationTimeout(): Long {
        TODO("Not yet implemented")
    }


}