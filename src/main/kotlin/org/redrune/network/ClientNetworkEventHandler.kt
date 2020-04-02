package org.redrune.network

import com.github.michaelbull.logging.InlineLogger
import io.netty.channel.ChannelHandlerContext
import org.redrune.core.network.connection.ConnectionEvent
import org.redrune.core.network.model.session.getSession
import org.redrune.core.network.model.session.setSession
import org.redrune.network.client.SocialClientSession
import org.redrune.network.client.codec.handshake.encode.message.HandshakeBuildConfigurationMessage
import org.redrune.utility.SocialConstants.MAJOR_BUILD
import org.redrune.utility.SocialConstants.MINOR_BUILD

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
class ClientNetworkEventHandler : ConnectionEvent {

    private val logger = InlineLogger()

    override fun onConnect(ctx: ChannelHandlerContext) {
        val channel = ctx.channel()
        val session = SocialClientSession(channel)

        channel.setSession(session)
        session.send(HandshakeBuildConfigurationMessage(MAJOR_BUILD, MINOR_BUILD))
        ctx.fireChannelActive()

        logger.info { "Connection to ${session.getDestinationIp()} made successfully" }
    }

    override fun onDisconnect(ctx: ChannelHandlerContext) {
        val session = ctx.channel().getSession()
        logger.info { "Disconnected from ${session.getDestinationIp()} complete " }
    }

    override fun onException(ctx: ChannelHandlerContext, exception: Throwable) {

    }

    override fun onInactive(ctx: ChannelHandlerContext) {

    }
}