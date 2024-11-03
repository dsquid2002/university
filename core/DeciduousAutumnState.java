package hva.core;

public class DeciduousAutumnState implements SeasonState{

    @Override
    public int getEffort(){
        return 5;
    }

    @Override
    public String getBioCycle(){
        return "LARGARFOLHAS";
    }

    @Override
    public SeasonState getNextState(){
        return new DeciduousWinterState();
    }
    
}
