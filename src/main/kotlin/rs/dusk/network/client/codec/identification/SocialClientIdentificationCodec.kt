package rs.dusk.network.client.codec.identification

import rs.dusk.core.network.codec.Codec
import rs.dusk.core.network.codec.message.MessageDecoder
import rs.dusk.core.network.codec.message.MessageEncoder
import rs.dusk.core.network.codec.message.MessageHandler
import rs.dusk.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 08, 2020
 */
class SocialClientIdentificationCodec : Codec() {

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
