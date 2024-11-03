package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;

/**
 * Represents a hotel that manages various entities related to animals, habitats, species, vaccines, trees, and employees.
 * This class provides functionality to register, search, and display information about these entities.
 * It implements Serializable to support saving and loading its state.
 */
public class Hotel implements Serializable {

    @Serial
    private static final long serialVersionUID = 202417081734L;

    private boolean _saved;
    private Season _season;
    private Map<String, Animal> _allAnimals;
    private Map<String, Species> _allSpecies;
    private Map<String, Vaccine> _allVaccines;
    private Map<String, Habitat> _allHabitats;
    private Map<String, Employee> _allEmployees;
    private Map<String, Tree> _allTrees;
    private List<VaccinationAct> _allVaccinationActs;

    /**
     * Constructs a Hotel instance with default values.
     * Initializes the season to SPRING and creates empty collections for all entities.
     */
    public Hotel() {
        _season = Season.SPRING;
        _allAnimals = new HashMap<>();
        _allSpecies = new HashMap<>();
        _allVaccines = new HashMap<>();
        _allHabitats = new HashMap<>();
        _allEmployees = new HashMap<>();
        _allTrees = new HashMap<>();
        _allVaccinationActs = new ArrayList<>();
        // There is no need to save an empty file
        _saved = true;
    }

    /**
     * Reads a text input file and creates corresponding domain entities.
     *
     * @param filename name of the text input file
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws IOException if there is an I/O error while processing the text file
     */
    void importFile(String filename) throws UnrecognizedEntryException, IOException {
        Parser parser = new Parser(this);
        parser.parseFile(filename);
    }

    /**
     * Checks if the current state of the hotel is saved.
     *
     * @return true if the state is saved, false otherwise
     */
    public boolean isSaved() {
        return _saved;
    }

    /**
     * Marks the hotel state as saved.
     */
    public void saveState() {
        _saved = true;
    }

    /**
     * Marks the hotel state as unsaved.
     */
    public void unsaveState() {
        _saved = false;
    }

    /**
     * Retrieves all registered animals.
     *
     * @return a collection of all animals
     */
    public Collection<Animal> getAllAnimals() {
        return _allAnimals.values();
    }

    /**
     * Retrieves all registered vaccines.
     *
     * @return a collection of all vaccines
     */
    public Collection<Vaccine> getAllVaccines() {
        return _allVaccines.values();
    }

    /**
     * Retrieves all registered habitats.
     *
     * @return a collection of all habitats
     */
    public Collection<Habitat> getAllHabitats() {
        return _allHabitats.values();
    }

    /**
     * Retrieves all registered trees.
     *
     * @return a collection of all trees
     */
    public Collection<Tree> getAllTrees() {
        return _allTrees.values();
    }

    /**
     * Retrieves all registered employees.
     *
     * @return a collection of all employees
     */
    public Collection<Employee> getAllEmployees() {
        return _allEmployees.values();
    }

    /**
     * Retrieves the current season.
     *
     * @return the current season
     */
    public Season getSeason() {
        return _season;
    }
    /**
     * Updates the Hotel's the current season with the next one.
     *
     */
    public int advanceSeason() {
        _season = _season.getNextSeason();

        return _season.ordinal();
    }

    // SEARCH FOR OBJECTS --------------------------------------------

    /**
     * Retrieves the species object given the ID string.
     *
     * @param id the ID of the species
     * @return the species object, or null if not found
     */
    public Species getSpeciesById(String id) {
        return _allSpecies.get(id.toLowerCase());
    }

    /**
     * Retrieves the animal object given the ID string.
     *
     * @param id the ID of the animal
     * @return the animal object, or null if not found
     */
    public Animal getAnimalById(String id) {
        return _allAnimals.get(id.toLowerCase());
    }

    /**
     * Retrieves the habitat object given the ID string.
     *
     * @param id the ID of the habitat
     * @return the habitat object, or null if not found
     */
    public Habitat getHabitatById(String id) {
        return _allHabitats.get(id.toLowerCase());
    }

