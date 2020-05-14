package rs.dusk.social.network.client.codec.relay.decode

import rs.dusk.core.network.codec.packet.access.PacketReader
import rs.dusk.core.network.model.packet.PacketMetaData
import rs.dusk.core.network.model.packet.PacketType.Companion.VARIABLE_LENGTH_SHORT
import rs.dusk.social.network.codec.relay.RelayMessageDecoder
import rs.dusk.social.network.codec.relay.message.friend.PrivateChatStatusChangeMessage
import rs.dusk.social.utility.constant.SocialOpcodes.PRIVATE_CHAT_STATUS_CHANGE_RELAY_OPCODE

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
@PacketMetaData(opcodes = [PRIVATE_CHAT_STATUS_CHANGE_RELAY_OPCODE], length = VARIABLE_LENGTH_SHORT)
class PrivateChatStatusChangeRelayMessageDecoder : RelayMessageDecoder<PrivateChatStatusChangeMessage>() {
	
	override fun decode(packet : PacketReader) : PrivateChatStatusChangeMessage = with(packet) {
		val username = readString()
		val status = readByte()
		return PrivateChatStatusChangeMessage(username, status)
	}
	
}