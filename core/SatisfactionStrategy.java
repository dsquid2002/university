package hva.core;

import java.io.Serializable;

public abstract class SatisfactionStrategy implements Serializable {

    public abstract float computeSatisfaction();
}

