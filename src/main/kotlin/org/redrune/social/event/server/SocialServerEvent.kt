package org.redrune.social.event.server

import org.redrune.network.server.SocialServer
import org.redrune.social.SocialManager
import org.redrune.utility.inject

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
abstract class SocialServerEvent {

    /**
     * The social server that is used for the event
     */
    protected val manager by inject<SocialManager>()

    /**
     * The execution of the event is handled here
     */
    abstract fun execute()
}