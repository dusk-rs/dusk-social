package rs.dusk.social

import org.koin.dsl.module
import rs.dusk.social.network.session.RelaySession

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since May 13, 2020
 */
// TODO finish this
class SocialServerRepository {
	
	private val map = mutableMapOf<Int, RelaySession>()
	
	
	
	
}

val repositoryModule = module {
	single { SocialServerRepository() }
}