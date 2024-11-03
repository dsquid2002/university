package hva.core.exception;

/**
 * Exception thrown when an unknown animal key is encountered in the core system.
 * This exception indicates that a specified animal ID does not exist within the
 * application context.
 */
public class CoreUnknownAnimalKeyException extends Exception {

    /**
     * Constructs a new CoreUnknownAnimalKeyException with a default detail message.
     */
    public CoreUnknownAnimalKeyException() {
        super("CoreUnknownAnimalKeyException");
    }
}
