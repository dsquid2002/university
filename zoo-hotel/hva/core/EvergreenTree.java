package hva.core;

/**
 * Represents an evergreen tree in the hotel system.
 * This class extends the Tree class and provides specific 
 * behavior related to evergreen trees, including seasonal effort
 * and biological cycles.
 */
public class EvergreenTree extends Tree {

    private final static SeasonState[] _stateArray = new SeasonState[]{ new EvergreenSpringState(), new EvergreenSummerState(),
        new EvergreenAutumnState(), new EvergreenWinterState()};

    @Override
    public SeasonState getStateBySeason() {
        return _stateArray[_season.ordinal()];
    }
        
    /**
     * Constructs an EvergreenTree with the specified parameters.
     *
     * @param id the unique identifier for the tree
     * @param name the name of the tree
     * @param age the age of the tree in years
     * @param difCleaning the difficulty of cleaning the tree
     * @param currentSeason the current season for the tree
     */
    public EvergreenTree(String id, String name, int age, int difCleaning, Season currentSeason) {
        super(id, name, age, difCleaning, currentSeason);
    }

    /**
     * Retrieves the type of tree as a string.
     *
     * @return a string representing the type of the tree
     */
    @Override
    protected String getType() {
        return "|PERENE|";
    }

}
