package artificialIntelligenceUtilities;

import gameModeling.Game;

import java.util.ArrayList;

public class OperationsOnNode {
    public static void expandNode(Node currentNode, int playerNumber) {
        ArrayList<Integer> currentPlayerVertices = currentNode.game.getGraph().getVerticesOfPlayer(playerNumber);
        for (int i = 0; i < currentPlayerVertices.size(); i++) {
            ArrayList<Integer> adjacentToCurrentVertex = currentNode.game.getGraph().getAdjacentToVertex(currentPlayerVertices.get(i));
            Game deployChild = new Game(currentNode.game);
            deployChild.addTroops(playerNumber);
            boolean attacked = false;

            for (int j = 0; j < adjacentToCurrentVertex.size(); j++) {
                boolean canAttack = deployChild.canAttack(currentPlayerVertices.get(i), adjacentToCurrentVertex.get(j));
                if (canAttack) {
                    Game childGame = new Game(deployChild);
                    childGame.attack(currentPlayerVertices.get(i), adjacentToCurrentVertex.get(j));
                    Node childNode = new Node(childGame);
                    currentNode.children.add(childNode);
                    childNode.parent = currentNode;
                    childNode.level = currentNode.level + 1;
                    attacked = true;
                }
            }

            if (!attacked) {
                Node childNode = new Node(deployChild);
                currentNode.children.add(childNode);
                childNode.parent = currentNode;
                childNode.level = currentNode.level + 1;
            }
        }
    }
}
