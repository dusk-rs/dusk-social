package rs.dusk.social

import org.redrune.social.world.World
import org.redrune.social.world.WorldType

/**
 * This repository contains data about the game worlds that are currently connected.
 *
 * @author Tyluur <contact@kiaira.tech>
 * @since March 31, 2020
 */
class SocialWorldRepository {

    /**
     * All the worlds currently connected
     */
    val connectedWorlds = mutableMapOf<Pair<Int, WorldType>, World>()

    /**
     * Adding a [World] is done by its the unique [World.pair] combination.
     * @return Boolean On successful addition
     */
    fun addWorld(id: Int, type: WorldType): Boolean {
        val pair = Pair(id, type)
        if (connectedWorlds.containsKey(pair)) {
            return false
        }
        connectedWorlds[pair] = World(id, type)
        return true
    }

    /**
     * The removal of a world is done using the unique [World.pair] combination
     * @return Boolean True on successful removal
     */
    fun removeWorld(world: World): Boolean {
        connectedWorlds.remove(world.pair) ?: return false
        return true
    }

}