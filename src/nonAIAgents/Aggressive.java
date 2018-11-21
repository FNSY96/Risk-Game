package nonAIAgents;

import gameModeling.Game;

public class Aggressive extends Agent{

    @Override
    public void performActions(Game game,int playerNumber) {
    	int maxVertex = game.getGraph().findMaxVertex(playerNumber);
    	game.deployTroops(playerNumber, maxVertex);
    	//game.attack(maxVertex, defenderVertex);
    }
}
