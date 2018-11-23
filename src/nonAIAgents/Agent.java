package nonAIAgents;

import gameModeling.Game;

public abstract class Agent {
    public abstract boolean agentDeploys(Game game, int playerNumber);

    public abstract boolean agentAttacks(Game game, int playerNumber);
}
