package org.redrune.network.server.codec.handshake.decode

import org.redrune.core.network.codec.packet.access.PacketReader
import org.redrune.core.network.model.packet.PacketMetaData
import org.redrune.core.network.model.packet.PacketType.Companion.VARIABLE_LENGTH_BYTE
import org.redrune.network.client.codec.handshake.encode.message.HandshakeBuildConfigurationMessage
import org.redrune.network.server.codec.handshake.SocialServerHandshakeMessageDecoder
import org.redrune.utility.SocialOpcodes.BUILD_CONFIGURATION_OPCODE

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
@PacketMetaData(opcodes = [BUILD_CONFIGURATION_OPCODE], length = VARIABLE_LENGTH_BYTE)
class HandshakeBuildConfigurationMessageDecoder :
    SocialServerHandshakeMessageDecoder<HandshakeBuildConfigurationMessage>() {

    override fun decode(packet: PacketReader): HandshakeBuildConfigurationMessage {
        println("decoding msg")
        val buildMajor = packet.readByte()
        println("read $buildMajor")
        val buildMinor = packet.readByte()
        println("read $buildMinor")

        return HandshakeBuildConfigurationMessage(buildMajor, buildMinor)
    }
}