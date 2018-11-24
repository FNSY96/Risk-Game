package AIAgents;

import artificialIntelligenceUtilities.Node;
import artificialIntelligenceUtilities.Pair;
import gameModeling.Game;
import nonAIAgents.Agent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Greedy extends AIAgent {

    @Override
    public boolean agentDeploys(Game game, int playerNumber) {
        return true;
    }

    @Override
    public boolean agentAttacks(Game game, int playerNumber) {
        return true;
    }

    @Override
    public Game performAction(Game game, int playerNumber) {
        Node node = new Node(game);
        node.expandNode(playerNumber);

        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return -1 * new Integer(o1.getCost()).compareTo(o2.getCost());
            }
        });

        for (Node child : node.children) {
            int heuristic = Heuristic.calculateHeuristic(child, playerNumber);
            Pair pair = new Pair(child, heuristic);
            maxHeap.add(pair);
        }

        System.out.println(maxHeap.peek().getNode().level);

        return maxHeap.poll().getNode().game;
    }
}
