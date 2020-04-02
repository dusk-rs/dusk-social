package org.redrune.network

import com.github.michaelbull.logging.InlineLogger
import io.netty.channel.ChannelHandlerContext
import org.redrune.core.network.connection.ConnectionEvent
import org.redrune.core.network.model.session.getSession
import org.redrune.core.network.model.session.setSession
import org.redrune.network.server.SocialServerSession
import org.redrune.social.SocialManager
import org.redrune.utility.inject

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
class ServerNetworkEventHandler : ConnectionEvent {

    private val logger = InlineLogger()

    /**
     * The social server that is used for the event
     */
    private val manager by inject<SocialManager>()

    override fun onActive(ctx: ChannelHandlerContext) {
        val session = SocialServerSession(ctx.channel())
        ctx.channel().setSession(session)
        logger.info { "Session $session has just connected" }
    }

    override fun onRegistration(ctx: ChannelHandlerContext) {
        val session = SocialServerSession(ctx.channel())
        logger.info { "Registration to ${session.getDestinationIp()} succeeded" }
    }

    override fun onDeregistration(ctx: ChannelHandlerContext) {
        val session = ctx.channel().getSession()
        logger.info { "Session $session has just disconnected" }
    }

    override fun onException(ctx: ChannelHandlerContext, exception: Throwable) {
        logger.error { exception }
    }

    override fun onInactive(ctx: ChannelHandlerContext) {
        val session = ctx.channel().getSession()
        logger.info { "Session $session is inactive" }
    }
}