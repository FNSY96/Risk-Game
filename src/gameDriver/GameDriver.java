package gameDriver;

import AIAgents.AStar;
import AIAgents.Greedy;
import AIAgents.RealTimeAStar;

import gameModeling.Game;
import nonAIAgents.*;

public class GameDriver {

    private Agent agent0;
    private Agent agent1;
    private Game game;
    private int turnNumber;

    public Game getGame() {

        return game;

    }

    public GameDriver(PlayersTypes player0, PlayersTypes player1) {
        this.agent0 = this.setAgents(player0);
        this.agent1 = this.setAgents(player1);
        this.game = new Game();
        this.turnNumber = 0;
        this.initializeTurn(this.turnNumber);
    }

    public int getTurn() {

        return turnNumber;

    }

    private Agent setAgents(PlayersTypes playerType) {
        if (playerType.equals(PlayersTypes.PASSIVE)) {
            return new Passive();
        } else if (playerType.equals(PlayersTypes.AGGRESSIVE)) {
            return new Aggressive();
        } else if (playerType.equals(PlayersTypes.PACIFIST)) {
            return new Pacifist();
        } else if (playerType.equals(PlayersTypes.GREEDY)) {
            return new Greedy();
        } else if (playerType.equals(PlayersTypes.A_STAR)) {
            return new AStar();
        } else if (playerType.equals(PlayersTypes.REAL_TIME_A_STAR)) {
            return new RealTimeAStar();
        } else if (playerType.equals(PlayersTypes.HUMAN)) {
            return new Human();
        }
        return null;
    }


    public void initializeTurn(int playerNumber) {
        this.game.addTroops(playerNumber);
        this.game.setOwnedContinents(playerNumber);
    }

    private boolean performAgentDeployment(Agent agent) {
        return agent.agentDeploys(game, turnNumber);
    }

    private boolean performAgentAttack(Agent agent) {

        return agent.agentAttacks(game, turnNumber);
    }


    public Game playDeploymentTurn() {
        if (turnNumber == 0) {
            this.performAgentDeployment(this.agent0);

        } else {
            this.performAgentDeployment(this.agent1);

        }

        return game;
    }


    public Game playAttackTurn() {

        if (turnNumber == 0) {
            this.performAgentAttack(this.agent0);
        } else {

            this.performAgentAttack(this.agent1);
        }
        
        return game;
    }

    public void changeTurn() {

        turnNumber = (turnNumber + 1) % 2;

    }


    private void performAIAgentAction(Agent agent) {
        if (agent instanceof Greedy) {
            this.game = ((Greedy) agent).performAction(game, turnNumber);
        } else if (agent instanceof AStar) {
            this.game = ((AStar) agent).performAction(game, turnNumber);
        } else if (agent instanceof RealTimeAStar) {
            this.game = ((RealTimeAStar) agent).performAction(game, turnNumber);
        }
    }


    public Game playAITurn() {

        if (turnNumber == 0) {
            this.performAIAgentAction(this.agent0);
        } else {
            this.performAIAgentAction(this.agent1);
        }

        return game;
    }


    public Game playHumanDeploymentTurn(int vertexToDeployIn) {

        if (turnNumber == 0) {
            ((Human) this.agent0).agentDeploys(game, turnNumber, vertexToDeployIn);

        } else {
            ((Human) this.agent1).agentDeploys(game, turnNumber, vertexToDeployIn);
        }

        return game;
    }

    public Game playHumanAttackTurn(int attackerVertex, int opponentVertex) {

        if (turnNumber == 0) {

            ((Human) this.agent0).agentAttacks(game, turnNumber, attackerVertex, opponentVertex);

        } else {

            ((Human) this.agent1).agentAttacks(game, turnNumber, attackerVertex, opponentVertex);

        }

        return game;
    }


}
