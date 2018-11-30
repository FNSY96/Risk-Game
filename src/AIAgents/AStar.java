package AIAgents;

import gameModeling.Game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import artificialIntelligenceUtilities.Node;
import artificialIntelligenceUtilities.Pair;
import nonAIAgents.Passive;

public class AStar extends AIAgent {

    private static int index;
    private static boolean enterOnce;
    private ArrayList<Game> solutionPath;

    public AStar() {
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
        if (this.enterOnce) {
            this.index = playerNumber + 1;
            this.solutionPath = generateTree(new Game(game), playerNumber);
            enterOnce = false;
        }

        Game updatedGame =  solutionPath.get(index);
        index += 2;
        return updatedGame;
    }

    private ArrayList<Game> generateTree(Game game, int playerNumber) {
        Node node = new Node(game);
        node.expandNode(playerNumber);
        Passive passive = new Passive();

        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return -1 * new Integer(o1.getCost()).compareTo(o2.getCost());
            }
        });

        // MAYBE NEED TO BE CHANGED TO node.game.gameEnded();
        while (true) {
            for (Node child : node.children) {
                child.level = index;
                int heuristic = Heuristic.calculateHeuristic(child, playerNumber);
                Pair pair = new Pair(child, heuristic + index);
                child.game.initializeTurn(playerNumber);
                maxHeap.add(pair);
            }

            Node topOfHeapNode = maxHeap.poll().getNode();
            Game topOfHeapGame = topOfHeapNode.game;
            if (topOfHeapGame.gameEnded()) {
                node = topOfHeapNode;
                break;
            }


            int opponentNumber = topOfHeapGame.getOpponentNumber(playerNumber);
            Game toBeChildForTopOfHeapGame = new Game(topOfHeapGame);
            toBeChildForTopOfHeapGame.initializeTurn(opponentNumber);
            passive.agentDeploys(toBeChildForTopOfHeapGame, opponentNumber);

            Node toBeChildForTopOfHeapNode = new Node(toBeChildForTopOfHeapGame);

            topOfHeapNode.addChild(toBeChildForTopOfHeapNode);

            node = toBeChildForTopOfHeapNode;
            node.expandNode(playerNumber);
        }

        return getSolutionPath(node);
    }

    public ArrayList<Game> getSolutionPath(Node lastNode) {
        ArrayList<Game> solutionPath = new ArrayList<>();

        while (lastNode != null) {
            solutionPath.add(0, new Game(lastNode.game));
            lastNode = lastNode.parent;
        }

        return solutionPath;
    }

}