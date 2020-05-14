package rs.dusk.social.network.codec.relay.message.friend

import rs.dusk.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
data class PrivateChatStatusChangeMessage(val username : String, val status : Int) : Message