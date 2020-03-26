package org.redrune.network.social.codec.handshake.decode

import org.redrune.core.network.codec.packet.access.PacketReader
import org.redrune.core.network.model.packet.PacketMetaData
import org.redrune.network.social.codec.handshake.HandshakeMessageDecoder
import org.redrune.network.social.codec.handshake.decode.message.HandshakeBuildMessage
import org.redrune.utility.SocialOpcodes
import kotlin.math.min

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
@PacketMetaData(opcodes = [SocialOpcodes.BUILD_VERIFICATION_OPCODE], length = 2)
class HandshakeBuildMessageDecoder : HandshakeMessageDecoder<HandshakeBuildMessage>() {

    override fun decode(packet: PacketReader): HandshakeBuildMessage {
        val major = packet.readByte()
        val minor = packet.readByte()
        return HandshakeBuildMessage(major, minor)
    }

}