package rs.dusk.network.server.codec.relay

import io.netty.channel.Channel
import rs.dusk.core.network.model.session.Session
import rs.dusk.social.world.World

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 25, 2020
 */
class SocialServerRelaySession(private val channel: Channel): Session(channel) {


}