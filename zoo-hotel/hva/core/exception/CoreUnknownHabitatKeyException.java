package hva.core.exception;

/**
 * Exception thrown when an unknown habitat key is encountered in the core system.
 * This exception indicates that a specified habitat ID does not exist within the
 * application context.
 */
public class CoreUnknownHabitatKeyException extends Exception {

    /**
     * Constructs a new CoreUnknownHabitatKeyException with a default detail message.
     */
    public CoreUnknownHabitatKeyException() {
        super("CoreUnknownHabitatKeyException");
    }
}
