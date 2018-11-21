package gameFlow;

import gameModeling.Game;
import nonAIAgents.Agent;
import nonAIAgents.Passive;

public class Turn {
    private Agent agent0;
    private Agent agent1;
    private Game game;
    private int turn = 0;

    public Turn(Agent agent0, Agent agent1, Game game) {
        this.agent0 = agent0;
        this.agent1 = agent1;
        this.game = game;
    }

    public void alternateTurns() {


    }
}
