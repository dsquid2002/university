package hva.core.exception;

/**
 * Exception thrown when a duplicate habitat key is encountered in the core system.
 * This exception indicates that an attempt was made to register a habitat with an
 * ID that already exists within the application context.
 */
public class CoreDuplicateHabitatKeyException extends Exception {

    /**
     * Constructs a new CoreDuplicateHabitatKeyException with a default detail message.
     */
    public CoreDuplicateHabitatKeyException() {
        super("CoreDuplicateHabitatKeyException");
    }
}
