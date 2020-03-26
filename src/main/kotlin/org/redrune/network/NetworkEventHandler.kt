package org.redrune.network

import io.netty.channel.ChannelHandlerContext
import org.redrune.core.network.connection.ConnectionEvent
import org.redrune.core.network.model.session.setSession
import org.redrune.network.social.session.HandshakeSession

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
class NetworkEventHandler : ConnectionEvent {

    override fun onConnect(ctx: ChannelHandlerContext) {
        ctx.channel().setSession(HandshakeSession(ctx.channel()))
    }

    override fun onDisconnect(ctx: ChannelHandlerContext) {
        TODO("implement this")
    }

    override fun onException(ctx: ChannelHandlerContext, exception: Throwable) {
        TODO("implement this")
    }

    override fun onInactive(ctx: ChannelHandlerContext) {
        TODO("implement this")
    }
}