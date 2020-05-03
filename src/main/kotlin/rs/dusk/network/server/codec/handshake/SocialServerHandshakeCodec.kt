package rs.dusk.network.server.codec.handshake

import rs.dusk.core.network.codec.Codec
import rs.dusk.core.network.codec.message.MessageDecoder
import rs.dusk.core.network.codec.message.MessageEncoder
import rs.dusk.core.network.codec.message.MessageHandler
import rs.dusk.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
class SocialServerHandshakeCodec : Codec() {

    override fun register() {
        bindDecoders<SocialServerHandshakeMessageDecoder<*>>()
        bindHandlers<SocialServerHandshakeMessageHandler<*>>()
        bindEncoders<SocialServerHandshakeMessageEncoder<*>>()
    }

}

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
abstract class SocialServerHandshakeMessageDecoder<M : Message> : MessageDecoder<M>()

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
abstract class SocialServerHandshakeMessageHandler<M : Message> : MessageHandler<M>()

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
abstract class SocialServerHandshakeMessageEncoder<M : Message> : MessageEncoder<M>()
