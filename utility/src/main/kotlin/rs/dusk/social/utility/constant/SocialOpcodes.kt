package rs.dusk.social.utility.constant

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 08, 2020
 */
object SocialOpcodes {
	
	/**
	 * The opcode for the packet that is encoded with handshake build information
	 */
	const val HANDSHAKE_BUILD_OPCODE = 1
	
	/**
	 * The opcode that identifies the packet that encodes the handshake response
	 */
	const val HANDSHAKE_RESPONSE_OPCODE = 2
	
	/**
	 * The opcode that identifies the packet that encodes for connection identity
	 */
	const val IDENTITY_CONFIGURATION_OPCODE = 3
	
	/**
	 * The opcode that identifies the packet that encodes the identification response information
	 */
	const val IDENTITY_RESPONSE_OPCODE = 4
	
	/**
	 * The opcode that identifies the packet that encodes a login request
	 */
	const val LOGIN_REQUEST_OPCODE = 5
	
	/**
	 * The opcode that identifies the packet (client -> server) that encodes a private chat status change
	 */
	const val PRIVATE_CHAT_STATUS_CHANGE_NOTIFICATION_OPCODE = 10
	
	/**
	 * The opcode that identifies the packet (server -> client[s]) that encodes a private chat status change
	 */
	const val PRIVATE_CHAT_STATUS_CHANGE_RELAY_OPCODE = 11
}