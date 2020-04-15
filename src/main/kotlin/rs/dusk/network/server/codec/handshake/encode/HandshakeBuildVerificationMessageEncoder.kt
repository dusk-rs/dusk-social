package rs.dusk.network.server.codec.handshake.encode

import rs.dusk.core.network.codec.packet.access.PacketWriter
import rs.dusk.core.network.model.packet.PacketType
import rs.dusk.network.server.codec.handshake.SocialServerHandshakeMessageEncoder
import rs.dusk.network.server.codec.handshake.encode.message.HandshakeBuildVerificationMessage
import rs.dusk.utility.SocialOpcodes

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class HandshakeBuildVerificationMessageEncoder : SocialServerHandshakeMessageEncoder<HandshakeBuildVerificationMessage>() {

    override fun encode(builder: PacketWriter, msg: HandshakeBuildVerificationMessage) {
        println("encoding msg")
        builder.writeOpcode(SocialOpcodes.BUILD_VERIFICATION_OPCODE, PacketType.FIXED)
        builder.writeByte(if (msg.verified) 1 else 0)
    }

}