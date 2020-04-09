package org.redrune.network.server.codec.identification

import io.netty.channel.Channel
import org.redrune.core.network.codec.message.decode.OpcodeMessageDecoder
import org.redrune.core.network.codec.message.encode.SizedMessageEncoder
import org.redrune.core.network.codec.message.handle.NetworkMessageHandler
import org.redrune.core.network.codec.packet.decode.SimplePacketDecoder
import org.redrune.core.network.model.session.Session
import org.redrune.core.network.model.session.type.VerifiableSession
import org.redrune.network.ServerNetworkEventHandler
import org.redrune.network.server.codec.relay.SocialServerRelayCodec

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
        replaceHandler("message.encoder", SizedMessageEncoder(SocialServerRelayCodec))
    }

    override fun onTimeout() {
        TODO("Not yet implemented")
    }

    override fun verificationTimeout(): Long {
        TODO("Not yet implemented")
    }


}