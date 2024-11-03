package hva.core.exception;

/**
 * Exception thrown when a duplicate tree key is encountered in the core system.
 * This exception indicates that an attempt was made to register a tree with an
 * ID that already exists within the application context.
 */
public class CoreDuplicateTreeKeyException extends Exception {

    /**
     * Constructs a new CoreDuplicateTreeKeyException with a default detail message.
     */
    public CoreDuplicateTreeKeyException() {
        super("CoreDuplicateTreeKeyException");
    }
}
