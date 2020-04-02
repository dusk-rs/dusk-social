package org.redrune.network.server

import com.github.michaelbull.logging.InlineLogger
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.redrune.core.network.codec.Codec
import org.redrune.core.network.codec.message.MessageHandler
import org.redrune.core.network.model.message.Message
import org.redrune.core.tools.utility.getPipelineContents
import java.io.IOException

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class Test(val codec: Codec) : SimpleChannelInboundHandler<Message>() {

    private val logger = InlineLogger()

    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
        println("Reading!!!!")
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: Message?) {
        try {
            println("got msg $msg")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}