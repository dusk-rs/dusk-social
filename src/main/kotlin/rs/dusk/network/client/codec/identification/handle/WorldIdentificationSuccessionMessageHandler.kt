package rs.dusk.network.client.codec.identification.handle

import com.github.michaelbull.logging.InlineLogger
import io.netty.channel.ChannelHandlerContext
import rs.dusk.core.network.model.session.getSession
import rs.dusk.network.client.codec.identification.SocialClientIdentificationMessageHandler
import rs.dusk.network.client.codec.identification.SocialClientIdentificationSession
import rs.dusk.network.server.codec.identification.encode.message.WorldIdentificationSuccessionMessage

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 25, 2020
 */
class WorldIdentificationSuccessionMessageHandler :
    SocialClientIdentificationMessageHandler<WorldIdentificationSuccessionMessage>() {

    private val logger = InlineLogger()

    override fun handle(ctx: ChannelHandlerContext, msg: WorldIdentificationSuccessionMessage) {
        if (!msg.success) {
            return
        }
        val session = ctx.channel().getSession() as SocialClientIdentificationSession
        session.onSuccession()
        logger.info { "World identified successfully!" }
    }

}