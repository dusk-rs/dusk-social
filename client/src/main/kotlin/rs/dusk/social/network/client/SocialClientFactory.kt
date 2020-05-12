package rs.dusk.social.network.client

import rs.dusk.core.network.connection.ConnectionFactory
import rs.dusk.core.network.connection.ConnectionPipeline
import rs.dusk.core.network.connection.event.ChannelEventChain
import rs.dusk.core.network.connection.event.ChannelEventType.ACTIVE
import rs.dusk.core.network.connection.event.ChannelEventType.INACTIVE
import rs.dusk.core.network.connection.event.type.ChannelActiveEvent
import rs.dusk.core.network.connection.event.type.ChannelInactiveEvent

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 07, 2020
 */
class SocialClientFactory : ConnectionFactory() {
	
	fun connect(client : SocialClient, chain : ChannelEventChain, pipeline : ConnectionPipeline) = with(chain) {
		append(ACTIVE, ChannelActiveEvent(client, channels))
		append(INACTIVE, ChannelInactiveEvent(client, channels))
		
		client.configure(pipeline)
		client.connect()
	}
	
	
}