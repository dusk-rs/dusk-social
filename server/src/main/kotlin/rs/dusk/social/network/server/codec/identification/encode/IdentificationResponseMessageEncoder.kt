package rs.dusk.social.network.server.codec.identification.encode

import rs.dusk.core.network.codec.packet.access.PacketWriter
import rs.dusk.social.network.codec.identification.IdentificationMessageEncoder
import rs.dusk.social.network.codec.identification.message.IdentificationResponseMessage
import rs.dusk.social.utility.constant.SocialOpcodes.IDENTITY_RESPONSE_OPCODE

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
class IdentificationResponseMessageEncoder : IdentificationMessageEncoder<IdentificationResponseMessage>() {
	
	override fun encode(builder : PacketWriter, msg : IdentificationResponseMessage) = with(builder) {
		val (successful) = msg
		
		writeOpcode(IDENTITY_RESPONSE_OPCODE)
		writeByte(if (successful) 1 else 0)
	}
	
}