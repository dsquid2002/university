package hva.core.exception;

/**
 * Exception thrown when a duplicate vaccine key is encountered in the core system.
 * This exception indicates that an attempt was made to register a vaccine with an
 * ID that already exists within the application context.
 */
public class CoreDuplicateVaccineKeyException extends Exception {

    /**
     * Constructs a new CoreDuplicateVaccineKeyException with a default detail message.
     */
    public CoreDuplicateVaccineKeyException() {
        super("CoreDuplicateVaccineKeyException");
    }
}
