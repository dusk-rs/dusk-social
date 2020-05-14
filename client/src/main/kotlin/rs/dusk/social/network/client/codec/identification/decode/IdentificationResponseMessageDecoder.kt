package rs.dusk.social.network.client.codec.identification.decode

import rs.dusk.core.network.codec.packet.access.PacketReader
import rs.dusk.core.network.model.packet.PacketMetaData
import rs.dusk.social.network.codec.identification.IdentificationMessageDecoder
import rs.dusk.social.network.codec.identification.message.IdentificationResponseMessage
import rs.dusk.social.utility.constant.SocialOpcodes.IDENTITY_RESPONSE_OPCODE

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
@PacketMetaData(opcodes = [IDENTITY_RESPONSE_OPCODE], length = 1)
class IdentificationResponseMessageDecoder : IdentificationMessageDecoder<IdentificationResponseMessage>() {
	
	override fun decode(packet : PacketReader) : IdentificationResponseMessage {
		val successful = packet.readByte() == 1
		return IdentificationResponseMessage(successful)
	}
	
}