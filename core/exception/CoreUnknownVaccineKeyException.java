package hva.core.exception;

/**
 * Exception thrown when an unknown vaccine key is encountered in the core system.
 * This exception indicates that a specified species ID does not exist within the
 * application context.
 */
public class CoreUnknownVaccineKeyException extends Exception {

    /**
     * Constructs a new CoreUnknownVaccineKeyException with a default detail message.
     */
    public CoreUnknownVaccineKeyException() {
        super("CoreUnknownVaccineKeyException");
    }
}