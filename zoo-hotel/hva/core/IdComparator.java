package hva.core;

import java.util.Comparator;

/**
 * A comparator for comparing {@link HotelEntry} objects based on their IDs.
 * This comparator performs a case-insensitive comparison of the IDs.
 */
public class IdComparator implements Comparator<HotelEntry> {

    /**
     * Compares two HotelEntry objects by their IDs in a case-insensitive manner.
     *
     * @param p1 The first HotelEntry to be compared.
     * @param p2 The second HotelEntry to be compared.
     * @return A negative integer, zero, or a positive integer as the first 
     *         argument is less than, equal to, or greater than the second.
     */
    @Override
    public int compare(HotelEntry p1, HotelEntry p2) {
        // Compare the IDs in a case-insensitive manner
        return p1.getId().toLowerCase().compareTo(p2.getId().toLowerCase());
    }
}
