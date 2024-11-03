package hva.core.exception;

/**
 * Exception thrown when a veterinary does not have permission to do vaccination.
 */
public class CoreVeterinarianNotAuthorizedException extends Exception {

    private final String _species;

    /**
     * Constructs a new CoreVeterinarianNotAuthorizedException with a default detail message.
     */
    public CoreVeterinarianNotAuthorizedException(String speciesId) {
        super("CoreVeterinarianNotAuthorizedException");
        _species = speciesId;
    }

    
    /**
     * Returns the species associated with this object.
     *
     * @return the species as a String
     */
    public String getSpecies() {
        return _species;
    }
}