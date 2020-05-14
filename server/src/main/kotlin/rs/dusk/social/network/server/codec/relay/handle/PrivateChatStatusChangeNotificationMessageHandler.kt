package rs.dusk.social.network.server.codec.relay.handle

import com.github.michaelbull.logging.InlineLogger
import io.netty.channel.ChannelHandlerContext
import rs.dusk.social.SocialServerRepository
import rs.dusk.social.network.codec.relay.RelayMessageHandler
import rs.dusk.social.network.codec.relay.message.friend.PrivateChatStatusChangeMessage
import rs.dusk.social.utility.inject

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
class PrivateChatStatusChangeNotificationMessageHandler : RelayMessageHandler<PrivateChatStatusChangeMessage>() {
	
	private val logger = InlineLogger()
	
	/**
	 * The repository for the social server
	 */
	private val repository : SocialServerRepository by inject()
	
	override fun handle(ctx : ChannelHandlerContext, msg : PrivateChatStatusChangeMessage) = with(msg) {
		logger.info { "We received a private status change packet" }
		
		repository.relayMessage(-1, msg)
	}
	
	
}