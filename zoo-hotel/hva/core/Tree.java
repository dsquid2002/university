package hva.core;

/**
 * An abstract class representing a tree in the hotel system.
 * This class extends the HotelEntry class and encapsulates 
 * properties and behaviors common to all tree types.
 */
public abstract class Tree extends HotelEntry {

    private int _age;
    private int _difficultyCleaning;
    protected Season _season;
    private final Season _seasonPlanted;
    private SeasonState _seasonState;

    /**
     * Constructs a Tree with the specified parameters.
     *
     * @param id the unique identifier for the tree
     * @param name the name of the tree
     * @param age the age of the tree in years
     * @param difCleaning the difficulty of cleaning the tree
     * @param currentSeason the current season for the tree
     */
    public Tree(String id, String name, int age, int difCleaning, Season currentSeason) {
        super(id, name);
        _difficultyCleaning = difCleaning;
        _season = currentSeason;
        _seasonPlanted = currentSeason;
        _age = age;
        _seasonState = getStateBySeason();
    }

    /**
     * Retrieves the age of the tree.
     *
     * @return the age of the tree in years
     */
    public int getAge() {
        return _age;
    }

    /**
     * Retrieves the season in which the tree was planted.
     *
     * @return the season the tree was planted
     */
    public Season getSeasonPlanted() {
        return _seasonPlanted;
    }

    /**
     * Retrieves the current season of the tree.
     *
     * @return the current season
     */
    public Season getCurrentSeason() {
        return _season;
    }

    /*JAVADOC */
    public abstract SeasonState getStateBySeason();


    /**
     * Retrieves the base cleaning effort required for the tree.
     *
     * @return the base difficulty of cleaning the tree
     */
    public int getBaseCleaningEffort() {
        return _difficultyCleaning;
    }

    /**
     * Advances the tree to the next season. 
     * Increases the age of the tree if it cycles back to the planted season.
     */
    public void getNextSeason() {
        _season = _season.getNextSeason();
        _seasonState = getStateBySeason();
        
        if (_season == _seasonPlanted) {
            _age += 1;
        }
    }
    
    /**
     * Calculates the total cleaning effort required for the tree 
     * based on age, base cleaning effort, and seasonal effort.
     *
     * @return the total cleaning effort as an integer
     */
    public float totalCleaningEfffort() {
        int sum = getBaseCleaningEffort();
        sum *= getSeasonalEffort();
        sum *= (float)Math.log((getAge()) + 1);

        return sum;
    }

    /**
     * Method to get the seasonal effort required for the tree.
     *
     * @return the seasonal effort as an integer
     */
    public int getSeasonalEffort() {
        return _seasonState.getEffort();
    }

    /**
     * Method to retrieve the biological cycle of the tree.
     *
     * @return a string describing the biological cycle
     */
    protected String getBioCycle() {
        return _seasonState.getBioCycle();
    }

    /**
     * Abstract method to retrieve the type of tree as a string.
     *
     * @return a string representing the type of tree
     */
    protected abstract String getType();

    /**
     * Returns a string representation of the tree, including 
     * its ID, name, age, base cleaning effort, type, and biological cycle.
     *
     * @return a formatted string representing the tree
     */
    @Override
    public String  toString() {
        StringBuilder fullString = new StringBuilder();
        
        fullString.append("ÁRVORE|")
                  .append(getId()).append("|")
                  .append(getName()).append("|")
                  .append(getAge()).append("|")
                  .append(getBaseCleaningEffort())
                  .append(getType())
                  .append(getBioCycle());
    
        return fullString.toString();
    }
}
