package rs.dusk.social.network.client.codec.relay.handle

import com.github.michaelbull.logging.InlineLogger
import io.netty.channel.ChannelHandlerContext
import rs.dusk.social.network.codec.relay.RelayMessageHandler
import rs.dusk.social.network.codec.relay.message.friend.PrivateChatStatusChangeMessage

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
class PrivateChatStatusRelayMessageHandler : RelayMessageHandler<PrivateChatStatusChangeMessage>() {
	
	private val logger = InlineLogger()
	
	override fun handle(ctx : ChannelHandlerContext, msg : PrivateChatStatusChangeMessage) = with(msg) {
		
		logger.info { "recieved back! [msg=$msg]"}
	}
	
	
}