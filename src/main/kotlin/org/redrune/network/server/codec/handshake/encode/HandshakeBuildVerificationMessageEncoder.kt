package org.redrune.network.server.codec.handshake.encode

import org.redrune.core.network.codec.packet.access.PacketBuilder
import org.redrune.core.network.model.packet.PacketType
import org.redrune.network.server.codec.handshake.SocialServerHandshakeMessageEncoder
import org.redrune.network.server.codec.handshake.encode.message.HandshakeBuildVerificationMessage
import org.redrune.utility.SocialOpcodes

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class HandshakeBuildVerificationMessageEncoder : SocialServerHandshakeMessageEncoder<HandshakeBuildVerificationMessage>() {

    override fun encode(builder: PacketBuilder, msg: HandshakeBuildVerificationMessage) {
        println("encoding msg")
        builder.writeOpcode(SocialOpcodes.BUILD_VERIFICATION_OPCODE, PacketType.FIXED)
        builder.writeByte(if (msg.verified) 1 else 0)
    }

}