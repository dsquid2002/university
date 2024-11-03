package hva.core;

import hva.core.exception.CoreNoResponsibilityException;

/**
 * Represents an abstract employee within a hotel, providing a structure for specific employee types.
 */
public abstract class Employee extends HotelEntry {

    protected SatisfactionStrategy _satisfactionStrategy;

    /**
     * Constructs an Employee with the specified ID and name.
     *
     * @param id   the unique identifier for the employee
     * @param name the name of the employee
     */
    public Employee(String id, String name) {
        super(id, name);
    }

    /**
     * Calculates the satisfaction level of the employee.
     *
     * @return the satisfaction level as an float
     */
    public float showSatisfaction(){
        return _satisfactionStrategy.computeSatisfaction();
    }

    /**
     * Retrieves the IDs of all responsibilities the employee has.
     *
     * @return a string representation of the responsibility IDs
     */
    public abstract String getResponsibilityIds();
    
    /**
     * Sets the satisfaction strategy to be used.
     *
     * @param strat the satisfaction strategy to set
     */
    public void setSatisfactionStrat(SatisfactionStrategy strat) {
        if (strat != null) {
            _satisfactionStrategy = strat;
        }
    }

    /**
     * Checks if the employee has responsibility for a specific entity identified by its ID.
     *
     * @param idResp the ID of the responsibility to check
     * @return true if the employee has the specified responsibility, false otherwise
     */
    public abstract boolean hasResponsability(String idResp);

    /**
     * Adds a responsibility to the employee.
     *
     * @param respId the ID of the responsibility to be added
     * @param hotel   the hotel where the responsibility is managed
     * @throws CoreNoResponsibilityException if the responsibility does not exist in the hotel
     */
    public abstract void addResponsibility(String respId, Hotel hotel) throws CoreNoResponsibilityException;

    /**
     * Removes a responsibility from the Employee's Collection of responsabilities.
     *
     * @param respId the Id of the responsability to be removed.
     * @throws CoreNoResponsibilityException if the responsbility's Id does not exist in the Employee's Collection
     */
    public abstract void rmResponsibility(String respId) throws CoreNoResponsibilityException;


    /**
     * Returns the type of employee as a string.
     *
     * @return a string indicating the type of employee
     */
    protected abstract String getType();

    /**
     * Returns a string representation of the employee, including type, ID, name, and responsibilities.
     *
     * @return a formatted string representation of the employee
     */
    @Override
    public String toString() {
        return getType() + getId() + "|" + getName() + getResponsibilityIds();
    }
}
