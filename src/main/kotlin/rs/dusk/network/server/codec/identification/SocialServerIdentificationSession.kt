package rs.dusk.network.server.codec.identification

import io.netty.channel.Channel
import rs.dusk.core.network.codec.message.decode.OpcodeMessageDecoder
import rs.dusk.core.network.codec.message.encode.GenericMessageEncoder
import rs.dusk.core.network.codec.message.handle.NetworkMessageHandler
import rs.dusk.core.network.codec.packet.access.PacketBuilder
import rs.dusk.core.network.codec.packet.decode.SimplePacketDecoder
import rs.dusk.core.network.model.session.Session
import rs.dusk.core.network.model.session.setSession
import rs.dusk.core.network.model.session.type.VerifiableSession
import rs.dusk.network.server.SocialServerConnectionEventHandler
import rs.dusk.network.server.codec.identification.encode.message.WorldIdentificationSuccessionMessage
import rs.dusk.network.server.codec.relay.SocialServerRelayCodec
import rs.dusk.network.server.codec.relay.SocialServerRelaySession
import rs.dusk.utility.setWorld

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 08, 2020
 */
class SocialServerIdentificationSession(private val channel: Channel) : Session(channel), VerifiableSession {

    override fun onSuccession() {
        send(WorldIdentificationSuccessionMessage(true))
        val session = SocialServerRelaySession(channel)

        // update codec to relay
        replaceHandler("packet.decoder", SimplePacketDecoder(SocialServerRelayCodec))
        replaceHandler("message.decoder", OpcodeMessageDecoder(SocialServerRelayCodec))
        replaceHandler(
            "message.handler",
            NetworkMessageHandler(
                SocialServerRelayCodec,
                SocialServerConnectionEventHandler(
                    session
                )
            )
        )
        channel.setSession(session)

        replaceHandler("message.encoder", GenericMessageEncoder(SocialServerRelayCodec, PacketBuilder(sized = true)))
    }

    override fun onTimeout() {
        TODO("Not yet implemented")
    }

    override fun verificationTimeout(): Long {
        TODO("Not yet implemented")
    }


}