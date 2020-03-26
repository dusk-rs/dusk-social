package org.redrune.network.social.codec.handshake

import org.redrune.core.network.codec.Codec
import org.redrune.core.network.codec.message.MessageDecoder
import org.redrune.core.network.codec.message.MessageEncoder
import org.redrune.core.network.codec.message.MessageHandler
import org.redrune.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
object HandshakeCodec : Codec() {

    override fun register() {
        bindDecoders<HandshakeMessageDecoder<*>>()
        bindHandlers<HandshakeMessageHandler<*>>()
        bindEncoders<HandshakeMessageEncoder<*>>()
        report()
    }

}

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
abstract class HandshakeMessageDecoder<M : Message> : MessageDecoder<M>()

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
abstract class HandshakeMessageHandler<M : Message> : MessageHandler<M>()

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
abstract class HandshakeMessageEncoder<M : Message> : MessageEncoder<M>()
