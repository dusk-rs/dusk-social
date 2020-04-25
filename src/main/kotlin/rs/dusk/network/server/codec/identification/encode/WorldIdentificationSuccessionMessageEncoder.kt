package rs.dusk.network.server.codec.identification.encode

import rs.dusk.core.network.codec.packet.access.PacketWriter
import rs.dusk.core.network.model.packet.PacketType.FIXED
import rs.dusk.network.server.codec.identification.SocialServerIdentificationMessageEncoder
import rs.dusk.network.server.codec.identification.encode.message.WorldIdentificationSuccessionMessage
import rs.dusk.utility.SocialOpcodes.IDENTIFICATION_SUCCESSION_MESSAGE_OPCODE

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 25, 2020
 */
class WorldIdentificationSuccessionMessageEncoder :
    SocialServerIdentificationMessageEncoder<WorldIdentificationSuccessionMessage>() {

    override fun encode(builder: PacketWriter, msg: WorldIdentificationSuccessionMessage) {
        builder.writeOpcode(IDENTIFICATION_SUCCESSION_MESSAGE_OPCODE, FIXED)
        builder.writeByte(if (msg.success) 1 else 0)
    }

}