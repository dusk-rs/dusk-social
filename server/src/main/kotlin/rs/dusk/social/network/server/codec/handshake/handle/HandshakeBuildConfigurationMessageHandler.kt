package rs.dusk.social.network.server.codec.handshake.handle

import com.github.michaelbull.logging.InlineLogger
import io.netty.channel.ChannelHandlerContext
import rs.dusk.core.network.codec.CodecRepository
import rs.dusk.core.network.codec.setCodec
import rs.dusk.core.network.model.session.setSession
import rs.dusk.social.network.codec.handshake.HandshakeMessageHandler
import rs.dusk.social.network.codec.handshake.message.HandshakeBuildConfigurationMessage
import rs.dusk.social.network.codec.handshake.message.HandshakeResponseMessage
import rs.dusk.social.network.codec.identification.IdentificationCodec
import rs.dusk.social.network.session.IdentificationSession
import rs.dusk.social.utility.constant.SocialConstants.BUILD_MAJOR
import rs.dusk.social.utility.constant.SocialConstants.BUILD_MINOR
import rs.dusk.social.utility.inject

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 12, 2020
 */
class HandshakeBuildConfigurationMessageHandler : HandshakeMessageHandler<HandshakeBuildConfigurationMessage>() {
	
	private val logger = InlineLogger()
	
	private val repository : CodecRepository by inject()
	
	override fun handle(ctx : ChannelHandlerContext, msg : HandshakeBuildConfigurationMessage) {
		val channel = ctx.channel()
		
		// handshake being successful is dependent on the build major and minor lining up with the current ones
		val successful = msg.major == BUILD_MAJOR && msg.minor == BUILD_MINOR
		
		// send the response mesasge
		ctx.channel().writeAndFlush(HandshakeResponseMessage(successful))
		
		// update codec once written
		channel.setCodec(repository.get(IdentificationCodec::class))
		channel.setSession(IdentificationSession(channel))
		
		logger.debug { "Handshake was ${(if (successful) "" else "un") + "successful"} and this was sent to the client" }
	}
	
}