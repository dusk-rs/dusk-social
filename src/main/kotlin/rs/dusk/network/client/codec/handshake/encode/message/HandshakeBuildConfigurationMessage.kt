package rs.dusk.network.client.codec.handshake.encode.message

import rs.dusk.core.network.model.message.Message

/**
 * This message is used to transfer information about the build major and minor version of the client.
 *
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class HandshakeBuildConfigurationMessage(val buildMajor: Int, val buildMinor: Int) : Message