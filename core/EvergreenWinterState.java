package hva.core;

public class EvergreenWinterState implements SeasonState{

    @Override
    public int getEffort(){
        return 2;
    }

    @Override
    public String getBioCycle(){
        return "LARGARFOLHAS";
    }

    @Override
    public SeasonState getNextState(){
        return new EvergreenSpringState();
    }
    
}
