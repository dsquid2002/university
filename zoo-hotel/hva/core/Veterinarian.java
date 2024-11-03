package hva.core;

import hva.core.exception.CoreNoResponsibilityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a Veterinarian who is responsible for specific species of animals.
 */
class Veterinarian extends Employee {

    private HashMap<String, Species> _species = new HashMap<>();
    private List<VaccinationAct> _allVaccinations;

    /**
     * Constructs a Veterinarian with the specified ID and name.
     *
     * @param id   the unique identifier for the veterinarian
     * @param name the name of the veterinarian
     */
    public Veterinarian(String id, String name) {
        super(id, name);
        _allVaccinations = new ArrayList<>(); // Initialize vaccinations
        _satisfactionStrategy = new DefaultSatisfactionVeterinarian(this);
    }

    /**
     * Adds a responsibility for a specific species to this veterinarian.
     *
     * @param respId the ID of the species to be added as responsibility
     * @param hotel   the hotel where the species is managed
     * @throws CoreNoResponsibilityException if the species does not exist in the hotel
     */
    @Override
    public void addResponsibility(String respId, Hotel hotel) throws CoreNoResponsibilityException {
        if (!(hotel.isThereSpecies(respId))) {
            throw new CoreNoResponsibilityException();
        }
        Species resp = hotel.getSpeciesById(respId);
        _species.put(respId, resp);
        resp.addVet();
    }

    /**
     * Removes a responsibility from the Veterinarian's _habitats.
     *
     * @param respId the Id of the specie to be removed from responsibilities
     * @throws CoreNoResponsibilityException if the specie's Id does not exist in the Veterinarian's Collection
     */
    public void rmResponsibility(String respId) throws CoreNoResponsibilityException {
        if (!(_species.containsKey(respId))) {
            throw new CoreNoResponsibilityException();
        }
        Species resp = _species.get(respId);
        _species.remove(respId);
        resp.remVet();
    }

    /**
     * Checks if the veterinarian has responsibility for a specific species.
     *
     * @param idResp the ID of the species to check
     * @return true if the veterinarian is responsible for the species, false otherwise
     */
    @Override
    public boolean hasResponsability(String idResp) {
        return _species.containsKey(idResp);
    }

    /**
     * Adds a vaccination act to the veterinary record.
     *
     * @param act The vaccination act to be added.
     */
    void addVaccinationAct(VaccinationAct act) {
        _allVaccinations.add(act);
    }

    /**
     * Retrieves the IDs of all species this veterinarian is responsible for.
     *
     * @return a string representation of the species IDs, or an empty string if there are none
     */
    @Override
    public String getResponsibilityIds() {
        if (_species.isEmpty()) return "";
        Collection<Species> species = _species.values();
        ArrayList<Species> auxList = new ArrayList<>(species);
        Collections.sort(auxList, new hva.core.IdComparator());
        StringBuilder result = new StringBuilder("|");
        for (Species specie : auxList) {
            result.append(specie.getId()).append(",");
        }

        // Remove the last "," if needed
        if (result.length() > 0) {
            result.setLength(result.length() - 1);
        }

        return result.toString();
    }

    /**
     * Retrieves a Collection of all species this Veterinarian is responsible for.
     *
     * @return the Species Collection this Veterinarian is responsible for.
     */
    Collection<Species> getSpecies() {
        return _species.values();
    }
    

    /**
     * Retrieves a Collection of all vaccinations this Veterinarian is responsible for.
     *
     * @return the Species Collection this Veterinarian is responsible for.
     */
    List<VaccinationAct> getVaccinations() {
        return new ArrayList<>(_allVaccinations);
    }


    /**
     * Returns the type of employee.
     *
     * @return a string indicating the type of employee ("VET|")
     */
    @Override
    protected String getType() {
        return "VET|";
    }

    
}
