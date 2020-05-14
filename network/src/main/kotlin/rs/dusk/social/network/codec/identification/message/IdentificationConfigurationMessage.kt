package rs.dusk.social.network.codec.identification.message

import rs.dusk.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
data class IdentificationConfigurationMessage(val worldId : Int) : Message