    /**
     * Retrieves the vaccine object given the ID string.
     *
     * @param id the ID of the vaccine
     * @return the vaccine object, or null if not found
     */
    private Vaccine getVaccineById(String id) {
        return _allVaccines.get(id.toLowerCase());
    }

    /**
     * Retrieves the veterinary object given the ID string.
     *
     * @param id the ID of the veterinary
     * @return the veterinary object, or null if not found
     */
    private Veterinarian getVetById(String id) {
        Employee vet = getEmployeeById(id.toLowerCase());
        if (vet == null || vet instanceof ZooKeeper) return null;
        return (Veterinarian) vet;
    }

    /**
     * Retrieves the tree object given the ID string.
     *
     * @param id the ID of the tree
     * @return the tree object, or null if not found
     */
    public Tree getTreeById(String id) {
        return _allTrees.get(id.toLowerCase());
    }

    /**
     * Retrieves the employee object given the ID string.
     *
     * @param id the ID of the employee
     * @return the employee object, or null if not found
     */
    public Employee getEmployeeById(String id) {
        return _allEmployees.get(id.toLowerCase());
    }

    // ID DUPLICATE CHECKS --------------------------------------------

    /**
     * Checks if a species exists with the given ID.
     *
     * @param id the ID of the species
     * @return true if the species exists, false otherwise
     */
    public boolean isThereSpecies(String id) {
        return _allSpecies.containsKey(id.toLowerCase());
    }

    /**
     * Checks if an employee exists with the given ID.
     *
     * @param id the ID of the employee
     * @return true if the employee exists, false otherwise
     */
    public boolean isThereEmployee(String id) {
        return _allEmployees.containsKey(id.toLowerCase());
    }

    /**
     * Checks if an animal exists with the given ID.
     *
     * @param id the ID of the animal
     * @return true if the animal exists, false otherwise
     */
    public boolean isThereAnimal(String id) {
        return _allAnimals.containsKey(id.toLowerCase());
    }

    /**
     * Checks if a habitat exists with the given ID.
     *
     * @param id the ID of the habitat
     * @return true if the habitat exists, false otherwise
     */
    public boolean isThereHabitat(String id) {
        return _allHabitats.containsKey(id.toLowerCase());
    }

    /**
     * Checks if a vaccine exists with the given ID.
     *
     * @param id the ID of the vaccine
     * @return true if the vaccine exists, false otherwise
     */
    public boolean isThereVaccine(String id) {
        return _allVaccines.containsKey(id.toLowerCase());
    }

    /**
     * Checks if a tree exists with the given ID.
     *
     * @param id the ID of the tree
     * @return true if the tree exists, false otherwise
     */
    public boolean isThereTree(String id) {
        return _allTrees.containsKey(id.toLowerCase());
    }

    // ----------------------------------REGISTERS --------------------------------------------

    /**
     * Registers a new species with the given ID and name.
     *
     * @param id the ID of the species
     * @param name the name of the species
     * @throws CoreDuplicateKSpeciesKeyException if the specified species already exists
     */
    public void registerSpecies(String id, String name)throws  CoreDuplicateKSpeciesKeyException{
        
        if (isThereSpecies(id)) throw new CoreDuplicateKSpeciesKeyException();
        _allSpecies.put(id.toLowerCase(), new Species(id, name));
    }

    /**
     * Registers a new animal with the given details.
     *
     * @param id the ID of the animal
     * @param name the name of the animal
     * @param idSpecies the ID of the species the animal belongs to
     * @param habitatId the ID of the habitat where the animal is located
     * @throws CoreDuplicateAnimalKeyException if an animal with the same ID already exists
     * @throws CoreUnknownHabitatKeyException if the specified habitat ID does not exist
     */
    public void registerAnimal(String id, String name, String idSpecies, String idHabitat)
        throws CoreDuplicateAnimalKeyException, CoreUnknownHabitatKeyException {
        
        if (isThereAnimal(id)) {
            throw new CoreDuplicateAnimalKeyException();
        } else if (!isThereHabitat(idHabitat)) {
            throw new CoreUnknownHabitatKeyException();
        }
        Species species = getSpeciesById(idSpecies);
        Habitat habitat = getHabitatById(idHabitat); 
        Animal animal = new Animal(id, name, species, habitat);
        habitat.addAnimal(animal);
        species.addAnimal(animal);
        _allAnimals.put(id.toLowerCase(), animal);
    }

