package rs.dusk.network.server.codec.identification.decode

import rs.dusk.core.network.codec.packet.access.PacketReader
import rs.dusk.core.network.model.packet.PacketMetaData
import rs.dusk.network.client.codec.identification.encode.message.WorldIdentificationMessage
import rs.dusk.network.server.codec.identification.SocialServerIdentificationMessageDecoder
import rs.dusk.social.world.WorldType
import rs.dusk.utility.SocialOpcodes.IDENTIFICATION_MESSAGE_OPCODE

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 08, 2020
 */
@PacketMetaData(opcodes = [IDENTIFICATION_MESSAGE_OPCODE], length = 2)
class WorldIdentificationMessageDecoder : SocialServerIdentificationMessageDecoder<WorldIdentificationMessage>() {

    override fun decode(packet: PacketReader): WorldIdentificationMessage {
        val id = packet.readByte().toByte()
        // the WorldType ordinal value
        val type = packet.readByte().toByte()
        if (type < 0 || type > WorldType.values().lastIndex) {
            throw IllegalStateException("Unexpected world type index received [id=$id, type=$type]")
        }
        return WorldIdentificationMessage(id, type)
    }
}