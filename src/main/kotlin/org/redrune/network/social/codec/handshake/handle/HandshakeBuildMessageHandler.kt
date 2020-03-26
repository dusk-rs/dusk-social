package org.redrune.network.social.codec.handshake.handle

import io.netty.channel.ChannelHandlerContext
import org.redrune.core.network.model.session.getSession
import org.redrune.network.social.codec.handshake.HandshakeMessageHandler
import org.redrune.network.social.codec.handshake.decode.message.HandshakeBuildMessage
import org.redrune.network.social.session.HandshakeSession
import org.redrune.utility.SocialConstants

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
class HandshakeBuildMessageHandler : HandshakeMessageHandler<HandshakeBuildMessage>() {

    override fun handle(ctx: ChannelHandlerContext, msg: HandshakeBuildMessage) {
        if (msg.major != SocialConstants.MAJOR_BUILD) {
            return
        }
        if (msg.minor != SocialConstants.MINOR_BUILD) {
            return
        }
        val session = ctx.channel().getSession()
        if (session is HandshakeSession) {
            session.buildVerified = true
        }
    }

}