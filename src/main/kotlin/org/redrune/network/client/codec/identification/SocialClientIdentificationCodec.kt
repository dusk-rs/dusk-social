package org.redrune.network.client.codec.identification

import org.redrune.core.network.codec.Codec
import org.redrune.core.network.codec.message.MessageDecoder
import org.redrune.core.network.codec.message.MessageEncoder
import org.redrune.core.network.codec.message.MessageHandler
import org.redrune.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 08, 2020
 */
object SocialClientIdentificationCodec : Codec() {

    override fun register() {
        bindDecoders<SocialClientIdentificationMessageDecoder<*>>()
        bindHandlers<SocialClientIdentificationMessageHandler<*>>()
        bindEncoders<SocialClientIdentificationMessageEncoder<*>>()
    }

}

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
abstract class SocialClientIdentificationMessageDecoder<M : Message> : MessageDecoder<M>()

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
abstract class SocialClientIdentificationMessageHandler<M : Message> : MessageHandler<M>()

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
abstract class SocialClientIdentificationMessageEncoder<M : Message> : MessageEncoder<M>()
