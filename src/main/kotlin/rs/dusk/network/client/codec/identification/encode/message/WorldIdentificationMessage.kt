package rs.dusk.network.client.codec.identification.encode.message

import rs.dusk.core.network.model.message.Message

/**
 * This message contains the information of a world
 *
 * @author Tyluur <contact@kiaira.tech>
 * @since April 08, 2020
 */
data class WorldIdentificationMessage(
    /**
     * The id of the world to identify
     */
    val id: Byte,

    /**
     * The [rs.dusk.social.world.WorldType] index value of the world to identify
     */
    val type: Byte
) : Message