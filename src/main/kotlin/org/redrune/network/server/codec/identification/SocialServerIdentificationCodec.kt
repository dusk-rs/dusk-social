package org.redrune.network.server.codec.identification

import org.redrune.core.network.codec.Codec
import org.redrune.core.network.codec.message.MessageDecoder
import org.redrune.core.network.codec.message.MessageEncoder
import org.redrune.core.network.codec.message.MessageHandler
import org.redrune.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 08, 2020
 */
object SocialServerIdentificationCodec : Codec() {

    override fun register() {
        bindDecoders<SocialServerIdentificationMessageDecoder<*>>()
        bindHandlers<SocialServerIdentificationMessageHandler<*>>()
        bindEncoders<SocialServerIdentificationMessageEncoder<*>>()
    }

}

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 4, 2020
 */
abstract class SocialServerIdentificationMessageDecoder<M : Message> : MessageDecoder<M>()

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 4, 2020
 */
abstract class SocialServerIdentificationMessageHandler<M : Message> : MessageHandler<M>()

/**
 *
 * @author Tyluur <contact@kiaira.tech>
 * @since April 4, 2020
 */
abstract class SocialServerIdentificationMessageEncoder<M : Message> : MessageEncoder<M>()