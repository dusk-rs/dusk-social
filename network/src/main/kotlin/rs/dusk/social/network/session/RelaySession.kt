package rs.dusk.social.network.session

import io.netty.channel.Channel
import rs.dusk.core.network.model.session.Session

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 12, 2020
 */
class RelaySession(
	val worldId : Int,
	channel : Channel
) : Session(channel) {

}

