package hva.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * An abstract class representing an entry in a hotel management system.
 * 
 * This class serves as a base for specific types of hotel entries, encapsulating
 * common properties such as ID and name. It also provides methods for equality
 * comparison, hash code generation, and string representation.
 */
abstract class HotelEntry implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 202404044486L;

    private final String _id;
    private final String _name;

    /**
     * Constructs a new HotelEntry with the specified ID and name.
     *
     * @param id the unique identifier for this hotel entry
     * @param name the name associated with this hotel entry
     */
    protected HotelEntry(String id, String name) {
        _id = id;
        _name = name;
    }

    /**
     * Gets the unique identifier for this hotel entry.
     *
     * @return the ID of this hotel entry
     */
    protected String getId() {
        return _id;
    }

    /**
     * Gets the name associated with this hotel entry.
     *
     * @return the name of this hotel entry
     */
    protected String getName() {
        return _name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof HotelEntry)) {
            return false;
        }
        HotelEntry other = (HotelEntry) obj;
        return other.getId().equals(_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }

    @Override
    public String toString() {
        return getId() + "|" + getName();
    }
}
