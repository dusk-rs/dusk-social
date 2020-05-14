package rs.dusk.social.network.client.codec.handshake.handle

import com.github.michaelbull.logging.InlineLogger
import io.netty.channel.ChannelHandlerContext
import rs.dusk.core.network.codec.CodecRepository
import rs.dusk.core.network.codec.getCodec
import rs.dusk.core.network.codec.setCodec
import rs.dusk.core.network.getClient
import rs.dusk.core.network.model.session.setSession
import rs.dusk.social.network.client.SocialClient
import rs.dusk.social.network.codec.handshake.HandshakeMessageHandler
import rs.dusk.social.network.codec.handshake.message.HandshakeResponseMessage
import rs.dusk.social.network.codec.identification.IdentificationCodec
import rs.dusk.social.network.codec.identification.message.IdentificationConfigurationMessage
import rs.dusk.social.network.session.IdentificationSession
import rs.dusk.social.utility.inject

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
class HandshakeResponseMessageHandler : HandshakeMessageHandler<HandshakeResponseMessage>() {
	
	private val logger = InlineLogger()
	
	private val repository : CodecRepository by inject()
	
	override fun handle(ctx : ChannelHandlerContext, msg : HandshakeResponseMessage) = with(msg) {
		val channel = ctx.channel()
		val client : SocialClient = channel.getClient() as SocialClient
		if (!successful) {
			logger.debug { "Handshake was unsuccessful, terminating connection..." }
			channel.close()
			return
		}
		
		channel.setCodec(repository.get(IdentificationCodec::class))
		channel.setSession(IdentificationSession(channel))
		
		channel.writeAndFlush(IdentificationConfigurationMessage(client.configuration.worldId))
		
		logger.debug { "Identification configuration message sent after handshake was successful. " }
	}
	
}