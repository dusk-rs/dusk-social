package rs.dusk.social.network.client.codec.relay.encode

import rs.dusk.core.network.codec.packet.access.PacketWriter
import rs.dusk.social.network.codec.relay.message.login.LoginRequestMessage
import rs.dusk.social.network.codec.relay.RelayMessageEncoder
import rs.dusk.social.utility.constant.SocialOpcodes.LOGIN_REQUEST_OPCODE

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
class LoginRequestMessageEncoder : RelayMessageEncoder<LoginRequestMessage>() {
	
	override fun encode(builder : PacketWriter, msg : LoginRequestMessage) = with(builder) {
		val (username) = msg
		writeOpcode(LOGIN_REQUEST_OPCODE)
		writeString(username)
	}
	
}