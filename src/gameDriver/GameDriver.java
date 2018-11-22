package gameDriver;

import gameModeling.Game;
import nonAIAgents.*;

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
        } else if (playerType.equals(PlayersTypes.AGGRESSIVE)) {
            return new Aggressive();
        } else if (playerType.equals(PlayersTypes.PACIFIST)) {
            return new Pacifist();
        }
        return null;
        // to be changed remove null :D
    }


    private void initializeTurn(int playerNumber) {
        this.game.addTroops(playerNumber);
        this.game.setOwnedContinents(playerNumber);
        // extra
    }

    private boolean performAgentDeployment(Agent agent) {
        if (agent instanceof Passive) {
            return ((Passive) agent).agentDeploys(game, turnNumber);
        } else if (agent instanceof Aggressive) {
            return ((Aggressive) agent).agentDeploys(game, turnNumber);
        } else if (agent instanceof Pacifist) {
            return ((Pacifist) agent).agentDeploys(game, turnNumber);
        } else if(agent instanceof Human) {
            return ((Human) agent).agentDeploys(game, turnNumber);
        }

        return false;
    }

    private boolean performAgentAttack(Agent agent) {
        if (agent instanceof Passive) {
            return ((Passive) agent).agentAttacks(game, turnNumber);
        } else if (agent instanceof Aggressive) {
            return ((Aggressive) agent).agentAttacks(game, turnNumber);
        } else if (agent instanceof Pacifist) {
            return ((Pacifist) agent).agentAttacks(game, turnNumber);
        } else if(agent instanceof Human) {
            return ((Human) agent).agentAttacks(game, turnNumber);
        }
        return false;
    }


    public Game playDeploymentTurn() {

        if (turnNumber == 0) {
            this.initializeTurn(turnNumber);

            this.performAgentDeployment(this.agent0);

        } else {
            this.initializeTurn(turnNumber);

            this.performAgentDeployment(this.agent1);

        }

        this.printGraph();

        return game;
    }

    public Game playAttackTurn() {

        if (turnNumber == 0) {

            this.performAgentAttack(this.agent0);

            turnNumber = 1;
        } else {

            this.performAgentAttack(this.agent1);

            turnNumber = 0;
        }

        this.printGraph();

        return game;
    }

    private void printGraph() {
        for (int i = 1; i < this.game.getGraph().adjacencyList.length; i++) {
            System.out.print(i + " Owner: " + this.game.getGraph().getOwner(i) + ": ");
            System.out.println(this.game.getGraph().getTroopsInVertex(i));
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }


}
