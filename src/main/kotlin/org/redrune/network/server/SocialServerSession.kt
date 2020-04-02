package org.redrune.network.server

import io.netty.channel.Channel
import org.redrune.core.network.model.session.Session

/**
 * @author Tyluur <contact@kiaira.tech>
* @since March 26, 2020
*/
class SocialServerSession(channel: Channel) : Session(channel) {

    /**
     * If the build version has been verified
     */
    var buildVerified = false

    fun verify() {
        // TODO update pipeline with new codec for interacation

        buildVerified = true
    }
}