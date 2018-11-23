package AIAgents;

import gameModeling.Game;
import nonAIAgents.Agent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import artificialIntelligenceUtilities.Node;
import artificialIntelligenceUtilities.Pair;

public class AStar extends AIAgent {

	private static int level ;
    @Override
    public boolean agentDeploys(Game game, int playerNumber) {
        return false;
    }

    @Override
    public boolean agentAttacks(Game game, int playerNumber) {
        return false;
    }
    
    @Override
    public Game performAction(Game game, int playerNumber) {
    	level++;
        Node node = new Node(game);
        node.expandNode(playerNumber);

        PriorityQueue<Pair> minHeap = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return new Integer(o1.getCost()).compareTo(o2.getCost());
            }
        });

        for (Node child : node.children) {
        	child.level = level;
            int heuristic = Heuristic.calculateHeuristic(child, playerNumber);
            Pair pair = new Pair(child, heuristic + level);
            minHeap.add(pair);
        }

        return minHeap.poll().getNode().game;
    }

}
