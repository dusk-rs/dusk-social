package rs.dusk.social.network.server.codec.handshake.encode

import rs.dusk.core.network.codec.packet.access.PacketWriter
import rs.dusk.social.network.codec.handshake.HandshakeMessageEncoder
import rs.dusk.social.network.codec.handshake.message.HandshakeResponseMessage
import rs.dusk.social.utility.constant.SocialOpcodes.HANDSHAKE_RESPONSE_OPCODE

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
class HandshakeResponseMessageEncoder : HandshakeMessageEncoder<HandshakeResponseMessage>() {
	
	override fun encode(builder : PacketWriter, msg : HandshakeResponseMessage) = with(builder) {
		writeOpcode(HANDSHAKE_RESPONSE_OPCODE)
		writeByte(if (msg.successful) 1 else 0)
	}
	
}