package rs.dusk.network.server

import com.github.michaelbull.logging.InlineLogger
import com.google.common.base.Stopwatch
import org.koin.core.context.startKoin
import rs.dusk.core.network.codec.CodecRepository
import rs.dusk.core.network.codec.message.MessageReader
import rs.dusk.core.network.codec.message.decode.OpcodeMessageDecoder
import rs.dusk.core.network.codec.message.encode.GenericMessageEncoder
import rs.dusk.core.network.codec.packet.access.PacketBuilder
import rs.dusk.core.network.codec.packet.decode.SimplePacketDecoder
import rs.dusk.core.network.connection.ConnectionPipeline
import rs.dusk.core.network.connection.ConnectionSettings
import rs.dusk.core.network.connection.server.NetworkServer
import rs.dusk.network.client.codec.identification.SocialClientIdentificationCodec
import rs.dusk.network.server.codec.handshake.SocialServerHandshakeCodec
import rs.dusk.network.server.codec.handshake.SocialServerHandshakeSession
import rs.dusk.network.server.codec.identification.SocialServerIdentificationCodec
import rs.dusk.network.server.codec.relay.SocialServerRelayCodec
import rs.dusk.social.server.managerModule
import rs.dusk.utility.SocialConstants
import rs.dusk.utility.SocialConstants.SOCIAL_PORT_ID
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
    }

    /**
     * Binding socket [SocialConstants.SOCIAL_PORT_ID]
     */
    fun bind() {
        val settings = ConnectionSettings("localhost", SOCIAL_PORT_ID)
        val server = NetworkServer(settings)
	    val codec = CodecRepository.get(SocialServerHandshakeCodec::class)
	    
        val pipeline = ConnectionPipeline {
            val session = SocialServerHandshakeSession(it.channel())
            it.addLast("packet.decoder", SimplePacketDecoder(codec))
            it.addLast("message.decoder", OpcodeMessageDecoder(codec))
            it.addLast(
                "message.reader", MessageReader(
		            codec
                )
            )
            it.addLast(
                "message.encoder",
                GenericMessageEncoder(codec, PacketBuilder(sized = true))
            )
        }

        // TODO: update to new codec finding
//        loadCodecs(SocialServerHandshakeCodec, SocialServerIdentificationCodec, SocialServerRelayCodec)
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