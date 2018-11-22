package artificialIntelligenceUtilities;

import gameModeling.Game;

public class Pair {
    private Node node;
    private int cost;

    public Pair(Node node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public Node getNode() {
        return node;
    }
}
