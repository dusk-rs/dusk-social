package org.redrune.network.client.codec.handshake.handle

import com.github.michaelbull.logging.InlineLogger
import io.netty.channel.ChannelHandlerContext
import org.redrune.core.network.model.session.getSession
import org.redrune.network.client.codec.handshake.SocialClientHandshakeMessageHandler
import org.redrune.network.client.codec.handshake.SocialClientHandshakeSession
import org.redrune.network.server.codec.handshake.encode.message.HandshakeBuildVerificationMessage

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class HandshakeBuildVerificationMessageHandler :
    SocialClientHandshakeMessageHandler<HandshakeBuildVerificationMessage>() {

    private val logger = InlineLogger()

    override fun handle(ctx: ChannelHandlerContext, msg: HandshakeBuildVerificationMessage) {
        val session = ctx.channel().getSession() as SocialClientHandshakeSession
        if (!msg.verified) {
            logger.info { "Verification with session $session failed." }
            return
        }
        session.onSuccession()
        logger.info { "Handshake complete and verified" }
    }
}