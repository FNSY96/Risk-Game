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

        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return -1 * new Integer(o1.getCost()).compareTo(o2.getCost());
            }
        });
        while(!node.game.gameEnded())
        {
	        for (Node child : node.children) {
	        	child.level = level;
	            int heuristic = Heuristic.calculateHeuristic(child, playerNumber);
	            Pair pair = new Pair(child, heuristic + level);
	            maxHeap.add(pair);
	        }
	        
	        Node maxHeapNode = maxHeap.peek().getNode();
	        Game beforePassivePlay = new Game(maxHeapNode.game);
	        //play passive turn
	        beforePassivePlay.deployTroops(game.getOpponentNumber(playerNumber), game.getGraph().findMinVertex(game.getOpponentNumber(playerNumber)));
	        Node afterPassivePlay = new Node(beforePassivePlay);
	        if(afterPassivePlay.game.gameEnded())
	        {
	        	break;
	        }
	        maxHeapNode.addChild(afterPassivePlay);
	        node = maxHeapNode;
	        node.expandNode(playerNumber);
        }
        
        return maxHeap.poll().getNode().game;
    }
    
    public ArrayList<Game> getSolutionPath()
    {
    	
    }

}
