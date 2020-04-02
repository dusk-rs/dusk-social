package org.redrune.network.server.codec.handshake.handle

import com.github.michaelbull.logging.InlineLogger
import io.netty.channel.ChannelHandlerContext
import org.redrune.core.network.model.session.getSession
import org.redrune.network.client.codec.handshake.encode.message.HandshakeBuildConfigurationMessage
import org.redrune.network.server.SocialServerSession
import org.redrune.network.server.codec.handshake.SocialServerHandshakeMessageHandler
import org.redrune.network.server.codec.handshake.encode.message.HandshakeBuildVerificationMessage
import org.redrune.utility.SocialConstants

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class HandshakeBuildConfigurationMessageHandler :
    SocialServerHandshakeMessageHandler<HandshakeBuildConfigurationMessage>() {

    private val logger = InlineLogger()

    override fun handle(ctx: ChannelHandlerContext, msg: HandshakeBuildConfigurationMessage) {
        if (msg.buildMajor != SocialConstants.MAJOR_BUILD) {
            return
        }
        if (msg.buildMinor != SocialConstants.MINOR_BUILD) {
            return
        }
        val session = ctx.channel().getSession() as SocialServerSession
        session.verify()
        session.send(HandshakeBuildVerificationMessage(true))
        logger.info { "Session $session verified " }
    }

}