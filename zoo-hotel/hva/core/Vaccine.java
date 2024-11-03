package hva.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a vaccine associated with various species and vaccination acts.
 * This class extends the HotelEntry class and manages the vaccinations and species 
 * related to a particular vaccine.
 */
public class Vaccine extends HotelEntry {

    /** List of all vaccination acts associated with this vaccine. */
    private List<VaccinationAct> _allVaccinations;

    /** Set of species associated with this vaccine. */
    private Set<Species> _allSpecies = new HashSet<>();

    /**
     * Constructs a Vaccine object with the specified ID, name, and species.
     *
     * @param id          The unique identifier for the vaccine.
     * @param name        The name of the vaccine.
     * @param manySpecies A list of species associated with the vaccine.
     */
    public Vaccine(String id, String name, ArrayList<Species> manySpecies) {
        super(id, name);
        for (Species species : manySpecies) {
            _allSpecies.add(species);
            _allVaccinations = new ArrayList<>();
        }
    }

    /**
     * Adds a vaccination act to the vaccine.
     *
     * @param act The vaccination act to be added.
     */
    public void addVaccinationAct(VaccinationAct act) {
        _allVaccinations.add(act);
    }

    /**
     * Returns a string representation of the species associated with this vaccine.
     * The species are sorted by their ID and are represented as a comma-separated string.
     *
     * @return A string containing the IDs of the species.
     */
    private String getSpeciesToString() {
        ArrayList<Species> auxList = new ArrayList<>(_allSpecies);
        Collections.sort(auxList, new IdComparator());
        StringBuilder speciesString = new StringBuilder("|");

        for (int i = 0; i < auxList.size(); i++) {
            speciesString.append(auxList.get(i).getId()).append(",");
        }

        // Remove the last "," if needed
        if (speciesString.length() > 0) {
            speciesString.setLength(speciesString.length() - 1);
        }
        return speciesString.toString();
    }

    /**
     * Compares this vaccine to another object for equality.
     *
     * @param obj The object to compare this vaccine against.
     * @return true if the specified object is equal to this vaccine, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Animal)) {
            return false;
        }
        Vaccine other = (Vaccine) obj;
        return other.getId().equals(getId());
    }

    /**
     * Evaluates the damage status for an animal based on its species and the associated vaccine.
     *
     * @param species the species of the animal to evaluate
     * @return a String indicating the damage status: "NORMAL", "CONFUSÃO", "ACIDENTE", or "ERRO"
     */
    String getDamageToAnimal(Species species) {
        String idSpecies = species.getName();

        if (_allSpecies.contains(species)) return "NORMAL";
        int damage = computeDamage(idSpecies);
        if (damage == 0) return "CONFUSÃO";
        else if (damage <= 4) return "ACIDENTE";
        else return "ERRO";
    }

    /**
     * Computes the damage value for a given species and vaccine.
     *
     * @param species the ID of the species to compute damage for
     * @return an int representing the computed damage value
     */
    private int computeDamage(String species) {
        int damage = 0;
        int commonChar = 0;
        int biggestSpecies;
        char[] animalSpeciesArray = species.toCharArray();
        int animalSpeciesSize = animalSpeciesArray.length;

        // iterates over all species of a vaccine
        for ( Species vaccineSpecies : _allSpecies) { 
            char[] vaccineSpeciesArray = vaccineSpecies.getName().toCharArray();
            int vaccineSpeciesSize = vaccineSpeciesArray.length;
            // Compares each char in both arrays not allowing repetition 
            for (int i = 0; i < animalSpeciesSize; i++) {
                for (int j = 0; j < vaccineSpeciesArray.length; j++) {
                    if (animalSpeciesArray[i] == vaccineSpeciesArray[j]){
                        commonChar++;
                        // To not repeat 
                        vaccineSpeciesArray[j] = '\0';
                        break;
                    } 
                } 
            }
            biggestSpecies = (animalSpeciesSize >= vaccineSpeciesSize) 
                ? animalSpeciesSize : vaccineSpeciesSize;
            damage = ((biggestSpecies - commonChar) > damage)    
                ? biggestSpecies - commonChar : damage;
            commonChar = 0;
        }
        return damage;
    }


    /**
     * Returns the hash code value for this vaccine.
     *
     * @return A hash code value for this vaccine.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Returns a string representation of the vaccine, including its ID, name, 
     * number of vaccination acts, and associated species.
     *
     * @return A string representation of the vaccine.
     */
    @Override
    public String toString() {
        String vaccinationTimes = _allVaccinations != null ? Integer.toString(_allVaccinations.size()) : "0";

        return "VACINA|" 
            + super.toString() 
            + "|" + vaccinationTimes 
            + getSpeciesToString();
    }
}
