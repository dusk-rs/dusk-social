package rs.dusk.network

import com.github.michaelbull.logging.InlineLogger
import io.netty.channel.ChannelHandlerContext
import org.redrune.core.network.connection.ConnectionEvent
import org.redrune.core.network.model.session.Session
import org.redrune.core.network.model.session.setSession
import org.redrune.network.server.codec.handshake.SocialServerHandshakeSession
import org.redrune.social.SocialManager
import org.redrune.utility.inject

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
class ServerNetworkEventHandler(private val session: Session) : ConnectionEvent {

    private val logger = InlineLogger()

    /**
     * The social server that is used for the event
     */
    private val manager by inject<SocialManager>()

    override fun onActive(ctx: ChannelHandlerContext) {
        ctx.channel().setSession(session)
        logger.info { "Session $session has just connected" }
    }

    override fun onRegistration(ctx: ChannelHandlerContext) {
        logger.info { "Registration to ${session.getDestinationIp()} succeeded" }
    }

    override fun onDeregistration(ctx: ChannelHandlerContext) {
        logger.info { "Session $session has just disconnected" }
    }

    override fun onException(ctx: ChannelHandlerContext, exception: Throwable) {
        exception.printStackTrace()
    }

    override fun onInactive(ctx: ChannelHandlerContext) {
        logger.info { "Session $session is inactive" }
    }
}