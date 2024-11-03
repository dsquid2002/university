package hva.core;

/**
 * Represents a vaccination act for an animal, capturing details about the vaccination, 
 * veterinarian, and species involved.
 */
public class VaccinationAct {

    private Veterinarian _vet;
    private Animal _animal;
    private Vaccine _vaccine;
    private boolean _badVaccination;

    /**
     * Constructs a VaccinationAct with the specified vaccine ID, veterinarian ID, and species ID.
     *
     * @param vacc the vaccine administered
     * @param vet the veterinarian who administered the vaccine
     * @param species the species being vaccinated
     */
    public VaccinationAct(Vaccine vacc, Veterinarian vet, Animal animal, String dmg) {
        _vaccine = vacc;
        _vet = vet;
        _animal = animal;
        _badVaccination = !dmg.equals("NORMAL");
    }

    /**
     * Returns the ID of the veterinarian who administered the vaccine.
     *
     * @return the veterinarian ID
     */
    String getIdVet() {
        return _vet.getId();
    }

    /**
     * Returns the ID of the animal being vaccinated.
     *
     * @return the animal ID
     */
    String getIdSpecies() {
        return _animal.getId();
    }

    /**
     * Returns the ID of the vaccine administered.
     *
     * @return the vaccine ID
     */
    String getIdVaccine() {
        return _vaccine.getId();
    }

    /**
     * Determines if a vaccination is considered bad based on the damage status.
     *
     * @param damage the damage status of the vaccination, which should be compared against "NORMAL"
     * @return true if the vaccination is considered bad (i.e., damage is not "NORMAL"), false otherwise
     */
    boolean isBadVaccination() {
        return _badVaccination;
    }


    /**
     * Returns a string representation of the vaccination act.
     *
     * @return a formatted string representing the vaccination act
     */
    @Override
    public String toString() {
        return "REGISTO-VACINA|" 
            + _vaccine.getId()
            + "|" + _vet.getId() 
            + "|" + _animal.getSpecies().getId();
    }
}
