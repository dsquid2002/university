package hva.core;

public class EvergreenAutumnState implements SeasonState{

    @Override
    public int getEffort(){
        return 1;
    }

    @Override
    public String getBioCycle(){
        return "COMFOLHAS";
    }

    @Override
    public SeasonState getNextState(){
        return new EvergreenWinterState();
    }
    
}
