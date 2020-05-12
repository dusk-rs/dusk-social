package rs.dusk.social.network.codec.handshake

import rs.dusk.core.network.codec.Codec
import rs.dusk.core.network.codec.message.MessageDecoder
import rs.dusk.core.network.codec.message.MessageEncoder
import rs.dusk.core.network.codec.message.MessageHandler
import rs.dusk.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 07, 2020
 */
class HandshakeCodec : Codec() {
	override fun register() {
		bindDecoders<HandshakeMessageDecoder<*>>()
		bindHandlers<HandshakeMessageHandler<*>>()
		bindEncoders<HandshakeMessageEncoder<*>>()
	}
}

abstract class HandshakeMessageDecoder<T: Message> : MessageDecoder<T>()

abstract class HandshakeMessageHandler<T: Message> : MessageHandler<T>()

abstract class HandshakeMessageEncoder<T: Message> : MessageEncoder<T>()