    /**
     * Registers a new habitat with the given ID, name, and area.
     *
     * @param id the ID of the habitat
     * @param name the name of the habitat
     * @param area the area of the habitat
     * @throws CoreDuplicateHabitatKeyException if a habitat with the same ID already exists
     */
    public void registerHabitat(String id, String name, int area) throws CoreDuplicateHabitatKeyException {
        
        if (isThereHabitat(id)) {
            throw new CoreDuplicateHabitatKeyException();
        }
        _allHabitats.put(id.toLowerCase(), new Habitat(id, name, area));
    }

    /**
     * Registers a new vaccine with the given details.
     *
     * @param id the ID of the vaccine
     * @param name the name of the vaccine
     * @param species the IDs of species that the vaccine is applicable to
     * @throws CoreDuplicateVaccineKeyException if a vaccine with the same ID already exists
     * @throws CoreUnknownSpeciesKeyException if any of the specified species IDs do not exist
     */
    public void registerVaccine(String id, String name, String[] species)
        throws CoreDuplicateVaccineKeyException, CoreUnknownSpeciesKeyException {
        
        if (isThereVaccine(id)) {
            throw new CoreDuplicateVaccineKeyException();
        } else {
            ArrayList<Species> speciesObjects = new ArrayList<>();
            for (String aux : species) {
                // Takes all white spaces in the string 
                aux = aux.replaceAll("\\s+", "");
                if (!isThereSpecies(aux)) {
                    throw new CoreUnknownSpeciesKeyException(aux);
                }
                speciesObjects.add(getSpeciesById(aux));
            }
            _allVaccines.put(id.toLowerCase(), new Vaccine(id, name, speciesObjects));
        }
    }

    /**
     * Registers a new tree in the specified habitat.
     *
     * @param idHabitat the ID of the habitat where the tree will be planted
     * @param idTree the ID of the tree
     * @param nameTree the name of the tree
     * @param age the age of the tree
     * @param baseDif the base diameter of the tree
     * @param treeType the type of the tree (e.g., "CADUCA" or "PERENNE")
     * @throws CoreDuplicateTreeKeyException if a tree with the same ID already exists
     * @throws CoreUnknownHabitatKeyException if the specified habitat ID does not exist
     */
    public void registerTree(String idHabitat, String idTree, String nameTree, int age, int baseDif, String treeType)
        throws CoreDuplicateTreeKeyException, CoreUnknownHabitatKeyException {
        
        if (!isThereHabitat(idHabitat)) {
            throw new CoreUnknownHabitatKeyException();
        }
        getHabitatById(idHabitat).plantTree(createTree(idTree, nameTree, age, baseDif, treeType));
    }

    /**
     * Creates a new tree object (either a deciduous or evergreen tree) and adds it to the collection.
     *
     * @param idTree the unique identifier for the tree
     * @param nameTree the name of the tree
     * @param age the age of the tree in years
     * @param baseDif the base difficulty associated with the tree
     * @param treeType the type of the tree, either "CADUCA" for deciduous or any other string for evergreen
     * @throws CoreDuplicateTreeKeyException if a tree with the same ID already exists
     * @return the newly created Tree object
     */
    public Tree createTree(String idTree, String nameTree, int age, int baseDif, String treeType) throws CoreDuplicateTreeKeyException {
        
        if (isThereTree(idTree)) throw new CoreDuplicateTreeKeyException();
        Tree tree;
        if (treeType.equals("CADUCA")) {
            tree = new DeciduousTree(idTree, nameTree, age, baseDif, getSeason());
            _allTrees.put(idTree.toLowerCase(), tree);
        } else {
            tree = new EvergreenTree(idTree, nameTree, age, baseDif, getSeason());
            _allTrees.put(idTree.toLowerCase(), tree);
        }
        return tree;
    }


