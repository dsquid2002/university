package hva.core;

import java.util.HashSet;

/**
 * Represents a species of animals, including associated animals and veterinarians.
 * This class extends the HotelEntry class and manages the collection of animals 
 * and veterinarians related to a specific species.
 */
public class Species extends HotelEntry {
    
    /** Set of all animals associated with this species. */
    private HashSet<Animal> _allAnimals;

    /** Number of veterinarians associated with this species. */
    private int _numberOfVets;

    /**
     * Constructs a Species object with the specified ID and name.
     *
     * @param id   The unique identifier for the species.
     * @param name The name of the species.
     */
    public Species(String id, String name) {
        super(id, name);
        _allAnimals = new HashSet<>();
    }

    /**
     * Adds an animal to the species.
     *
     * @param animal The animal to be added to the species.
     */
    public void addAnimal(Animal animal) {
        _allAnimals.add(animal);
    }

    /**
     * Returns the total number of veterinarians associated with this species.
     *
     * @return The number of veterinarians.
     */
    int getTotalVets() {
        return _numberOfVets;
    }

    /**
     * Returns the total number of animals associated with this species.
     *
     * @return The number of animals.
     */
    int getTotalAnimals() {
        return _allAnimals != null ? _allAnimals.size() : 0;
    }

    /**
     * Increments the count of veterinarians associated with this species.
     */
    void addVet() {
        _numberOfVets++;
    }

    /**
     * Decrements the count of veterinarians associated with this species.
     */
    void remVet() {
        _numberOfVets--;
    }

    /**
     * Compares this species to another object for equality.
     *
     * @param obj The object to compare this species against.
     * @return true if the specified object is equal to this species, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Species)) {
            return false;
        }
        Species other = (Species) obj;
        return other.getId().equals(getId());
    }

    /**
     * Returns the hash code value for this species.
     *
     * @return A hash code value for this species.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Returns a string representation of the species, including its ID and name.
     *
     * @return A string representation of the species.
     */
    @Override
    public String toString() {
        return "ESPÉCIE|" + super.toString();
    }
}
