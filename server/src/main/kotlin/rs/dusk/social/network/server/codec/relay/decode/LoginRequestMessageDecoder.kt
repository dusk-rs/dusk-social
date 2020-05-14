package rs.dusk.social.network.server.codec.relay.decode

import rs.dusk.core.network.codec.packet.access.PacketReader
import rs.dusk.core.network.model.packet.PacketMetaData
import rs.dusk.core.network.model.packet.PacketType.Companion.VARIABLE_LENGTH_BYTE
import rs.dusk.social.network.codec.relay.RelayMessageDecoder
import rs.dusk.social.network.codec.relay.message.login.LoginRequestMessage
import rs.dusk.social.utility.constant.SocialOpcodes.LOGIN_REQUEST_OPCODE

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
@PacketMetaData(opcodes = [LOGIN_REQUEST_OPCODE], length = VARIABLE_LENGTH_BYTE)
class LoginRequestMessageDecoder : RelayMessageDecoder<LoginRequestMessage>() {
	
	override fun decode(packet : PacketReader) : LoginRequestMessage = with(packet) {
		val username = readString()
		return LoginRequestMessage(username)
	}
	
	
}