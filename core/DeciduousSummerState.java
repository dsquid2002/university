package hva.core;

public class DeciduousSummerState implements SeasonState{

    @Override
    public int getEffort(){
        return 2;
    }

    @Override
    public String getBioCycle(){
        return "COMFOLHAS";
    }

    @Override
    public SeasonState getNextState(){
        return new DeciduousAutumnState();
    }
    
}