    /**
     * Registers a new employee with the given details.
     *
     * @param id the ID of the employee
     * @param name the name of the employee
     * @param type the type of employee (e.g., "VET" or "ZOO_KEEPER")
     * @throws CoreDuplicateEmployeeKeyException if an employee with the same ID already exists
     */
    public Employee registerEmployee(String id, String name, String type) throws CoreDuplicateEmployeeKeyException {
        
        if (isThereEmployee(id)) {
            throw new CoreDuplicateEmployeeKeyException();
        }
        Employee emp;
        if (type.equals("VET")) {
            emp = new Veterinarian(id, name);
            _allEmployees.put(id.toLowerCase(), emp);
        } else {
            emp = new ZooKeeper(id, name);
            _allEmployees.put(id.toLowerCase(), emp);
        }
        return emp;
    }

    /**
     * Adds a responsibility to the specified employee.
     *
     * @param empId the employee's Id to whom the responsibility will be added
     * @param respId the ID of the responsibility (either a habitat or a species)
     * @throws CoreUnknownEmployeeKeyException if the specified employee ID does not exist
     * @throws CoreNoResponsibilityException if the specified responsibility is not valid
     */
    public void addResponsibility(String empId, String respId)
            throws CoreUnknownEmployeeKeyException, CoreNoResponsibilityException {
            
        if (!isThereEmployee(empId))
            throw new CoreUnknownEmployeeKeyException();
        Employee emp = getEmployeeById(empId);
        emp.addResponsibility(respId, this);
    }

    /**
     * Removes a responsibility to the specified employee.
     *
     * @param empId the employee's Id to whom the responsibility will be removed
     * @param respId the ID of the responsibility (either a habitat or a species)
     * @throws CoreUnknownEmployeeKeyException if the specified employee ID does not exist
     * @throws CoreNoResponsibilityException if the specified responsibility is not valid
     */
    public void rmResponsibility(String empId, String respId)
            throws CoreUnknownEmployeeKeyException, CoreNoResponsibilityException {
            
        if (!isThereEmployee(empId))
            throw new CoreUnknownEmployeeKeyException();
        Employee emp = getEmployeeById(empId);
        emp.rmResponsibility(respId);   
    }

    // ----------------------------------SHOW ALL ----------------------

    /**
     * Displays all habitats in a formatted string.
     *
     * @return a string containing all habitats, or null if no habitats exist
     */
    public String doShowAllHabitats() {
        
        Collection<Habitat> habitats = getAllHabitats();
        ArrayList<Habitat> auxList = new ArrayList<>(habitats);
        Collections.sort(auxList, new hva.core.IdComparator());
        return formatCollection(auxList);
    }

    /**
     * Displays all animals in the program in a formatted string.
     *
     * @return a string containing all animals, or null if no animals exist
     */
    public String doShowAllAnimals() {
        
        Collection<Animal> animals = getAllAnimals();
        ArrayList<Animal> auxList = new ArrayList<>(animals);
        Collections.sort(auxList, new hva.core.IdComparator());
        return formatCollection(auxList);
    }

    /**
     * Displays all animals in an habitat in a formatted string.
     *
     * @return a string containing all animals, or null if no animals exist
     */
    public String doShowAllAnimals(String idHabitat) throws CoreUnknownHabitatKeyException{
        
        if (!isThereHabitat(idHabitat)) throw new CoreUnknownHabitatKeyException();
        Collection<Animal> animals = getHabitatById(idHabitat).getAllAnimals();
        ArrayList<Animal> auxList = new ArrayList<>(animals);
        Collections.sort(auxList, new hva.core.IdComparator());
        return formatCollection(auxList);
    }

