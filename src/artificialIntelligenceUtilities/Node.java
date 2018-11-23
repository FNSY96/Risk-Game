package artificialIntelligenceUtilities;

import gameModeling.Game;

import java.util.*;

public class Node {

    public ArrayList<Node> children = new ArrayList<>();
    public Node parent;
    public Game game;
    public int level = 0;

    public Node(Game game) {
        this.game = game;
    }

    public void expandNode(int playerNumber) {
        ArrayList<Integer> currentPlayerVertices = this.game.getGraph().getVerticesOfPlayer(playerNumber);
        for (int i = 0; i < currentPlayerVertices.size(); i++) {
            ArrayList<Integer> adjacentToCurrentVertex = this.game.getGraph().getAdjacentToVertex(currentPlayerVertices.get(i));
            Game deployChild = new Game(this.game);
            deployChild.addTroops(playerNumber);
            boolean attacked = false;

            for (int j = 0; j < adjacentToCurrentVertex.size(); j++) {
                boolean canAttack = deployChild.canAttack(currentPlayerVertices.get(i), adjacentToCurrentVertex.get(j));
                if (canAttack) {
                    Game childGame = new Game(deployChild);
                    childGame.attack(currentPlayerVertices.get(i), adjacentToCurrentVertex.get(j));
                    Node childNode = new Node(childGame);
                    this.children.add(childNode);
                    childNode.parent = this;
                    childNode.level = this.level + 1;
                    attacked = true;
                }
            }

            if (!attacked) {
                Node childNode = new Node(deployChild);
                this.children.add(childNode);
                childNode.parent = this;
                childNode.level = this.level + 1;
            }
        }
    }
}