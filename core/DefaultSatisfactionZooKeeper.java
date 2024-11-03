package hva.core;


public class DefaultSatisfactionZooKeeper extends SatisfactionStrategy {

    private ZooKeeper _zooKeeper;

    public DefaultSatisfactionZooKeeper(ZooKeeper zooKeeper){
        _zooKeeper = zooKeeper;
    }


    /**
     * Calculates the satisfaction level of the ZooKeeper based on the habitats under their care.
     *
     * @return the satisfaction level as an float
     */
    @Override
    public float computeSatisfaction() {
        float sum = 300; // Default value
        for (Habitat habitat : _zooKeeper.getHabitats()) {
            float totalWorkers = habitat.getTotalWorkers();
            if (totalWorkers > 0) {
                sum -= (habitat.getTotalWork() / totalWorkers);
            }
        }
        return sum;
    }
}