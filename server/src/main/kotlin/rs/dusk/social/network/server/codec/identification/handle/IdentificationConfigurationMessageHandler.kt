package rs.dusk.social.network.server.codec.identification.handle

import com.github.michaelbull.logging.InlineLogger
import io.netty.channel.ChannelHandlerContext
import rs.dusk.core.network.codec.CodecRepository
import rs.dusk.core.network.codec.setCodec
import rs.dusk.core.network.model.session.setSession
import rs.dusk.social.SocialServerRepository
import rs.dusk.social.network.codec.identification.IdentificationMessageHandler
import rs.dusk.social.network.codec.identification.message.IdentificationConfigurationMessage
import rs.dusk.social.network.codec.identification.message.IdentificationResponseMessage
import rs.dusk.social.network.codec.relay.RelayCodec
import rs.dusk.social.network.session.RelaySession
import rs.dusk.social.utility.inject

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
class IdentificationConfigurationMessageHandler : IdentificationMessageHandler<IdentificationConfigurationMessage>() {
	
	private val logger = InlineLogger()
	
	/**
	 * The repository for the social server
	 */
	private val serverRepository : SocialServerRepository by inject()
	
	/**
	 * The repository used for codecs
	 */
	private val codecRepository : CodecRepository by inject()
	
	override fun handle(ctx : ChannelHandlerContext, msg : IdentificationConfigurationMessage) : Unit = with(msg) {
		val world = serverRepository.register(worldId)
		val successful = world != null
		val channel = ctx.channel()
		
		// send the response message
		channel.writeAndFlush(IdentificationResponseMessage(successful))
		
		// update attributes, we should be at relay now
		if (successful) {
			channel.setSession(RelaySession(worldId, channel))
			channel.setCodec(codecRepository.get(RelayCodec::class))
		}
		
		logger.debug { "Identification was ${(if (successful) "" else "un") + "successful"} and this was sent to the client" }
	}
	
}