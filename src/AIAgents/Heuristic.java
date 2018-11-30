package AIAgents;

import java.awt.Point;
import java.util.ArrayList;

import artificialIntelligenceUtilities.Node;
import gameModeling.Game;

public class Heuristic {

    private static final int OWNED_VERTICES_INVERSE_PRIORITY = 1;
//    private static final int BORDER_TROOPS_INVERSE_PRIORITY = 2;
    private static final int OWNED_CONTINENTS_INVERSE_PRIORITY = 3;

    public static int calculateHeuristic(Node node, int playerNumber) {
        Game game = node.game;
        int ownedContinentsBonus = getOwnedContinentsBonus(game, playerNumber);
        int numberOfOwnedVertices = getNumberOfOwnedVertices(game, playerNumber);
        int heuristic =  ownedContinentsBonus + numberOfOwnedVertices;
        System.out.println(heuristic);
        return heuristic;
    }

//    private static int getTotalBorderVerticesValue(Game GUIGame, int playerNumber) {
//        return BORDER_TROOPS_INVERSE_PRIORITY * GUIGame.getGraph().getTotalBordersTroops(playerNumber);
//    }

    private static int getOwnedContinentsBonus(Game game, int playerNumber) {
        int ownedContinentsBonus = 0;
        ArrayList<Point> ownedContinentsOfPlayer = game.getGraph().ownedContinents(playerNumber);
        for (Point p : ownedContinentsOfPlayer) {
            ownedContinentsBonus += p.y;
        }
        return OWNED_CONTINENTS_INVERSE_PRIORITY * ownedContinentsBonus;
    }

    private static int getNumberOfOwnedVertices(Game game, int playerNumber) {
        return OWNED_VERTICES_INVERSE_PRIORITY * game.getGraph().getVerticesOfPlayer(playerNumber).size();
    }
}
