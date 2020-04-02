package org.redrune.network.server.codec.handshake

import org.redrune.core.network.codec.Codec
import org.redrune.core.network.codec.message.MessageDecoder
import org.redrune.core.network.codec.message.MessageEncoder
import org.redrune.core.network.codec.message.MessageHandler
import org.redrune.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
object SocialServerHandshakeCodec : Codec() {

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
