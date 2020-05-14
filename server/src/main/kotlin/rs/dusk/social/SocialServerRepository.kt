package rs.dusk.social

import com.github.michaelbull.logging.InlineLogger
import org.koin.dsl.module
import rs.dusk.core.network.model.message.Message
import rs.dusk.core.network.model.session.Session
import rs.dusk.core.network.model.session.getSession
import rs.dusk.social.model.world.World
import rs.dusk.social.model.world.WorldPlayer
import rs.dusk.social.network.server.SocialServerFactory
import rs.dusk.social.network.session.RelaySession
import rs.dusk.social.utility.inject

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
class SocialServerRepository {
	
	private val logger = InlineLogger()
	
	/**
	 * The collection of all [worlds][World], identified by their [id][World.id]
	 */
	private val map = mutableMapOf<Int, World>()
	
	/**
	 * Registers a world by its id, and returns a boolean of whether registration succeeded
	 */
	fun register(id : Int) : World? {
		if (map.containsKey(id)) {
			return null
		}
		val world = World(id)
		map[id] = world
		return world
	}
	
	/**
	 * Finds the session of a world by its id
	 * @return Session
	 */
	fun getSessionByWorldId(id : Int) : Session? {
		val factory : SocialServerFactory by inject()
		val channel = factory.channels.find {
			val session = it.getSession()
			session is RelaySession && session.worldId == id
		}
		logger.info { "Finding session from world id [id=$id] [channel=$channel], [ids=${map.keys}]" }
		return channel?.getSession()
	}
	
	/**
	 * Relays a [message][Message] to every [world][World] except the [source world][srcWorldId]
	 */
	fun relayMessage(srcWorldId : Int, message : Message) {
		val factory : SocialServerFactory by inject()
		val channel = factory.channels.filter {
			val session = it.getSession()
			session is RelaySession && session.worldId != srcWorldId
		}
		var count = 0
		for (it in channel) {
			it.writeAndFlush(message)
			count++
		}
		logger.info { "Sent message [$message] to ${count}/${channel.size} channels" }
	}
	
	/**
	 * Finds a [player][WorldPlayer] from any  [world][World]
	 */
	fun findPlayer(username : String) : WorldPlayer? {
		val iterator = map.iterator()
		var player : WorldPlayer? = null
		while (iterator.hasNext()) {
			val world = iterator.next().value
			val playerFound = world.getPlayerByUsername(username)
			if (playerFound != null) {
				player = playerFound
				break
			}
		}
		return player
	}
	
}

val repositoryModule = module {
	single { SocialServerRepository() }
}
