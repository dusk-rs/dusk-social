package rs.dusk.network.server

import rs.dusk.core.network.model.session.Session

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
class SocialServerConnectionEventHandler(private val session: Session) {/*: ConnectionEvent {

    private val logger = InlineLogger()

    */
    /**
     * The social server that is used for the event
     *//*
    private val manager by inject<SocialServerManager>()

    override fun onActive(ctx: ChannelHandlerContext) {
        ctx.channel().setSession(session)
        logger.info { "Session $session has just connected" }
    }

    override fun onRegistration(ctx: ChannelHandlerContext) {
        logger.info { "Registration to ${session.getDestinationIp()} succeeded" }
    }

    override fun onDeregistration(ctx: ChannelHandlerContext) {
        val world = ctx.channel().getWorld()
        if (world != null) {
            manager.removeWorld(world)
        }
        logger.info { "Session $session has just disconnected" }
    }

    override fun onException(ctx: ChannelHandlerContext, exception: Throwable) {
        exception.printStackTrace()
    }

    override fun onInactive(ctx: ChannelHandlerContext) {
        logger.info { "Session $session is inactive" }
    }*/
}