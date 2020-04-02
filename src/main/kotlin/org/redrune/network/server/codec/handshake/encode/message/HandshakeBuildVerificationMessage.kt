package org.redrune.network.server.codec.handshake.encode.message

import org.redrune.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class HandshakeBuildVerificationMessage(val verified: Boolean) : Message