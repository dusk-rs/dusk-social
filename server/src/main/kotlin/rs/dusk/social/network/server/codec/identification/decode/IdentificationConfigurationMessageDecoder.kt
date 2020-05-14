package rs.dusk.social.network.server.codec.identification.decode

import rs.dusk.core.network.codec.packet.access.PacketReader
import rs.dusk.core.network.model.packet.PacketMetaData
import rs.dusk.social.network.codec.identification.IdentificationMessageDecoder
import rs.dusk.social.network.codec.identification.message.IdentificationConfigurationMessage
import rs.dusk.social.utility.constant.SocialOpcodes.IDENTITY_CONFIGURATION_OPCODE

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
@PacketMetaData(opcodes = [IDENTITY_CONFIGURATION_OPCODE], length = 1)
class IdentificationConfigurationMessageDecoder : IdentificationMessageDecoder<IdentificationConfigurationMessage>() {
	
	override fun decode(packet : PacketReader) : IdentificationConfigurationMessage = with(packet) {
		val worldId = packet.readByte()
		return IdentificationConfigurationMessage(worldId)
	}
	
}