    /**
     * Displays all employees in a formatted string.
     *
     * @return a string containing all employees, or null if no employees exist
     */
    public String doShowAllEmployees() {
        
        Collection<Employee> employees = getAllEmployees();
        ArrayList<Employee> auxList = new ArrayList<>(employees);
        Collections.sort(auxList, new hva.core.IdComparator());
        return formatCollection(auxList);
    }

    /**
     * Displays all vaccinations in a formatted string.
     *
     * @return a string containing all vaccinations, or null if no vaccinations exist
     */
    public String doShowAllVaccinations() {
        return formatCollection(_allVaccinationActs);
    }

    /**
     * Displays all vaccinations of an animal in a formatted string.
     *
     * @return a string containing all vaccinations, or null if no vaccinations exist
     */
    public String doShowAllVaccinations(String idAnimal) throws CoreUnknownAnimalKeyException{
        
        // Break of open close pattern 
        if (!isThereAnimal(idAnimal)) throw new CoreUnknownAnimalKeyException();
        Collection <VaccinationAct> allVacc = getAnimalById(idAnimal).getVaccinations();
        return formatCollection(allVacc);
    }


    /**
     * Displays all vaccinations of a veterinary in a formatted string.
     *
     * @return a string containing all vaccinations, or null if no vaccinations exist
     */
    public String doShowMedicalActs(String idVet) throws CoreUnknownVeterinarianKeyException{
        
        Employee aux = getEmployeeById(idVet);
        // Break of open close pattern 
        if (!isThereEmployee(idVet) || !(aux instanceof Veterinarian)) throw new CoreUnknownVeterinarianKeyException();
        Veterinarian vet = (Veterinarian) aux;
        Collection <VaccinationAct> allVacc = vet.getVaccinations();
        return formatCollection(allVacc);
    }

    /**
     * Displays all vaccinations of an animal in a formatted string.
     *
     * @return a string containing all vaccinations, or null if no vaccinations exist
     */
    public String doShowAllVaccinations(String idVet, int d) throws CoreUnknownVeterinarianKeyException{
        
        Employee aux = getEmployeeById(idVet);
        // Break of open close pattern 
        if (!isThereEmployee(idVet) || !(aux instanceof Veterinarian)) throw new CoreUnknownVeterinarianKeyException();
        Veterinarian vet = (Veterinarian) aux;
        Collection <VaccinationAct> allVacc = vet.getVaccinations();
        return formatCollection(allVacc);
    }

        /**
     * Formats a collection of items into a string, each item on a new line.
     *
     * @param collection the collection of items to format
     * @param <T> the type of items in the collection
     * @return a formatted string with each item's string representation on a new line,
     *         or null if the collection is empty
     */
    <T> String formatCollection(Collection<T> collection) {
        
        StringBuilder result = new StringBuilder();
        if (!collection.isEmpty()) {
            for (T item : collection) {
                result.append(item.toString()).append("\n");
            }
            // Remove the last newline character
            result.setLength(result.length() - 1);
            return result.toString();
        }
        return null;
    }   

    /**
     * Retrieves and formats a list of trees in a specified habitat.
     *
     * This method checks if the habitat exists, retrieves the trees associated with it,
     * sorts the trees using the provided comparator, and returns a formatted string 
     * representation of the trees.
     *
     * @param idHabitat the ID of the habitat for which to retrieve the trees
     * @throws CoreUnknownHabitatKeyException if the specified habitat ID does not correspond to an existing habitat
     * @return a formatted string containing the list of trees in the habitat, or an empty string if there are no trees
     */
    public String doShowTreesInHabitat(String idHabitat) throws CoreUnknownHabitatKeyException {
        
        if (!isThereHabitat(idHabitat)) throw new CoreUnknownHabitatKeyException();
        Habitat habitat = getHabitatById(idHabitat);
        ArrayList<Tree> trees = new ArrayList<>(habitat.getTrees());
        Collections.sort(trees, new IdComparator());
        return formatCollection(trees);
    }

