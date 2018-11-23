package AIAgents;

import java.awt.Point;
import java.util.ArrayList;

import artificialIntelligenceUtilities.Node;
import gameModeling.Game;

public class Heuristic {

    public static int calculateHeuristic(Node node,int playerNumber) {
    	Game game = node.game;
    	int totalBorderVerticesValue = getTotalBorderVerticesValue(game,playerNumber);
    	int ownedContinentsBonus = getOwnedContinentsBonus(game,playerNumber);
    	int numberOfOwnedVertices = getNumberOfOwnedVertices(game,playerNumber);
    	int heuristic = totalBorderVerticesValue + ownedContinentsBonus + numberOfOwnedVertices;
    	return heuristic;
    }
    
    private static int getTotalBorderVerticesValue(Game game,int playerNumber)
    {    	
    	return 2*game.getGraph().getTotalBordersTroops(playerNumber);
    }
    
    private static int getOwnedContinentsBonus(Game game,int playerNumber)
    {
    	int ownedContinentsBonus = 0;
    	ArrayList<Point> ownedContinentsOfPlayer = game.getGraph().ownedContinents(playerNumber);
    	for(Point p : ownedContinentsOfPlayer)
    	{
    		ownedContinentsBonus += p.y; 
    	}
    	return 3*ownedContinentsBonus;
    }
    
    private static int getNumberOfOwnedVertices(Game game,int playerNumber)
    {
    	return 1*game.getGraph().getVerticesOfPlayer(playerNumber).size();
    }
}
