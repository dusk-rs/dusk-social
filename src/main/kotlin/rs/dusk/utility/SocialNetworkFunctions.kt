package rs.dusk.utility

import io.netty.channel.Channel
import io.netty.util.AttributeKey
import rs.dusk.social.world.World
import rs.dusk.utility.SocialNetworkFunctions.Companion.WORLD_KEY

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 25, 2020
 */
class SocialNetworkFunctions {
    companion object {
        /**
         * The attribute in the [Channel] that identifies the world
         */
        val WORLD_KEY: AttributeKey<World> = AttributeKey.valueOf("world.key")

    }
}

/**
 * Gets the object in the [SocialNetworkFunctions.WORLD_KEY] attribute
 * @receiver Channel
 * @return Session
 */
fun Channel.getWorld(): World? {
    return attr(WORLD_KEY).get()
}

/**
 * Sets the [SocialNetworkFunctions.WORLD_KEY] attribute
 */
fun Channel.setWorld(world: World) {
    attr(WORLD_KEY).set(world)
}