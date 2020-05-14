package rs.dusk.social.network.client.codec.identification.handle

import com.github.michaelbull.logging.InlineLogger
import io.netty.channel.ChannelHandlerContext
import rs.dusk.core.network.codec.CodecRepository
import rs.dusk.core.network.codec.setCodec
import rs.dusk.core.network.getClient
import rs.dusk.core.network.model.session.getSession
import rs.dusk.core.network.model.session.setSession
import rs.dusk.social.network.client.SocialClient
import rs.dusk.social.network.codec.identification.IdentificationMessageHandler
import rs.dusk.social.network.codec.identification.message.IdentificationResponseMessage
import rs.dusk.social.network.codec.relay.RelayCodec
import rs.dusk.social.network.codec.relay.message.friend.PrivateChatStatusChangeMessage
import rs.dusk.social.network.session.RelaySession
import rs.dusk.social.utility.inject
import kotlin.system.exitProcess

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
class IdentificationResponseMessageHandler : IdentificationMessageHandler<IdentificationResponseMessage>() {
	
	private val logger = InlineLogger()
	
	/**
	 * The repository to find codecs from
	 */
	private val repository : CodecRepository by inject()
	
	override fun handle(ctx : ChannelHandlerContext, msg : IdentificationResponseMessage) = with(msg) {
		val channel = ctx.channel()
		
		if (!successful) {
			logger.debug { "Identification was unsuccessful, terminating connection..." }
			channel.close()
			exitProcess(0)
		}
		val worldId = (channel.getClient() as SocialClient).configuration.worldId
		val session = RelaySession(worldId, channel)
		
		// change attributes
		channel.setCodec(repository.get(RelayCodec::class))
		channel.setSession(session)
		
		logger.debug { "Identification was successful, we have transferred over to the relay state." }
	}
	
}