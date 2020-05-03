package rs.dusk.network.client

import rs.dusk.core.network.model.session.Session

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
class SocialClientConnectionEventHandler(private val session: Session) {/* : ConnectionEvent {

    private val logger = InlineLogger()

    override fun onActive(ctx: ChannelHandlerContext) {
        ctx.channel().setSession(session)
        session.send(HandshakeBuildConfigurationMessage(MAJOR_BUILD, MINOR_BUILD))
        logger.info { "Registered to ${session.getDestinationIp()} successfully" }
    }

    override fun onRegistration(ctx: ChannelHandlerContext) {
        logger.info { "Registration to ${session.getDestinationIp()} succeeded" }
    }

    override fun onDeregistration(ctx: ChannelHandlerContext) {
        logger.info { "Session $session has just disconnected" }
    }

    override fun onException(ctx: ChannelHandlerContext, exception: Throwable) {
        exception.printStackTrace()
//        logger.error { exception }
    }

    override fun onInactive(ctx: ChannelHandlerContext) {
        logger.info { "Session $session is inactive" }
    }*/
}