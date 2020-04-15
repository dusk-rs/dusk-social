package rs.dusk.network.server.codec.identification.decode

import org.redrune.core.network.codec.packet.access.PacketReader
import org.redrune.core.network.model.packet.PacketMetaData
import org.redrune.network.client.codec.identification.encode.message.WorldIdentificationMessage
import org.redrune.network.server.codec.identification.SocialServerIdentificationMessageDecoder
import org.redrune.social.world.WorldType
import org.redrune.utility.SocialOpcodes.IDENTIFICATION_MESSAGE_OPCODE

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