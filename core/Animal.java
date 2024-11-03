package hva.core;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an animal with associated health records, species, and habitat.
 * This class extends the HotelEntry class and manages the properties related 
 * to a specific animal.
 */
public class Animal extends HotelEntry {

    /** The health record of the animal. */
    private String _healthRecord;

    /** The species to which the animal belongs. */
    private  Species _species; 

    /** The habitat where the animal resides. */
    private Habitat _habitat;

    /** List of all vaccination acts associated with this vaccine. */
    private List<VaccinationAct> _allVaccinations;

    /**
     * Constructs an Animal object with the specified ID, name, species, and habitat.
     *
     * @param id       The unique identifier for the animal.
     * @param name     The name of the animal.
     * @param species  The species to which the animal belongs.
     * @param habitat  The habitat where the animal resides.
     */
    public Animal(String id, String name, Species species, Habitat habitat) {
        super(id, name);
        _species = species;
        _habitat = habitat;
        _healthRecord = "VOID";
        _allVaccinations = new ArrayList<>();
    }

    /** 
     * Código desenvolvido pelo professor docente da cadeira para resolver
    /* problemas relacionados com a serializaçáo
    **/ 
    private void readObject(ObjectInputStream in) throws IOException,
    ClassNotFoundException {
       _healthRecord = (String) in.readObject();
       _species = (Species)in.readObject();
       _habitat = (Habitat)in.readObject();
       _allVaccinations = (List<VaccinationAct>) in.readObject();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
       out.writeObject(_healthRecord);
       out.writeObject(_species);
       out.writeObject(_habitat);
       out.writeObject(_allVaccinations);
    }
    //_--------------------------------------------------------------------

    /**
     * Returns the species associated with this animal.
     *
     * @return The species of the animal.
     */
    Species getSpecies() {
        return _species;
    }

    /**
     * Changes the habitat of this animal to the specified habitat.
     *
     * @param habitat The new habitat for the animal.
     */
    void changeHabitat(Habitat habitat) {
        _habitat = habitat;
    }

    /**
     * Returns the current habitat of the animal.
     *
     * @return the current Habitat object representing the animal's habitat.
     */
    Habitat getCurrentHabitat() {
        return _habitat;
    }

    /**
     * Returns the vaccinations.
     *
     * @return the vaccinations on an animal.
     */
    List<VaccinationAct> getVaccinations() {
        return new ArrayList<>(_allVaccinations);
    }

    /**
     * Shows the satisfaction level of the animal.
     * 
     * @return An integer representing the satisfaction level.
     */
    // Consider making this package-private if needed.
    public int showSatisfaction() {
        // The animal itself does not count
        int sameSpecies = _habitat.getAnimalSameSpecies(this);
        float diffSpecies = _habitat.getTotalAnimals() - sameSpecies - 1;
        return Math.round(20 + 3 * sameSpecies - 2 * diffSpecies + 
            (_habitat.getArea()/_habitat.getTotalAnimals()) + _habitat.getAdequationValue(_species.getId()));
    }

    /**
     * Adds a vaccination act to the animal record.
     *
     * @param act The vaccination act to be added.
     */
    public void addVaccinationAct(VaccinationAct act) {
        _allVaccinations.add(act);
    }

    /**
     * Records the damage caused by a vaccination on this animal.
     *
     * @param damage The damage.
     */
    public void doVaccineDamage(String damage) {
        if (_healthRecord.equals("VOID")) {
            _healthRecord = damage;
        } else {
            _healthRecord += ("," + damage);
        }
    }

    /**
     * Compares this animal to another object for equality.
     *
     * @param obj The object to compare this animal against.
     * @return true if the specified object is equal to this animal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Animal)) {
            return false;
        }
        Animal other = (Animal) obj;
        return other.getId().equals(getId());
    }

    /**
     * Returns the hash code value for this animal.
     *
     * @return A hash code value for this animal.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Returns a string representation of the animal, including its ID, species,
     * health record, and habitat.
     *
     * @return A string representation of the animal.
     */
    @Override
    public String toString() {

        return "ANIMAL|" + super.toString() 
                + "|" 
                + _species.getId() 
                + "|" 
                + _healthRecord 
                + "|" 
                + _habitat.getId();
    }
}
