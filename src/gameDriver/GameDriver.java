package gameDriver;

import gameModeling.Game;
import nonAIAgents.Agent;
import nonAIAgents.Aggressive;
import nonAIAgents.Passive;

public class GameDriver {


    private Agent agent0;
    private Agent agent1;
    private Game game;
    private int turnNumber;

    public GameDriver(PlayersTypes player0, PlayersTypes player1) {
        this.agent0 = this.setAgents(player0);
        this.agent1 = this.setAgents(player1);
        this.game = new Game();
        this.turnNumber = 0;
    }

    private Agent setAgents(PlayersTypes playerType) {
        if (playerType.equals(PlayersTypes.PASSIVE)) {
            return new Passive();
        } else if (playerType.equals(PlayersTypes.HUMAN)) {
            return new Aggressive();
        }
        return null;
        // to be changed remove null :D
    }


    public Game playTurn() {
    	if(turnNumber == 0)
    	{
    		((Passive)agent0).performActions(game,turnNumber);
    		turnNumber = 1;
    	}else
    	{
    		((Passive)agent1).performActions(game,turnNumber);
    		turnNumber = 0;
    	}
        return game;
    }


}
