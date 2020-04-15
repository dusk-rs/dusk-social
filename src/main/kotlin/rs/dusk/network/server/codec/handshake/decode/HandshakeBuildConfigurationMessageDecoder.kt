package rs.dusk.network.server.codec.handshake.decode

import org.redrune.core.network.codec.packet.access.PacketReader
import org.redrune.core.network.model.packet.PacketMetaData
import org.redrune.network.client.codec.handshake.encode.message.HandshakeBuildConfigurationMessage
import org.redrune.network.server.codec.handshake.SocialServerHandshakeMessageDecoder
import org.redrune.utility.SocialOpcodes.BUILD_CONFIGURATION_OPCODE

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
@PacketMetaData(opcodes = [BUILD_CONFIGURATION_OPCODE], length = 2)
class HandshakeBuildConfigurationMessageDecoder :
    SocialServerHandshakeMessageDecoder<HandshakeBuildConfigurationMessage>() {

    override fun decode(packet: PacketReader): HandshakeBuildConfigurationMessage {
        val buildMajor = packet.readByte()
        val buildMinor = packet.readByte()
        return HandshakeBuildConfigurationMessage(buildMajor, buildMinor)
    }
}