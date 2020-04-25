package rs.dusk.social.server

import org.koin.dsl.module
import rs.dusk.core.network.model.session.Session
import rs.dusk.social.world.World
import rs.dusk.social.world.WorldType

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class SocialServerManager {

    /**
     * All the worlds currently connected
     */
    private val connectedWorlds = mutableMapOf<Pair<Int, WorldType>, World>()

    /**
     * All of the live sessions
     */
    private val sessions = mutableMapOf<Session, World>()

    /**
     * Adding a [World] is done by its the unique [World.pair] combination.
     * @return Boolean On successful addition
     */
    fun addWorld(id: Int, type: WorldType): World? {
        val pair = Pair(id, type)
        if (connectedWorlds.containsKey(pair)) {
            return null
        }
        val world = World(id, type)
        connectedWorlds[pair] = world
        return world
    }

    /**
     * The removal of a world is done using the unique [World.pair] combination
     * @return Boolean True on successful removal
     */
    fun removeWorld(world: World): World? {
        return connectedWorlds.remove(world.pair)
    }

}

val managerModule = module {
    single(createdAtStart = true) { SocialServerManager() }
}