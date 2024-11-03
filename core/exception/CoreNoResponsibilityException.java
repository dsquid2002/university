package hva.core.exception;

/**
 * Exception thrown when there is no responsibility assigned in the core system.
 * This exception indicates that an operation cannot be completed due to the absence 
 * of an assigned responsibility.
 */
public class CoreNoResponsibilityException extends Exception {

    /**
     * Constructs a new CoreNoResponsibilityException with a default detail message.
     */
    public CoreNoResponsibilityException() {
        super("CoreNoResponsibilityException");
    }
}
