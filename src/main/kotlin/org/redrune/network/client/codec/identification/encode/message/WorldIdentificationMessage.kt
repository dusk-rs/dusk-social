package org.redrune.network.client.codec.identification.encode.message

import org.redrune.core.network.model.message.Message

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
     * The [org.redrune.social.world.WorldType] index value of the world to identify
     */
    val type: Byte
) : Message