package rs.dusk.network.client.codec.handshake.encode.message

import org.redrune.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class HandshakeBuildConfigurationMessage(val buildMajor: Int, val buildMinor: Int) : Message