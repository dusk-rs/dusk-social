package rs.dusk.network.client.codec.handshake.encode

import rs.dusk.core.network.codec.packet.access.PacketWriter
import rs.dusk.core.network.model.packet.PacketType
import rs.dusk.network.client.codec.handshake.SocialClientHandshakeMessageEncoder
import rs.dusk.network.client.codec.handshake.encode.message.HandshakeBuildConfigurationMessage
import rs.dusk.utility.SocialOpcodes.BUILD_CONFIGURATION_OPCODE

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class HandshakeBuildConfigurationMessageEncoder :
    SocialClientHandshakeMessageEncoder<HandshakeBuildConfigurationMessage>() {

    override fun encode(builder: PacketWriter, msg: HandshakeBuildConfigurationMessage) {
        builder.writeOpcode(BUILD_CONFIGURATION_OPCODE, PacketType.FIXED)
        builder.writeByte(msg.buildMajor)
        builder.writeByte(msg.buildMinor)
    }

}