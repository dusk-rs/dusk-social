package rs.dusk.social.network.server.codec.relay.handle

import io.netty.channel.ChannelHandlerContext
import rs.dusk.social.SocialServerRepository
import rs.dusk.social.network.codec.relay.RelayMessageHandler
import rs.dusk.social.network.codec.relay.message.login.LoginRequestMessage
import rs.dusk.social.utility.inject

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
class LoginRequestMessageHandler : RelayMessageHandler<LoginRequestMessage>() {
	
	/**
	 * The repository for the social server
	 */
	private val repository : SocialServerRepository by inject()
	
	override fun handle(ctx : ChannelHandlerContext, msg : LoginRequestMessage) = with(msg) {
		val existingPlayer = repository.findPlayer(username)
		
	}
	
	
}