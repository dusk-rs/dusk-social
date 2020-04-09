package org.redrune.network.client.codec.identification

import io.netty.channel.Channel
import org.redrune.core.network.codec.message.MessageDecoder
import org.redrune.core.network.codec.message.MessageEncoder
import org.redrune.core.network.codec.message.MessageHandler
import org.redrune.core.network.model.message.Message
import org.redrune.core.network.model.session.Session
import org.redrune.core.network.model.session.type.VerifiableSession

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