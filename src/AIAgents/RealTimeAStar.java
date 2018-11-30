package AIAgents;

import gameModeling.Game;
import nonAIAgents.Agent;
import nonAIAgents.Passive;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import artificialIntelligenceUtilities.Node;
import artificialIntelligenceUtilities.Pair;

public class RealTimeAStar extends AIAgent {

    private static int index;
    private static boolean enterOnce;
    private ArrayList<Game> solutionPath;

    public RealTimeAStar() {
        this.enterOnce = true;
        this.solutionPath = new ArrayList<>();
    }

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
        return generateTree(new Game(game), playerNumber);
    }

    
    private Game generateTree(Game game, int playerNumber) {
        Node node = new Node(game);
        node.expandNode(playerNumber);
        Passive passive = new Passive();
        for(Node child : node.children)
        {
            child.game.initializeTurn(playerNumber);
        	int opponentNumber = child.game.getOpponentNumber(playerNumber);
            Game toBeChild = new Game(child.game);
            toBeChild.initializeTurn(opponentNumber);
            passive.agentDeploys(toBeChild, opponentNumber);
            Node toBeChildNode = new Node(toBeChild);
            child.addChild(toBeChildNode);
            toBeChildNode.expandNode(playerNumber);
        }
        
	      PriorityQueue<Pair> maxHeap = new PriorityQueue<>(new Comparator<Pair>() {
	      @Override
	      public int compare(Pair o1, Pair o2) {
	          return -1 * new Integer(o1.getCost()).compareTo(o2.getCost());
	      }
	  });
        
        for(Node child : node.children)
        {
        	int cumHeuristic = 0;
        	for(Node fakhry : child.children.get(0).children)
        	{
        		cumHeuristic += Heuristic.calculateHeuristic(fakhry, playerNumber);
        	}
            Pair pair = new Pair(child, cumHeuristic);
            maxHeap.add(pair);
        }
        return maxHeap.poll().getNode().game;
    }
}
