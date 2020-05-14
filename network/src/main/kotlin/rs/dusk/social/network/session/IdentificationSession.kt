package rs.dusk.social.network.session

import io.netty.channel.Channel
import rs.dusk.core.network.model.session.Session

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
class IdentificationSession(channel : Channel) : Session(channel)