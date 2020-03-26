package org.redrune.network.social.codec.handshake.handle

import io.netty.channel.ChannelHandlerContext
import org.redrune.network.social.codec.handshake.HandshakeMessageHandler
import org.redrune.network.social.codec.handshake.encode.message.HandshakeRegistryMessage

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
class HandshakeRegistryMessageHandler : HandshakeMessageHandler<HandshakeRegistryMessage>() {

    override fun handle(ctx: ChannelHandlerContext, msg: HandshakeRegistryMessage) {

    }
}