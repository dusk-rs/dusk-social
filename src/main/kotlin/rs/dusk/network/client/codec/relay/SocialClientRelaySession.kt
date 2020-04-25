package rs.dusk.network.client.codec.relay

import io.netty.channel.Channel
import rs.dusk.core.network.model.session.Session

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 25, 2020
 */
class SocialClientRelaySession(private val channel: Channel) : Session(channel) {


}