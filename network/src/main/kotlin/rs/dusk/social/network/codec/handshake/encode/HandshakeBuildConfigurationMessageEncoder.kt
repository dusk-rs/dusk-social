package rs.dusk.social.network.codec.handshake.encode

import rs.dusk.core.network.codec.packet.access.PacketWriter
import rs.dusk.social.network.codec.handshake.HandshakeMessageEncoder
import rs.dusk.social.network.codec.handshake.encode.message.HandshakeBuildConfigurationMessage
import rs.dusk.social.utility.constant.SocialConstants.BUILD_MAJOR
import rs.dusk.social.utility.constant.SocialConstants.BUILD_MINOR
import rs.dusk.social.utility.constant.SocialOpcodes.HANDSHAKE_BUILD_ENCODE_OPCODE

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 08, 2020
 */
class HandshakeBuildConfigurationMessageEncoder : HandshakeMessageEncoder<HandshakeBuildConfigurationMessage>() {
	
	override fun encode(builder : PacketWriter, msg : HandshakeBuildConfigurationMessage) {
		builder.apply {
			writeOpcode(HANDSHAKE_BUILD_ENCODE_OPCODE)
			writeByte(BUILD_MAJOR)
			writeByte(BUILD_MINOR)
		}
	}
	
	
}