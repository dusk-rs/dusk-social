package rs.dusk.social.network.client.codec.identification.encode

import rs.dusk.core.network.codec.packet.access.PacketWriter
import rs.dusk.social.network.codec.identification.IdentificationMessageEncoder
import rs.dusk.social.network.codec.identification.message.IdentificationConfigurationMessage
import rs.dusk.social.utility.constant.SocialOpcodes.IDENTITY_CONFIGURATION_OPCODE

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
class IdentificationConfigurationMessageEncoder : IdentificationMessageEncoder<IdentificationConfigurationMessage>() {
	
	override fun encode(builder : PacketWriter, msg : IdentificationConfigurationMessage) = with(builder) {
		writeOpcode(IDENTITY_CONFIGURATION_OPCODE)
		writeByte(msg.worldId)
	}
	
}