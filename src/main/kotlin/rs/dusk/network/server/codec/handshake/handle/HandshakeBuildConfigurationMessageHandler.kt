package rs.dusk.network.server.codec.handshake.handle

import com.github.michaelbull.logging.InlineLogger
import io.netty.channel.ChannelHandlerContext
import rs.dusk.core.network.model.session.getSession
import rs.dusk.network.client.codec.handshake.encode.message.HandshakeBuildConfigurationMessage
import rs.dusk.network.server.codec.handshake.SocialServerHandshakeMessageHandler
import rs.dusk.network.server.codec.handshake.SocialServerHandshakeSession
import rs.dusk.utility.SocialConstants

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class HandshakeBuildConfigurationMessageHandler :
    SocialServerHandshakeMessageHandler<HandshakeBuildConfigurationMessage>() {

    private val logger = InlineLogger()

    override fun handle(ctx: ChannelHandlerContext, msg: HandshakeBuildConfigurationMessage) {
        if (msg.buildMajor != SocialConstants.MAJOR_BUILD) {
            logger.info { "Build major ${msg.buildMajor} received, denied handshake!" }
            return
        }
        if (msg.buildMinor != SocialConstants.MINOR_BUILD) {
            logger.info { "Build minor ${msg.buildMinor} received, denied handshake!" }
            return
        }
        val session = ctx.channel().getSession() as SocialServerHandshakeSession
        session.onSuccession()
        logger.info { "Session $session verified " }
    }

}