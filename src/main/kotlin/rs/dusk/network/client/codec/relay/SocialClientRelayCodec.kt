package rs.dusk.network.client.codec.relay

import rs.dusk.core.network.codec.Codec
import rs.dusk.core.network.codec.message.MessageDecoder
import rs.dusk.core.network.codec.message.MessageEncoder
import rs.dusk.core.network.codec.message.MessageHandler
import rs.dusk.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 25, 2020
 */
object SocialClientRelayCodec : Codec() {

    override fun register() {
        bindDecoders<SocialClientRelayMessageDecoder<*>>()
        bindHandlers<SocialClientRelayMessageHandler<*>>()
        bindEncoders<SocialClientRelayMessageEncoder<*>>()
    }


}

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
abstract class SocialClientRelayMessageDecoder<M : Message> : MessageDecoder<M>()

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
abstract class SocialClientRelayMessageHandler<M : Message> : MessageHandler<M>()

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
abstract class SocialClientRelayMessageEncoder<M : Message> : MessageEncoder<M>()
