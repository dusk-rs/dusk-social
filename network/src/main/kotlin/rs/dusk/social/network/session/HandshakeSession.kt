package rs.dusk.social.network.session

import io.netty.channel.Channel
import rs.dusk.core.network.model.session.Session

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 11, 2020
 */
class HandshakeSession(channel : Channel) : Session(channel)