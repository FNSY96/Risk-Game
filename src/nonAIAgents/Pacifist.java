package nonAIAgents;

import java.awt.Point;
import java.util.ArrayList;

import gameModeling.Game;

public class Pacifist extends Agent {

    @Override
    public void performActions(Game game, int playerNumber) {
        boolean deployed = game.deployTroops(playerNumber, game.getGraph().findMinVertex(playerNumber));
        ArrayList<Point> possibleAttacks = getPossibleAttacks(game,playerNumber);
        Point pairOfAttack = getPairOfAttack(game,possibleAttacks);
        if(pairOfAttack.x != -1)
        {
        	game.attack(pairOfAttack.x, pairOfAttack.y);
        }
    }

	private ArrayList<Point> getPossibleAttacks(Game game,int playerNumber) {
		ArrayList<Point> possibleAttacks = new ArrayList<>();
		ArrayList<Integer> verticesOfPlayer = game.getGraph().getVerticesOfPlayer(playerNumber);
		for(int i =0; i < verticesOfPlayer.size(); i++)
		{
			ArrayList<Integer> adjacentToCurrentVertex = game.getGraph().getAdjacentToVertex(verticesOfPlayer.get(i));
			for(int j= 0; j < adjacentToCurrentVertex.size(); j++)
			{
				if(game.canAttack(verticesOfPlayer.get(i), adjacentToCurrentVertex.get(j)))
				{
					possibleAttacks.add(new Point(verticesOfPlayer.get(i), adjacentToCurrentVertex.get(j)));
				}
			}
		}
		return possibleAttacks;
	}
	
	private Point getPairOfAttack(Game game,ArrayList<Point> possibleAttacks)
	{
		Point pairOfAttack = new Point(-1,-1);
		int minTroops = Integer.MAX_VALUE;
		for(int i=0;i<possibleAttacks.size();i++)
		{
			int numberOfTroopsInAttackedVertex = game.getGraph().getTroopsInVertex(possibleAttacks.get(i).y);
			if(minTroops > numberOfTroopsInAttackedVertex)
			{
				pairOfAttack.x = possibleAttacks.get(i).x;
				pairOfAttack.y = possibleAttacks.get(i).y;
				minTroops = numberOfTroopsInAttackedVertex;
			}
		}
		return pairOfAttack;
	}

}
