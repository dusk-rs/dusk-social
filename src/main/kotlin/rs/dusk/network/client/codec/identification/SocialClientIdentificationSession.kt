package rs.dusk.network.client.codec.identification

import io.netty.channel.Channel
import rs.dusk.core.network.model.session.Session
import rs.dusk.core.network.model.session.type.VerifiableSession

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 08, 2020
 */
class SocialClientIdentificationSession(private val channel: Channel) : Session(channel), VerifiableSession {

    override fun onSuccession() {
        TODO("Not yet implemented")
    }

    override fun onTimeout() {
        TODO("Not yet implemented")
    }

    override fun verificationTimeout(): Long {
        TODO("Not yet implemented")
    }


}