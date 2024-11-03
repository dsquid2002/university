package hva.core.exception;

/**
 * Exception thrown when a duplicate animal key is encountered in the core system.
 * This exception indicates that an attempt was made to register an animal with an
 * ID that already exists within the application context.
 */
public class CoreDuplicateAnimalKeyException extends Exception {

    /**
     * Constructs a new CoreDuplicateAnimalKeyException with a default detail message.
     */
    public CoreDuplicateAnimalKeyException() {
        super("CoreDuplicateAnimalKeyException");
    }
}
