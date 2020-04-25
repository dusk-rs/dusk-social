package rs.dusk.network.client.codec.identification.decode

import rs.dusk.core.network.codec.packet.access.PacketReader
import rs.dusk.core.network.model.packet.PacketMetaData
import rs.dusk.network.client.codec.identification.SocialClientIdentificationMessageDecoder
import rs.dusk.network.server.codec.identification.encode.message.WorldIdentificationSuccessionMessage
import rs.dusk.utility.SocialOpcodes

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 25, 2020
 */
@PacketMetaData(opcodes = [SocialOpcodes.IDENTIFICATION_SUCCESSION_MESSAGE_OPCODE], length = 1)
class WorldIdentificationSuccessionMessageDecoder :
    SocialClientIdentificationMessageDecoder<WorldIdentificationSuccessionMessage>() {

    override fun decode(packet: PacketReader): WorldIdentificationSuccessionMessage {
        val success = packet.readByte() == 1
        return WorldIdentificationSuccessionMessage(success)
    }

}