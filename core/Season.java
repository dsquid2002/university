package hva.core;

/**
 * An enumeration representing the four seasons: SPRING, SUMMER, AUTUMN, and WINTER.
 * This enum provides functionality to retrieve the next season in the annual cycle.
 */
public enum Season {
    SPRING(), SUMMER(), AUTUMN(), WINTER();

    /**
     * Retrieves the next season in the cycle.
     *
     * @return the next Season in the order: SPRING -> SUMMER -> AUTUMN -> WINTER -> SPRING
     */
    public Season getNextSeason() {
        switch (this) {
            case SPRING:
                return SUMMER;

            case SUMMER:
                return AUTUMN;

            case AUTUMN:
                return WINTER;

            case WINTER:
                return SPRING;

            default:
                return null; // This case should never occur
        }
    }

}
