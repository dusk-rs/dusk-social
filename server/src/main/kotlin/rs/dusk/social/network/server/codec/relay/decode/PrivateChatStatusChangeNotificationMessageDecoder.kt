package rs.dusk.social.network.server.codec.relay.decode

import rs.dusk.core.network.codec.packet.access.PacketReader
import rs.dusk.core.network.model.packet.PacketMetaData
import rs.dusk.core.network.model.packet.PacketType.Companion.VARIABLE_LENGTH_BYTE
import rs.dusk.core.network.model.packet.PacketType.Companion.VARIABLE_LENGTH_SHORT
import rs.dusk.social.network.codec.relay.RelayMessageDecoder
import rs.dusk.social.network.codec.relay.message.friend.PrivateChatStatusChangeMessage
import rs.dusk.social.utility.constant.SocialOpcodes.PRIVATE_CHAT_STATUS_CHANGE_NOTIFICATION_OPCODE

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
@PacketMetaData(opcodes = [PRIVATE_CHAT_STATUS_CHANGE_NOTIFICATION_OPCODE], length = VARIABLE_LENGTH_SHORT)
class PrivateChatStatusChangeNotificationMessageDecoder : RelayMessageDecoder<PrivateChatStatusChangeMessage>() {
	
	override fun decode(packet : PacketReader) : PrivateChatStatusChangeMessage {
		val username = packet.readString()
		val status = packet.readByte()
		return PrivateChatStatusChangeMessage(username, status)
	}
	
}