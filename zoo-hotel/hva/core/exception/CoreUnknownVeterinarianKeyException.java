package hva.core.exception;

/**
 * Exception thrown when an unknown veterinary key is encountered in the core system.
 * This exception indicates that a specified veterinary ID does not exist within the
 * application context.
 */
public class CoreUnknownVeterinarianKeyException extends Exception {

    /**
     * Constructs a new CoreUnknownVeterinarianKeyException with a default detail message.
     */
    public CoreUnknownVeterinarianKeyException() {
        super("CoreUnknownVeterinarianKeyException");
    }
}