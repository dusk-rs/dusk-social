package rs.dusk.network.server.codec.handshake.encode.message

import org.redrune.core.network.model.message.Message

/**
 * This message is used to identify whether handshake was successfully verified.
 * When it is sent, the codec is updated appropriately on successful handshake.
 *
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class HandshakeBuildVerificationMessage(val verified: Boolean) : Message