package org.redrune.network.client.codec.handshake.decode

import org.redrune.core.network.codec.packet.access.PacketReader
import org.redrune.core.network.model.packet.PacketMetaData
import org.redrune.core.network.model.packet.PacketType
import org.redrune.core.network.model.packet.PacketType.Companion.VARIABLE_LENGTH_BYTE
import org.redrune.network.client.codec.handshake.SocialClientHandshakeMessageDecoder
import org.redrune.network.server.codec.handshake.encode.message.HandshakeBuildVerificationMessage
import org.redrune.utility.SocialOpcodes

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
@PacketMetaData(opcodes = [SocialOpcodes.BUILD_VERIFICATION_OPCODE], length = 1)
class HandshakeBuildVerificationMessageDecoder :
    SocialClientHandshakeMessageDecoder<HandshakeBuildVerificationMessage>() {

    override fun decode(packet: PacketReader): HandshakeBuildVerificationMessage {
        val verified = packet.readByte() == 1
        return HandshakeBuildVerificationMessage(verified)
    }

}