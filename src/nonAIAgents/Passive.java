package nonAIAgents;

import gameModeling.Game;

public class Passive extends Agent {

    @Override
    public boolean agentDeploys(Game game, int playerNumber) {
        return game.deployTroops(playerNumber, game.getGraph().findMinVertex(playerNumber));
    }

    @Override
    public boolean agentAttacks(Game game, int playerNumber) {
        return false;
    }

}
