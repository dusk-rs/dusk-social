package rs.dusk.social.network.client.codec.handshake.decode

import rs.dusk.core.network.codec.packet.access.PacketReader
import rs.dusk.core.network.model.packet.PacketMetaData
import rs.dusk.social.network.codec.handshake.HandshakeMessageDecoder
import rs.dusk.social.network.codec.handshake.message.HandshakeResponseMessage
import rs.dusk.social.utility.constant.SocialOpcodes

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
@PacketMetaData(opcodes = [SocialOpcodes.HANDSHAKE_RESPONSE_OPCODE], length = 1)
class HandshakeResponseMessageDecoder : HandshakeMessageDecoder<HandshakeResponseMessage>() {
	
	override fun decode(packet : PacketReader) : HandshakeResponseMessage {
		val successful = packet.readByte() == 1
		return HandshakeResponseMessage(successful)
	}
	
}