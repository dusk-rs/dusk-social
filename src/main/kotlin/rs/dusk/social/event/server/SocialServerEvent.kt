package rs.dusk.social.event.server

import rs.dusk.social.SocialManager
import rs.dusk.utility.inject

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