package rs.dusk.network.server.codec.identification

import rs.dusk.core.network.codec.Codec
import rs.dusk.core.network.codec.message.MessageDecoder
import rs.dusk.core.network.codec.message.MessageEncoder
import rs.dusk.core.network.codec.message.MessageHandler
import rs.dusk.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 08, 2020
 */
class SocialServerIdentificationCodec : Codec() {

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