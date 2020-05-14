package rs.dusk.social.network.codec.handshake.message

import rs.dusk.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 08, 2020
 */
data class HandshakeBuildConfigurationMessage(val major : Int, val minor : Int) : Message