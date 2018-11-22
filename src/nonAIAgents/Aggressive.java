package nonAIAgents;

import gameModeling.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Aggressive extends Agent {

    @Override
    public void performActions(Game game, int playerNumber) {
        int maxVertex = game.getGraph().findMaxVertex(playerNumber);
        game.deployTroops(playerNumber, maxVertex);
        int attackedVertex = this.getOpponentVertexBelongToContinent(game, playerNumber);
        if (attackedVertex > -1) {
            game.attack(maxVertex, attackedVertex);
        } else {
            attackMaxOpponent(game, playerNumber);
        }
    }

    private int getOpponentVertexBelongToContinent(Game game, int playerNumber) {
        int maxVertex = game.getGraph().findMaxVertex(playerNumber);
        int opponentNumber = game.getOpponentNumber(playerNumber);
        ArrayList<Integer> adjacentToMaxVertex = game.getGraph().getAdjacentToVertex(maxVertex);
        Collections.sort(adjacentToMaxVertex);
        // Tie breaking we select the smallest one in the order
        ArrayList<Integer> opponentContinents = game.getOpponentContinents(opponentNumber);

        for (int i = 0; i < adjacentToMaxVertex.size(); i++) {
            int continentOfVertex = game.getGraph().getContinentOfVertex(adjacentToMaxVertex.get(i));
            if (opponentContinents.contains(continentOfVertex)) {
                return adjacentToMaxVertex.get(i);
            }
        }
        return -1;
    }

    private boolean attackMaxOpponent(Game game, int playerNumber) {
        int maxVertex = game.getGraph().findMaxVertex(playerNumber);
        ArrayList<Point> adjacentSortedByTroops = game.getGraph().getVertexAndTroops(maxVertex);
        for (int i = 0; i < adjacentSortedByTroops.size(); i++) {
            int opponentVertex = adjacentSortedByTroops.get(i).x;

            if (game.attack(maxVertex, opponentVertex)) {
                return true;
            }
        }
        return false;
    }
}
