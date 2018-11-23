package AIAgents;

import artificialIntelligenceUtilities.Node;
import artificialIntelligenceUtilities.Pair;
import gameModeling.Game;
import nonAIAgents.Agent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Greedy extends Agent {

    @Override
    public boolean agentDeploys(Game game, int playerNumber) {
        return true;
    }

    public boolean agentAttacks(Game game, int playerNumber) {
        Node node = new Node(game);
        node.expandNode(playerNumber);

        PriorityQueue<Pair> minHeap = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return new Integer(o1.getCost()).compareTo(o2.getCost());
            }
        });


        for (Node child : node.children) {
            int heuristic = Heuristic.calculateHeuristic(child);
            Pair pair = new Pair(child, heuristic);
            minHeap.add(pair);
        }

        game = minHeap.poll().getNode().game;
// may need change
        return true;
    }
}
