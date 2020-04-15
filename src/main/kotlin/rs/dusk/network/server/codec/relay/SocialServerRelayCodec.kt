package rs.dusk.network.server.codec.relay

import org.redrune.core.network.codec.Codec
import org.redrune.core.network.codec.message.MessageDecoder
import org.redrune.core.network.codec.message.MessageEncoder
import org.redrune.core.network.codec.message.MessageHandler
import org.redrune.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 08, 2020
 */
object SocialServerRelayCodec : Codec() {

    override fun register() {
        bindDecoders<SocialServerRelayMessageDecoder<*>>()
        bindHandlers<SocialServerRelayMessageHandler<*>>()
        bindEncoders<SocialServerRelayMessageEncoder<*>>()
    }

}

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 4, 2020
 */
abstract class SocialServerRelayMessageDecoder<M : Message> : MessageDecoder<M>()

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 4, 2020
 */
abstract class SocialServerRelayMessageHandler<M : Message> : MessageHandler<M>()

/**
 *
 * @author Tyluur <contact@kiaira.tech>
 * @since April 4, 2020
 */
abstract class SocialServerRelayMessageEncoder<M : Message> : MessageEncoder<M>()