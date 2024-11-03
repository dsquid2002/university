package hva.core.exception;

/**
 * Exception thrown when a duplicate employee key is encountered in the core system.
 * This exception indicates that an attempt was made to register an employee with an
 * ID that already exists within the application context.
 */
public class CoreDuplicateEmployeeKeyException extends Exception {

    /**
     * Constructs a new CoreDuplicateEmployeeKeyException with a default detail message.
     */
    public CoreDuplicateEmployeeKeyException() {
        super("CoreDuplicateEmployeeKeyException");
    }
}
