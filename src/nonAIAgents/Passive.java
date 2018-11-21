package nonAIAgents;

import gameModeling.Game;

public class Passive extends Agent {

    @Override
    public void performActions(Game game, int playerNumber) {

        boolean deployed = game.deployTroops(playerNumber, game.getGraph().findMinVertex(playerNumber));
    }


}
