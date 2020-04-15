package rs.dusk.social

import com.github.michaelbull.logging.InlineLogger
import org.redrune.social.event.server.SocialServerEvent
import org.redrune.utility.inject
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class SocialTaskWorker {

    private val logger = InlineLogger()

    /**
     * The thread pool used for event execution
     */
    private val service = Executors.newSingleThreadScheduledExecutor()

    /**
     * The events that are awaiting [SocialServerEvent.execute]
     */
    private val pendingEvents = Collections.synchronizedList(mutableListOf<SocialServerEvent>())

    /**
     * The social server which this event is managing
     */
    private val manager by inject<SocialManager>()

    /**
     * Handling the processing of events synchronously
     */
    private val processEvents = {
        synchronized(pendingEvents) {
            val iterator = pendingEvents.iterator()
            while (iterator.hasNext()) {
                val event = iterator.next()
                event.execute()
                iterator.remove()
                logger.info { "Successfully executed and removed event $event" }
            }
        }
    }

    private val processConnections = {
        val unverifiedWorlds = manager.worldRepository.connectedWorlds.filter { !it.value.verified }.values

    }

    fun run() {
        service.scheduleWithFixedDelay({ processEvents }, 1, 1, TimeUnit.SECONDS)
        service.scheduleWithFixedDelay({ processConnections }, 10, 10, TimeUnit.SECONDS)
        logger.info { "Successfully started the social manager" }
    }
}