package rs.dusk.social.network.client.codec.relay.encode

import rs.dusk.core.network.codec.packet.access.PacketWriter
import rs.dusk.core.network.model.packet.PacketType.BYTE
import rs.dusk.core.network.model.packet.PacketType.SHORT
import rs.dusk.social.network.codec.relay.RelayMessageEncoder
import rs.dusk.social.network.codec.relay.message.friend.PrivateChatStatusChangeMessage
import rs.dusk.social.utility.constant.SocialOpcodes.PRIVATE_CHAT_STATUS_CHANGE_NOTIFICATION_OPCODE

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
class PrivateChatStatusChangeNotificationMessageEncoder : RelayMessageEncoder<PrivateChatStatusChangeMessage>() {
	
	override fun encode(builder : PacketWriter, msg : PrivateChatStatusChangeMessage) = with(builder) {
		writeOpcode(PRIVATE_CHAT_STATUS_CHANGE_NOTIFICATION_OPCODE, SHORT)
		writeString(msg.username)
		writeByte(msg.status)
	}
	
}