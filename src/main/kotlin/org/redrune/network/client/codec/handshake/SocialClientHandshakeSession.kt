package org.redrune.network.client.codec.handshake

import io.netty.channel.Channel
import org.redrune.core.network.codec.message.decode.OpcodeMessageDecoder
import org.redrune.core.network.codec.message.encode.SizedMessageEncoder
import org.redrune.core.network.codec.message.handle.NetworkMessageHandler
import org.redrune.core.network.codec.packet.decode.SimplePacketDecoder
import org.redrune.core.network.model.session.Session
import org.redrune.core.network.model.session.getSession
import org.redrune.core.network.model.session.type.VerifiableSession
import org.redrune.network.ClientNetworkEventHandler
import org.redrune.network.ServerNetworkEventHandler
import org.redrune.network.client.codec.identification.SocialClientIdentificationCodec
import org.redrune.network.client.codec.identification.SocialClientIdentificationSession
import org.redrune.network.client.codec.identification.encode.message.WorldIdentificationMessage
import org.redrune.social.world.WorldType

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
        replaceHandler("message.encoder", SizedMessageEncoder(SocialClientIdentificationCodec))

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

