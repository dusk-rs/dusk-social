package org.redrune.social

import org.koin.dsl.module

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class SocialManager {

    /**
     * The social repository for a server
     */
    val worldRepository = SocialWorldRepository()

    /**
     * The manager for social events
     */
    private val taskWorker = SocialTaskWorker()

    fun run() {
        taskWorker.run()
    }
}

val managerModule = module {
    single(createdAtStart = true) { SocialManager() }
}