package hva.core.exception;

/**
 * Exception thrown when a duplicate species key is encountered in the core system.
 * This exception indicates that an attempt was made to register a species with an
 * ID that already exists within the application context.
 */
public class CoreDuplicateKSpeciesKeyException extends Exception {

    /**
     * Constructs a new CoreDuplicateAnimalKeyException with a default detail message.
     */
    public CoreDuplicateKSpeciesKeyException() {
        super("CoreDuplicateKSpeciesKeyException");
    }
}
