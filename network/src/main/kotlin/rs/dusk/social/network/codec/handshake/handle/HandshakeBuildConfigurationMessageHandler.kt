package rs.dusk.social.network.codec.handshake.handle

import io.netty.channel.ChannelHandlerContext
import rs.dusk.social.network.codec.handshake.HandshakeMessageHandler
import rs.dusk.social.network.codec.handshake.encode.message.HandshakeBuildConfigurationMessage

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 12, 2020
 */
class HandshakeBuildConfigurationMessageHandler : HandshakeMessageHandler<HandshakeBuildConfigurationMessage>() {
	
	override fun handle(ctx : ChannelHandlerContext, msg : HandshakeBuildConfigurationMessage) {
		// TODO write handling
		
		println("${msg.major}, ${msg.minor}")
	}
	
}