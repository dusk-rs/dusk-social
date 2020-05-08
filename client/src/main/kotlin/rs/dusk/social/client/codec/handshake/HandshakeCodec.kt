package rs.dusk.social.client.codec.handshake

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
		bindDecoders<HandshakeCodecMessageDecoder<*>>()
		bindHandlers<HandshakeCodecMessageHandler<*>>()
		bindEncoders<HandshakeCodecMessageEncoder<*>>()
	}
}

abstract class HandshakeCodecMessageDecoder<T: Message> : MessageDecoder<T>()

abstract class HandshakeCodecMessageHandler<T: Message> : MessageHandler<T>()

abstract class HandshakeCodecMessageEncoder<T: Message> : MessageEncoder<T>()