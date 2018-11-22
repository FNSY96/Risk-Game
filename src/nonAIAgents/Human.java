package nonAIAgents;

import gameModeling.Game;

public class Human extends Agent {

    private Game game;
    private int playerNumber;

    @Override
    public boolean agentDeploys(Game game, int playerNumber) {
        this.game = game;
        this.playerNumber = playerNumber;
        return false;
    }

    public boolean deployHumanTroops(int vertexToDeployIn) {
        return this.game.deployTroops(this.playerNumber, vertexToDeployIn);
    }

    public boolean humanAttack(int attackerVertex, int opponentVertex) {
        return this.game.attack(attackerVertex, opponentVertex);
    }
}
