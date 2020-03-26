package org.redrune.network.social.codec.handshake.encode.message

import org.redrune.core.network.model.message.Message
import org.redrune.social.world.WorldType

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
data class HandshakeRegistryMessage(val id: Int, val type: WorldType) : Message