    /**
     * Retrieves and formats a list of bad vaccinations from the collection of vaccination acts.
     * 
     * @return a formatted string containing the bad vaccinations, or null if there are no bad vaccinations
     */
    public String doShowBadVaccinations() {

        ArrayList<VaccinationAct> auxList = new ArrayList<>(_allVaccinationActs);
        if (!auxList.isEmpty()) {
            Iterator<VaccinationAct> iter = auxList.iterator();
            VaccinationAct item;
            while (iter.hasNext()) {
                item = iter.next();
                if (!item.isBadVaccination()) iter.remove();
            }
            return formatCollection(auxList);
        } else return null;
    }

    /**
     * Retrieves and formats a list of vaccines.
     * 
     * @return a formatted string containing the vaccines, or null if there are no bad vaccinations
     */    
    public String doShowAllVaccines(){

        ArrayList<Vaccine> auxList = new ArrayList<>(getAllVaccines());
        Collections.sort(auxList, new IdComparator());
        return formatCollection(auxList);
    }
    
    // --------------------------------------------------

    /**
     * Changes the habitat of the specified animal to the specified habitat.
     *
     * @param idAnimal the ID of the animal whose habitat is to be changed
     * @param idHabitat the ID of the new habitat
     * @throws CoreUnknownAnimalKeyException if the specified animal ID does 
     * not correspond to an existing animal
     * @throws CoreUnknownHabitatKeyException if the specified habitat ID does
     * not correspond to an existing habitat
     */
    public void changeHabitat(String idAnimal, String idHabitat) 
            throws CoreUnknownAnimalKeyException, CoreUnknownHabitatKeyException {
        
        if (!isThereAnimal(idAnimal)) throw new CoreUnknownAnimalKeyException();
        if (!isThereHabitat(idHabitat)) throw new CoreUnknownHabitatKeyException();
        
        // Gets respective objects
        Animal animal = getAnimalById(idAnimal);
        Habitat habitatNew = getHabitatById(idHabitat);
        Habitat habitatOld = animal.getCurrentHabitat();
        if (!habitatOld.isAnimalInside(animal)) return;
        // Updates old information 
        habitatOld.rmAnimal(animal); 
        animal.changeHabitat(habitatNew);
        habitatNew.addAnimal(animal);
    }

    /**
     * Changes the given habitat's area value.
     *
     * @param idHabitat the ID of the habitat
     * @param newArea the new area value to atribute
     * @throws CoreUnknownHabitatKeyException if the specified habitat ID does
     * not correspond to an existing habitat
     */
    public void changeAreaHabitat(String idHabitat, int newArea)
            throws CoreUnknownHabitatKeyException {
                
        if (!isThereHabitat(idHabitat)) throw new CoreUnknownHabitatKeyException();
                
        //Updates the given Habitat with the new area value
        Habitat habitat = getHabitatById(idHabitat);
        habitat.chArea(newArea);
    }

    /**
     * Adds a vaccination act for a specified animal, performed by a veterinarian using a specified vaccine.
     *
     * @param idVacc   the ID of the vaccine being administered
     * @param idVet    the ID of the veterinarian administering the vaccine
     * @param idAnimal the ID of the animal receiving the vaccination
     * @return true if the vaccination resulted in damage; false if the damage is considered normal
     * @throws CoreUnknownVeterinarianKeyException if the specified veterinarian ID does not correspond to an existing veterinarian
     * @throws CoreVeterinarianNotAuthorizedException if the specified veterinarian is not authorized to administer the vaccine
     * @throws CoreUnknownVaccineKeyException if the specified vaccine ID does not correspond to an existing vaccine
     * @throws CoreUnknownAnimalKeyException if the specified animal ID does not correspond to an existing animal
     */
    public boolean addVaccinationAct(String idVacc, String idVet, String idAnimal) 
            throws CoreUnknownVeterinarianKeyException, 
                CoreVeterinarianNotAuthorizedException,
                CoreUnknownVaccineKeyException, 
                CoreUnknownAnimalKeyException {

        // Retrieve the vaccine, veterinarian, and animal based on their IDs
        Vaccine vaccine = getVaccineById(idVacc);
        Veterinarian vet = getVetById(idVet);
        Animal animal = getAnimalById(idAnimal);

        // Validate the veterinarian
        if (vet == null) {
            throw new CoreUnknownVeterinarianKeyException();
        }
        if (!vet.hasResponsability(animal.getSpecies().getId())) {
            throw new CoreVeterinarianNotAuthorizedException(animal.getSpecies().getId());
        }

        // Validate the vaccine and animal
        if (!isThereVaccine(idVacc)) {
            throw new CoreUnknownVaccineKeyException();
        }
        if (!isThereAnimal(idAnimal)) {
            throw new CoreUnknownAnimalKeyException();
        }

        // Determine the damage caused by the vaccine
        String damage = vaccine.getDamageToAnimal(animal.getSpecies());
        animal.doVaccineDamage(damage);

        // Create a new vaccination act and record it
        VaccinationAct act = new VaccinationAct(vaccine, vet, animal, damage);
        vaccine.addVaccinationAct(act);
        animal.addVaccinationAct(act);
        vet.addVaccinationAct(act);
        _allVaccinationActs.add(act);

        return !damage.equals("NORMAL");
    }


