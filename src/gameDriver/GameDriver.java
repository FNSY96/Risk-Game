package gameDriver;

import gameFlow.Turn;
import gameModeling.Game;
import nonAIAgents.Agent;
import nonAIAgents.Human;
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
    }

    private Agent setAgents(PlayersTypes playerType) {
        if (playerType.equals(PlayersTypes.PASSIVE)) {
            return new Passive();
        } else if (playerType.equals(PlayersTypes.HUMAN)) {
            return new Human();
        }
        return null;
        // to be changed remove null :D
    }


    public Game playTurn() {


        return game;
    }


}
