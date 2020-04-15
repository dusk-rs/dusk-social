package rs.dusk.social.event.client

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
abstract class SocialClientEvent<T> {

    /**
     * The execution of the event, which is to be handled by the implementing client
     */
    abstract fun execute(t: T)
}