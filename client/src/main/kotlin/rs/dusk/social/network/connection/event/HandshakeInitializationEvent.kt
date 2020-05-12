package rs.dusk.social.network.connection.event

import com.github.michaelbull.logging.InlineLogger
import io.netty.channel.ChannelHandlerContext
import rs.dusk.core.network.connection.event.ChannelEvent
import rs.dusk.social.network.codec.handshake.encode.message.HandshakeBuildConfigurationMessage
import rs.dusk.social.utility.constant.SocialConstants.BUILD_MAJOR
import rs.dusk.social.utility.constant.SocialConstants.BUILD_MINOR

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 11, 2020
 */
class HandshakeInitializationEvent : ChannelEvent {
	
	private val logger = InlineLogger()
	
	override fun run(ctx : ChannelHandlerContext, cause : Throwable?) {
		ctx.channel()
			.writeAndFlush(HandshakeBuildConfigurationMessage(BUILD_MAJOR, BUILD_MINOR))
		logger.info { "Handshake initialization packet sent [HandshakeBuildConfigurationMessage]" }
	}
}