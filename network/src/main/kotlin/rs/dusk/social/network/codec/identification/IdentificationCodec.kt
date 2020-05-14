package rs.dusk.social.network.codec.identification

import rs.dusk.core.network.codec.Codec
import rs.dusk.core.network.codec.message.MessageDecoder
import rs.dusk.core.network.codec.message.MessageEncoder
import rs.dusk.core.network.codec.message.MessageHandler
import rs.dusk.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
class IdentificationCodec : Codec() {
	
	override fun register() {
		bindDecoders<IdentificationMessageDecoder<*>>()
		bindHandlers<IdentificationMessageHandler<*>>()
		bindEncoders<IdentificationMessageEncoder<*>>()
	}
	
}

abstract class IdentificationMessageDecoder<T : Message> : MessageDecoder<T>()

abstract class IdentificationMessageHandler<T : Message> : MessageHandler<T>()

abstract class IdentificationMessageEncoder<T : Message> : MessageEncoder<T>()