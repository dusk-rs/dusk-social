package org.redrune.network.client.codec.identification.encode

import org.redrune.core.network.codec.packet.access.PacketBuilder
import org.redrune.core.network.model.packet.PacketType
import org.redrune.network.client.codec.identification.SocialClientIdentificationMessageEncoder
import org.redrune.network.client.codec.identification.encode.message.WorldIdentificationMessage
import org.redrune.utility.SocialOpcodes
import org.redrune.utility.SocialOpcodes.IDENTIFICATION_MESSAGE_OPCODE

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 08, 2020
 */
class WorldIdentificationMessageEncoder : SocialClientIdentificationMessageEncoder<WorldIdentificationMessage>() {
    override fun encode(builder: PacketBuilder, msg: WorldIdentificationMessage) {
        builder.writeOpcode(IDENTIFICATION_MESSAGE_OPCODE, PacketType.FIXED)
        builder.writeByte(msg.id.toInt())
        builder.writeByte(msg.type.toInt())
    }
}