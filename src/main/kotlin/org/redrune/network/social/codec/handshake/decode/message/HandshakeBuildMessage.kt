package org.redrune.network.social.codec.handshake.decode.message

import org.redrune.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
data class HandshakeBuildMessage(
    /**
     * The major build number of the social server
     */
    val major: Int,

    /**
     * The minor build number of the social server
     */
    val minor: Int
) : Message