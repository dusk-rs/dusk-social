package org.redrune.network.server.codec.handshake.handle

import io.netty.channel.ChannelHandlerContext
import org.redrune.network.client.codec.handshake.encode.message.HandshakeBuildConfigurationMessage
import org.redrune.network.server.codec.handshake.SocialServerHandshakeMessageHandler
import org.redrune.network.server.codec.handshake.encode.message.HandshakeBuildVerificationMessage
import org.redrune.utility.SocialConstants

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class HandshakeBuildConfigurationMessageHandler : SocialServerHandshakeMessageHandler<HandshakeBuildConfigurationMessage>() {

    override fun handle(ctx: ChannelHandlerContext, msg: HandshakeBuildConfigurationMessage) {
        println("handling msg")
        if (msg.buildMajor != SocialConstants.MAJOR_BUILD) {
            return
        }
        if (msg.buildMinor != SocialConstants.MINOR_BUILD) {
            return
        }
        ctx.channel().writeAndFlush(HandshakeBuildVerificationMessage(true))
    }

}