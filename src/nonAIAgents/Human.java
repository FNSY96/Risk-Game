package nonAIAgents;

import gameModeling.Game;

public class Human extends Agent {


    public boolean agentDeploys(Game game, int playerNumber, int vertexToDeployIn) {
        return game.deployTroops(playerNumber, vertexToDeployIn);
    }

    public boolean agentAttacks(Game game, int playerNumber, int attackerVertex, int opponentVertex) {
        return game.attack(attackerVertex, opponentVertex);
    }
}
