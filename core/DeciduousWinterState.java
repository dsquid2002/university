package hva.core;

public class DeciduousWinterState implements SeasonState{

    @Override
    public int getEffort(){
        return 0;
    }

    @Override
    public String getBioCycle(){
        return "SEMFOLHAS";
    }

    @Override
    public SeasonState getNextState(){
        return new DeciduousSpringState();
    }
    
}
