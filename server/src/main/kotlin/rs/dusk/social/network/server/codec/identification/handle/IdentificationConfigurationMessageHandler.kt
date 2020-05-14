package rs.dusk.social.network.server.codec.identification.handle

import com.github.michaelbull.logging.InlineLogger
import io.netty.channel.ChannelHandlerContext
import rs.dusk.social.SocialServerRepository
import rs.dusk.social.network.codec.identification.IdentificationMessageHandler
import rs.dusk.social.network.codec.identification.message.IdentificationConfigurationMessage
import rs.dusk.social.utility.inject

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
class IdentificationConfigurationMessageHandler : IdentificationMessageHandler<IdentificationConfigurationMessage>() {
	
	/**
	 * The repository for the social server
	 */
	private val repository : SocialServerRepository by inject()
	
	private val logger = InlineLogger()
	
	override fun handle(ctx : ChannelHandlerContext, msg : IdentificationConfigurationMessage) : Unit = with(msg) {
		val channel = ctx.channel()
		
		// TODO: finish social server registry
//		val successful
//		channel.writeAndFlush(IdentificationResponseMessage(successful))
		
		logger.info { "$this" }
	}
	
}