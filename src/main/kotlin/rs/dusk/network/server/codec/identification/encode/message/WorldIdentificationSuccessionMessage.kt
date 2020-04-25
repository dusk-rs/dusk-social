package rs.dusk.network.server.codec.identification.encode.message

import rs.dusk.core.network.model.message.Message

/**
 * This message contains information about whether the world identification process was successful
 *
 * @author Tyluur <contact@kiaira.tech>
 * @since April 25, 2020
 */
data class WorldIdentificationSuccessionMessage(val success: Boolean) : Message