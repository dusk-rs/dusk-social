package rs.dusk.social.network.codec.handshake.message

import rs.dusk.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
data class HandshakeResponseMessage(val successful : Boolean) : Message