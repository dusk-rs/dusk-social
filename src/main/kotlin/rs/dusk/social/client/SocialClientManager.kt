package rs.dusk.social.client

import org.koin.dsl.module
import rs.dusk.social.world.WorldType

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 25, 2020
 */
class SocialClientManager {

    /**
     * The id of the world this client is representing
     */
    var worldId: Int? = -1

    /**
     * The type of world this client is representing
     */
    var worldType: WorldType? = null

}

val clientManagerModule = module {
    single(createdAtStart = true) { SocialClientManager() }
}