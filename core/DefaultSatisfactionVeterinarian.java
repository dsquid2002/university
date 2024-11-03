package hva.core;


public class DefaultSatisfactionVeterinarian extends SatisfactionStrategy {

    private Veterinarian _veterinarian;

    public DefaultSatisfactionVeterinarian(Veterinarian veterinarian){
        _veterinarian = veterinarian;
    }


    /**
     * Calculates the satisfaction level of the veterinarian based on the animals under their care.
     *
     * @return the satisfaction level as an float
     */
    @Override
    public float computeSatisfaction() {
        float sum = 20; // Default value
        for (Species specie : _veterinarian.getSpecies()) {
            float totalVets = specie.getTotalVets();
            if (totalVets > 0) {
                sum -= (specie.getTotalAnimals() / totalVets);
            }
        }
        return sum;
    }
}