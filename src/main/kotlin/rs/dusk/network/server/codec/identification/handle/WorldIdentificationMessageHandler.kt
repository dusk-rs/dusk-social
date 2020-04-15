package rs.dusk.network.server.codec.identification.handle

import com.github.michaelbull.logging.InlineLogger
import io.netty.channel.ChannelHandlerContext
import rs.dusk.core.network.model.session.getSession
import rs.dusk.network.client.codec.identification.encode.message.WorldIdentificationMessage
import rs.dusk.network.server.codec.identification.SocialServerIdentificationMessageHandler
import rs.dusk.network.server.codec.identification.SocialServerIdentificationSession
import rs.dusk.social.SocialManager
import rs.dusk.social.world.WorldType
import rs.dusk.utility.inject

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 08, 2020
 */
class WorldIdentificationMessageHandler : SocialServerIdentificationMessageHandler<WorldIdentificationMessage>() {

    private val logger = InlineLogger()

    /**
     * The social server which this event is managing
     */
    private val manager by inject<SocialManager>()

    override fun handle(ctx: ChannelHandlerContext, msg: WorldIdentificationMessage) {
        val id = msg.id.toInt()
        val type = WorldType.values()[msg.type.toInt()]
        val session = ctx.channel().getSession() as SocialServerIdentificationSession
        if (!manager.worldRepository.addWorld(id, type)) {
            logger.info { "Unable to add a new world! [msg=$msg]"}
            return
        }
        logger.info { "Successfully registered a new world! [msg=$msg]" }
        session.onSuccession()
    }

}