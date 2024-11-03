package hva.core;

public class EvergreenSpringState implements SeasonState{

    @Override
    public int getEffort(){
        return 1;
    }

    @Override
    public String getBioCycle(){
        return "GERARFOLHAS";
    }

    @Override
    public SeasonState getNextState(){
        return new EvergreenSummerState();
    }
    
}
