package org.redrune.network.social.codec.handshake.encode

import org.redrune.core.network.codec.packet.access.PacketBuilder
import org.redrune.network.social.codec.handshake.HandshakeMessageEncoder
import org.redrune.network.social.codec.handshake.encode.message.HandshakeRegistryMessage

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
class HandshakeRegistryMessageEncoder : HandshakeMessageEncoder<HandshakeRegistryMessage>() {

    override fun encode(builder: PacketBuilder, msg: HandshakeRegistryMessage) {

    }

}