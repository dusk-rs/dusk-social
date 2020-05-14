package rs.dusk.social.network.codec.relay.message.login

import rs.dusk.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
data class LoginRequestMessage(val username: String) : Message