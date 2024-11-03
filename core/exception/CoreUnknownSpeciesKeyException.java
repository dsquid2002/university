package hva.core.exception;

/**
 * Exception thrown when an unknown species key is encountered in the core system.
 * This exception indicates that a specified species ID does not exist within the
 * application context.
 */
public class CoreUnknownSpeciesKeyException extends Exception {

    private final String _species;
    /**
     * Constructs a new CoreUnknownSpeciesKeyException with a default detail message.
     */
    public CoreUnknownSpeciesKeyException(String species) {
        super("CoreUnknownSpeciesKeyException");
        _species = species;
    }

    /**
     * Returns the species associated with this object.
     *
     * @return the species as a String
     */
    public String getSpecies(){
        return _species;
    }
}
