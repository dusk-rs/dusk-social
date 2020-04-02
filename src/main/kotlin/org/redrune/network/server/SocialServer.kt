package org.redrune.network.server

import com.github.michaelbull.logging.InlineLogger
import com.google.common.base.Stopwatch
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
import org.koin.core.context.startKoin
import org.redrune.core.network.codec.message.decode.OpcodeMessageDecoder
import org.redrune.core.network.codec.message.encode.RawMessageEncoder
import org.redrune.core.network.codec.message.encode.SizedMessageEncoder
import org.redrune.core.network.codec.message.handle.NetworkMessageHandler
import org.redrune.core.network.codec.packet.decode.SimplePacketDecoder
import org.redrune.core.network.connection.ConnectionPipeline
import org.redrune.core.network.connection.ConnectionSettings
import org.redrune.core.network.connection.server.NetworkServer
import org.redrune.core.tools.function.NetworkUtils.Companion.loadCodecs
import org.redrune.network.ServerNetworkEventHandler
import org.redrune.network.server.codec.handshake.SocialServerHandshakeCodec
import org.redrune.social.SocialManager
import org.redrune.social.managerModule
import org.redrune.utility.SocialConstants
import org.redrune.utility.SocialConstants.SOCIAL_PORT_ID
import org.redrune.utility.get
import java.util.concurrent.TimeUnit

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since April 01, 2020
 */
class SocialServer {

    private val logger = InlineLogger()

    /**
     * Tasks that need to be done before the server is loaded called here
     */
    fun preload() {
        startKoin {
            modules(managerModule)
        }
        val module: SocialManager = get()
        module.run()
    }

    /**
     * Binding socket [SocialConstants.SOCIAL_PORT_ID]
     */
    fun bind() {
        val settings = ConnectionSettings("localhost", SOCIAL_PORT_ID)
        val server = NetworkServer(settings)
        val pipeline = ConnectionPipeline {
            it.addLast("logger", LoggingHandler(LogLevel.INFO))
            it.addLast("packet.decoder", SimplePacketDecoder(SocialServerHandshakeCodec))
            it.addLast("message.decoder", OpcodeMessageDecoder(SocialServerHandshakeCodec))
           /* it.addLast(
                "message.handler", NetworkMessageHandler(
                    SocialServerHandshakeCodec,
                    ServerNetworkEventHandler()
                )
            )*/
            it.addLast("message.handler", Test(SocialServerHandshakeCodec))
            it.addLast("message.encoder", SizedMessageEncoder(SocialServerHandshakeCodec))
        }
        loadCodecs(SocialServerHandshakeCodec)
        server.configure(pipeline)
        server.start()
    }

    /**
     * All components required to run the server, and the running of the server itself, are done here
     */
    fun run() {
        val watch = Stopwatch.createStarted()
        preload()
        bind()
        logger.info {
            "Social server booted in ${watch.elapsed(TimeUnit.MILLISECONDS)}ms"
        }
    }

}

fun main() {
    val socialServer = SocialServer()
    socialServer.run()
}