package AIAgents;

import gameModeling.Game;
import nonAIAgents.Agent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import artificialIntelligenceUtilities.Node;
import artificialIntelligenceUtilities.Pair;

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
            this.index = playerNumber;
            this.solutionPath = generateTree(game, playerNumber);
            enterOnce = false;
        }

        System.out.println(index);
        return solutionPath.get(index += 2);
    }

    private ArrayList<Game> generateTree(Game game, int playerNumber) {
        Node node = new Node(game);
        node.expandNode(playerNumber);
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return -1 * new Integer(o1.getCost()).compareTo(o2.getCost());
            }
        });

        while (!node.game.gameEnded()) {
            for (Node child : node.children) {
                child.level = index;
                int heuristic = Heuristic.calculateHeuristic(child, playerNumber);
                Pair pair = new Pair(child, heuristic + index);
                maxHeap.add(pair);
            }

            Node maxHeapNode = maxHeap.poll().getNode();
//            maxHeapNode.game.getGraph().printGraph();

            Game beforePassivePlay = new Game(maxHeapNode.game);
            if (beforePassivePlay.gameEnded()) {
                node = maxHeapNode;
                break;
            }
            //play passive turn
            beforePassivePlay.addTroops(beforePassivePlay.getOpponentNumber(playerNumber));
            beforePassivePlay.deployTroops(beforePassivePlay.getOpponentNumber(playerNumber), beforePassivePlay.getGraph().findMinVertex(beforePassivePlay.getOpponentNumber(playerNumber)));
            Node afterPassivePlay = new Node(beforePassivePlay);

            maxHeapNode.addChild(afterPassivePlay);
            System.out.println("iam child");
//            maxHeapNode.children.get(0).game.getGraph().printGraph();
            node = afterPassivePlay;
            node.expandNode(playerNumber);
        }

//        System.out.println("Heap: " + maxHeap.size());
        return getSolutionPath(node);
    }

    public ArrayList<Game> getSolutionPath(Node lastNode) {
        ArrayList<Game> solutionPath = new ArrayList<>();

        while (lastNode != null) {
            solutionPath.add(0, new Game(lastNode.game));
            lastNode = lastNode.parent;
        }

        System.out.println("Inside: " + solutionPath.size());

        for (Game game : solutionPath)
            game.getGraph().printGraph();
        System.out.println("Done inside");
        return solutionPath;
    }

}