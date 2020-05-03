package rs.dusk.network.client.codec.handshake

import rs.dusk.core.network.codec.Codec
import rs.dusk.core.network.codec.message.MessageDecoder
import rs.dusk.core.network.codec.message.MessageEncoder
import rs.dusk.core.network.codec.message.MessageHandler
import rs.dusk.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 31, 2020
 */
class SocialClientHandshakeCodec : Codec() {

    override fun register() {
        bindDecoders<SocialClientHandshakeMessageDecoder<*>>()
        bindHandlers<SocialClientHandshakeMessageHandler<*>>()
        bindEncoders<SocialClientHandshakeMessageEncoder<*>>()
    }

}

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
abstract class SocialClientHandshakeMessageDecoder<M : Message> : MessageDecoder<M>()

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
abstract class SocialClientHandshakeMessageHandler<M : Message> : MessageHandler<M>()

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
abstract class SocialClientHandshakeMessageEncoder<M : Message> : MessageEncoder<M>()
