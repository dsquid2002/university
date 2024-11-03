package hva.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Represents a habitat in a zoo, containing various species of animals and trees.
 * This class manages the area of the habitat, the total number of workers, and
 * the adequation of species to the habitat.
 */
public class Habitat extends HotelEntry {

    private int _area;
    private int _totalWorkers;
    private HashMap<String, Integer> _speciesAdequation;
    private HashSet<Animal> _animalsInHabitat;
    private List<Tree> _treesInHabitat;

    /**
     * Constructs a new Habitat with the specified ID, name, and area.
     *
     * @param id the unique identifier for the habitat
     * @param name the name of the habitat
     * @param area the area of the habitat in square units
     */
    public Habitat(String id, String name, int area) {
        super(id, name);
        _area = area;
        _animalsInHabitat = new HashSet<>();
        _treesInHabitat = new ArrayList<>();
        _speciesAdequation = new HashMap<>();
    }

    /**
     * Returns the area of the habitat.
     *
     * @return the area of the habitat
     */
    int getArea() {
        return _area;
    }

    /**
     * Changes the area of the habitat to the specified value.
     *
     * @param value the new area value
     */
    void chArea(int value) {
        _area = value;
    }

    /**
     * Returns the total number of workers in the habitat.
     *
     * @return the total number of workers
     */
    int getTotalWorkers() {
        return _totalWorkers;
    }

    /**
     * Adds a zoo keeper to the habitat, increasing the worker count by one.
     */
    void addZooKeeper() {
        _totalWorkers += 1;
    }

    /**
     * Removes a zoo keeper from the habitat, decreasing the worker count by one.
     */
    void rmZooKeeper() {
        _totalWorkers -= 1;
    }

    /**
     * Adds an animal to the habitat.
     *
     * @param animal the animal to add
     */
    void addAnimal(Animal animal) {
        _animalsInHabitat.add(animal);
    }

    /**
     * Removes an animal from the habitat.
     *
     * @param animal the animal to remove
     */
    void rmAnimal(Animal animal) {
        _animalsInHabitat.remove(animal);
    }

    /**
     * Plants a tree in the habitat.
     *
     * @param tree the tree to plant
     */
    void plantTree(Tree tree) {
        _treesInHabitat.add(tree);
    }

    /**
     * Returns the total number of animals in the habitat.
     *
     * @return the total number of animals
     */
    int getTotalAnimals() {
        return _animalsInHabitat.size();
    }

    /**
     * Returns the total number of trees in the habitat.
     *
     * @return the total number of trees
     */
    int getTotalTrees() {
        return _treesInHabitat.size();
    }

    /**
     * Returns a list of all animals in the habitat.
     *
     * @return a list of animals
     */
    List<Animal> getAllAnimals() {
        return new ArrayList<>(_animalsInHabitat);
    }

    /**
     * Checks if a specific animal is inside the habitat.
     *
     * @param animal the animal to check
     * @return true if the animal is inside, false otherwise
     */
    boolean isAnimalInside(Animal animal) {
        return _animalsInHabitat.contains(animal);
    }

    /**
     * Adds a species adequation value for a specific species.
     *
     * @param specieId the ID of the species
     * @param adequation true for positive adequation, false for negative
     */
    void addAdequation(String specieId, String adequation) {
        if (adequation.equals("POS")) {
            // Positive Adequation
            _speciesAdequation.put(specieId.toLowerCase(), 20);
        } else {
            // Negative Adequation
            _speciesAdequation.put(specieId.toLowerCase(), -20);
        }
    }

    /**
     * Removes the adequation value for a specific species.
     *
     * @param specieId the ID of the species to remove
     */
    void rmAdequation(String specieId) {
        _speciesAdequation.remove(specieId.toLowerCase());
    }

    /**
     * Returns the adequation value for a specific species.
     *
     * @param speciesId the ID of the species
     * @return the adequation value, or 0 if not found
     */
    int getAdequationValue(String speciesId) {
        if (!(_speciesAdequation.containsKey(speciesId.toLowerCase()))) {
            return 0;
        }
        return _speciesAdequation.get(speciesId.toLowerCase());
    }

    /**
     * Counts the number of animals of the same species in the habitat.
     *
     * @param animal the reference animal for species comparison
     * @return the number of animals of the same species, excluding the reference animal
     */
    int getAnimalSameSpecies(Animal animal) {
        int sum = 0;
        for (Animal a : _animalsInHabitat) {
            if (a.getSpecies().equals(animal.getSpecies())) {
                sum += 1;
            }
        }
        // The animal itself does not count
        return sum - 1;
    }

    /**
     * Calculates the total cleaning effort required for the habitat.
     *
     * @return the total cleaning effort
     */
    float getTotalWork() {
        float sum = this.getArea();
        sum += 3 * (this.getTotalAnimals());
        for (Tree tree : _treesInHabitat) {
            sum += tree.totalCleaningEfffort();
        }

        return sum;
    }

    /**
     * Returns a Collection of all trees.
     *
     * @return a a Collection of all trees
     */
    List<Tree> getTrees() {
        return new ArrayList<>(_treesInHabitat);
    }

    /**
     * Returns a string representation of the habitat.
     *
     * @return a formatted string containing habitat details
     */
    @Override
    public String toString() {
        int treeNumber = getTotalTrees();
        String trees = (treeNumber == 0) ? "0" : String.valueOf(treeNumber);
        Collections.sort(_treesInHabitat, new IdComparator());

        StringBuilder fullString = new StringBuilder();
        fullString.append("HABITAT|")
                  .append(super.toString())
                  .append("|")
                  .append(getArea())
                  .append("|")
                  .append(trees);

        for (Tree tree : _treesInHabitat) {
            fullString.append("\n").append(tree.toString());
        }
        return fullString.toString();
    }
}
