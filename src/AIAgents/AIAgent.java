package AIAgents;

import gameModeling.Game;
import nonAIAgents.Agent;

public abstract class AIAgent extends Agent {

    public abstract Game performAction(Game game, int playerNumber);
}
