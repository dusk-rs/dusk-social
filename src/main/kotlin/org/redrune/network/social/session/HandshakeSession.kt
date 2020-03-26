package org.redrune.network.social.session

import io.netty.channel.Channel
import org.redrune.core.network.model.session.Session

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
class HandshakeSession(channel: Channel) : Session(channel) {

    /**
     * If the build version has been verified
     */
    var buildVerified = false
}