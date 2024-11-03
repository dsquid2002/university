package hva.core;

import hva.core.exception.CoreNoResponsibilityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * Represents a ZooKeeper who is responsible for specific habitats in the zoo.
 */
class ZooKeeper extends Employee {

    private HashMap<String, Habitat> _habitats = new HashMap<>();

    /**
     * Constructs a ZooKeeper with the specified ID and name.
     *
     * @param id   the unique identifier for the ZooKeeper
     * @param name the name of the ZooKeeper
     */
    public ZooKeeper(String id, String name) {
        super(id, name);
        _satisfactionStrategy = new DefaultSatisfactionZooKeeper(this);
  
    }

    /**
     * Adds a responsibility for a specific habitat to this ZooKeeper.
     *
     * @param respId the ID of the habitat to be added as responsibility
     * @param hotel   the hotel where the habitat is managed
     * @throws CoreNoResponsibilityException if the habitat does not exist in the hotel
     */
    @Override
    public void addResponsibility(String respId, Hotel hotel) throws CoreNoResponsibilityException {
        if (!(hotel.isThereHabitat(respId))) {
            throw new CoreNoResponsibilityException();
        }
        Habitat resp = hotel.getHabitatById(respId);
        _habitats.put(respId, resp);
        resp.addZooKeeper();
    }

    /**
     * Removes a responsibility from the zooKeeper's _habitats.
     *
     * @param respId the Id of the habitat to be removed from responsibilities
     * @throws CoreNoResponsibilityException if the habitat's Id does not exist in the ZooKeeper's Collection
     */
    public void rmResponsibility(String respId) throws CoreNoResponsibilityException {
        if (!(_habitats.containsKey(respId))) {
            throw new CoreNoResponsibilityException();
        }
        Habitat resp = _habitats.get(respId);
        _habitats.remove(respId);
        resp.rmZooKeeper();
    }

    /**
     * Checks if the ZooKeeper has responsibility for a specific habitat.
     *
     * @param idResp the ID of the habitat to check
     * @return true if the ZooKeeper is responsible for the habitat, false otherwise
     */
    @Override
    public boolean hasResponsability(String idResp) {
        return _habitats.containsKey(idResp);
    }

    /**
     * Retrieves the IDs of all habitats this ZooKeeper is responsible for.
     *
     * @return a string representation of the habitat names, or an empty string if there are none
     */
    @Override
    public String getResponsibilityIds() {
        Collection<Habitat> habitat = _habitats.values();
        ArrayList<Habitat> auxList = new ArrayList<>(habitat);
        Collections.sort(auxList, new hva.core.IdComparator());
        StringBuilder result = new StringBuilder("|");
        for (Habitat aux : auxList) {
            result.append(aux.getId()).append(",");
        }

        // Remove the last "," if needed
        if (result.length() > 0) {
            result.setLength(result.length() - 1);
        }

        return result.toString();
    }


    /**
     * Retrieves a Collection of all habitats this ZooKeeper is responsible for.
     *
     * @return the Habitat Collection this ZooKeeper is responsible for.
     */
    Collection<Habitat> getHabitats() {
        return _habitats.values();
    }

    /**
     * Returns the type of employee.
     *
     * @return a string indicating the type of employee ("TRT|")
     */
    @Override
    protected String getType() {
        return "TRT|";
    }
}
