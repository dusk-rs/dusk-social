package rs.dusk.utility

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since March 26, 2020
 */
object SocialOpcodes {

    /**
     * The opcode which identifies the packet for major and minor build configuration
     */
    const val BUILD_CONFIGURATION_OPCODE = 1

    /**
     * The opcode which identifies the packet which sends the verification message
     */
    const val BUILD_VERIFICATION_OPCODE = 1

    /**
     * The opcode which identifies the packet that sends the identification message
     */
    const val IDENTIFICATION_MESSAGE_OPCODE = 2
}