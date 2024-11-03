package hva.core.exception;

/**
 * Exception thrown when an unknown employee key is encountered in the core system.
 * This exception indicates that a specified employee ID does not exist within the
 * application context.
 */
public class CoreUnknownEmployeeKeyException extends Exception {

    /**
     * Constructs a new CoreUnknownEmployeeKeyException with a default detail message.
     */
    public CoreUnknownEmployeeKeyException() {
        super("CoreUnknownEmployeeKeyException");
    }
}
