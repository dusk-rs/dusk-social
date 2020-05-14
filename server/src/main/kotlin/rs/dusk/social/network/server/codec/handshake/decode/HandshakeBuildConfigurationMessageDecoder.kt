package rs.dusk.social.network.server.codec.handshake.decode

import rs.dusk.core.network.codec.packet.access.PacketReader
import rs.dusk.core.network.model.packet.PacketMetaData
import rs.dusk.social.network.codec.handshake.message.HandshakeBuildConfigurationMessage
import rs.dusk.social.network.codec.handshake.HandshakeMessageDecoder
import rs.dusk.social.utility.constant.SocialOpcodes

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 12, 2020
 */
@PacketMetaData(opcodes = [SocialOpcodes.HANDSHAKE_BUILD_OPCODE], length = 2)
class HandshakeBuildConfigurationMessageDecoder : HandshakeMessageDecoder<HandshakeBuildConfigurationMessage>() {
	
	override fun decode(packet : PacketReader) : HandshakeBuildConfigurationMessage {
		val major = packet.readByte()
		val minor = packet.readByte()
		
		return HandshakeBuildConfigurationMessage(
			major,
			minor
		)
	}
	
}