package hva.core;

public interface  SeasonState {
    public int getEffort();
    public String getBioCycle();
    public SeasonState getNextState();
}
