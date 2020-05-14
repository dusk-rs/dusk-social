package rs.dusk.social.network.codec.relay

import rs.dusk.core.network.codec.Codec
import rs.dusk.core.network.codec.message.MessageDecoder
import rs.dusk.core.network.codec.message.MessageEncoder
import rs.dusk.core.network.codec.message.MessageHandler
import rs.dusk.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
class RelayCodec : Codec() {
	
	override fun register() {
		bindDecoders<RelayMessageDecoder<*>>()
		bindHandlers<RelayMessageHandler<*>>()
		bindEncoders<RelayMessageEncoder<*>>()
	}
	
}

abstract class RelayMessageDecoder<T : Message> : MessageDecoder<T>()

abstract class RelayMessageHandler<T : Message> : MessageHandler<T>()

abstract class RelayMessageEncoder<T : Message> : MessageEncoder<T>()