     /**
     * Changes the influence of a species on a specified habitat.
     *
     * This method modifies the adequation of a species within a habitat based on the specified influence type.
     * If the influence type is "NEU", it removes the adequation for the species from the habitat before adding 
     * the new influence.
     *
     * @param idHabitat the ID of the habitat where the influence is to be changed
     * @param idSpecies the ID of the species whose influence is being changed
     * @param influence the type of influence to be set (e.g., "POS", "NEG", or "NEU")
     * @throws CoreUnknownHabitatKeyException if the specified habitat ID does not correspond to an existing habitat
     * @throws CoreUnknownSpeciesKeyException if the specified species ID does not correspond to an existing species
     */
    public void changeInfluence(String idHabitat, String idSpecies, String influence) 
            throws CoreUnknownHabitatKeyException, CoreUnknownSpeciesKeyException {
        if (!isThereHabitat(idHabitat)) throw new CoreUnknownHabitatKeyException();
        if (!isThereSpecies(idSpecies)) throw new CoreUnknownSpeciesKeyException(idSpecies);

        Habitat habitat = getHabitatById(idHabitat);
        if (influence.equals("NEU")) habitat.rmAdequation(idSpecies);
        else habitat.addAdequation(idSpecies, influence);
    }


    // ------------------------------ Satisfaction ----------------------

    /**
     * Displays the satisfaction level of an employee.
     *
     * @param empId the unique identifier of the employee.
     * @return the rounded satisfaction value of the employee as a String.
     * @throws CoreUnknownEmployeeKeyException if the employee ID does not correspond to a known employee.
     */
    public String showEmployeeSatisfaction(String empId) throws CoreUnknownEmployeeKeyException {
        
        if (!(isThereEmployee(empId))) throw new CoreUnknownEmployeeKeyException();
        float value = getEmployeeById(empId).showSatisfaction();
        return String.valueOf(Math.round(value));
    }

    /**
     * Displays the satisfaction level of an animal.
     *
     * @param id the unique identifier of the animal.
     * @return the satisfaction value of the animal as a String.
     * @throws CoreUnknownAnimalKeyException if the animal ID does not correspond to a known animal.
     */
    public String showAnimalSatisfaction(String id) throws CoreUnknownAnimalKeyException {
        
        if (!(isThereAnimal(id))) throw new CoreUnknownAnimalKeyException();
        int value = getAnimalById(id).showSatisfaction();
        return String.valueOf(value);
    }

    /**
     * Computes the total satisfaction level of all employees and animals.
     *
     * @return the rounded total satisfaction value as a String.
     */
    public String computeAllSatisfaction() {
        
        float sum = 0;
        for (Employee emp : _allEmployees.values()) {
            sum += emp.showSatisfaction();
        }
        for (Animal animal : _allAnimals.values()) {
            sum += animal.showSatisfaction();
        }
        return String.valueOf(Math.round(sum));
    }

}


