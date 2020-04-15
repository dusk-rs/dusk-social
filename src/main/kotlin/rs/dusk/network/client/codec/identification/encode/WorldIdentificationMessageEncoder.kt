package rs.dusk.network.client.codec.identification.encode

import rs.dusk.core.network.codec.packet.access.PacketWriter
import rs.dusk.core.network.model.packet.PacketType
import rs.dusk.network.client.codec.identification.SocialClientIdentificationMessageEncoder
import rs.dusk.network.client.codec.identification.encode.message.WorldIdentificationMessage
import rs.dusk.utility.SocialOpcodes.IDENTIFICATION_MESSAGE_OPCODE

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 08, 2020
 */
class WorldIdentificationMessageEncoder : SocialClientIdentificationMessageEncoder<WorldIdentificationMessage>() {
    override fun encode(builder: PacketWriter, msg: WorldIdentificationMessage) {
        builder.writeOpcode(IDENTIFICATION_MESSAGE_OPCODE, PacketType.FIXED)
        builder.writeByte(msg.id.toInt())
        builder.writeByte(msg.type.toInt())
    }
}