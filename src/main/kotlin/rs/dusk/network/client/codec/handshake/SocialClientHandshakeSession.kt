package rs.dusk.network.client.codec.handshake

import io.netty.channel.Channel
import rs.dusk.core.network.codec.message.decode.OpcodeMessageDecoder
import rs.dusk.core.network.codec.message.encode.GenericMessageEncoder
import rs.dusk.core.network.codec.message.handle.NetworkMessageHandler
import rs.dusk.core.network.codec.packet.access.PacketBuilder
import rs.dusk.core.network.codec.packet.decode.SimplePacketDecoder
import rs.dusk.core.network.model.session.Session
import rs.dusk.core.network.model.session.type.VerifiableSession
import rs.dusk.network.ClientNetworkEventHandler
import rs.dusk.network.client.codec.identification.SocialClientIdentificationCodec
import rs.dusk.network.client.codec.identification.SocialClientIdentificationSession
import rs.dusk.network.client.codec.identification.encode.message.WorldIdentificationMessage
import rs.dusk.social.world.WorldType

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class SocialClientHandshakeSession(private val channel: Channel) : Session(channel), VerifiableSession {

    override fun onSuccession() {
        // update to identification codec
        replaceHandler("packet.decoder", SimplePacketDecoder(SocialClientIdentificationCodec))
        replaceHandler("message.decoder", OpcodeMessageDecoder(SocialClientIdentificationCodec))
        replaceHandler(
            "message.handler",
            NetworkMessageHandler(
                SocialClientIdentificationCodec,
                ClientNetworkEventHandler(SocialClientIdentificationSession(channel))
            )
        )
        replaceHandler(
            "message.encoder",
            GenericMessageEncoder(SocialClientIdentificationCodec, PacketBuilder(sized = true))
        )

        // send identification information
        send(WorldIdentificationMessage(1, WorldType.LOBBY.ordinal.toByte()))
    }

    override fun onTimeout() {
        TODO("Not yet implemented")
    }

    override fun verificationTimeout(): Long {
        TODO("Not yet implemented")
    }


}

