package org.redrune.network.client.codec.handshake.encode

import org.redrune.core.network.codec.packet.access.PacketBuilder
import org.redrune.core.network.model.packet.PacketType
import org.redrune.network.client.codec.handshake.SocialClientHandshakeMessageEncoder
import org.redrune.network.client.codec.handshake.encode.message.HandshakeBuildConfigurationMessage
import org.redrune.utility.SocialOpcodes

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class HandshakeBuildConfigurationMessageEncoder :
    SocialClientHandshakeMessageEncoder<HandshakeBuildConfigurationMessage>() {

    override fun encode(builder: PacketBuilder, msg: HandshakeBuildConfigurationMessage) {
        builder.writeOpcode(SocialOpcodes.BUILD_CONFIGURATION_OPCODE, PacketType.FIXED)
        builder.writeByte(msg.buildMajor)
        builder.writeByte(msg.buildMinor)
    }

}