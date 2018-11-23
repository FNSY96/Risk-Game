package gameDriver;

import AIAgents.Greedy;
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
        } else if (playerType.equals(PlayersTypes.GREEDY)) {
            return new Greedy();
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

//        this.game.getGraph().printGraph();

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

        this.game.getGraph().printGraph();

        return game;
    }


    private void performAIAgentAction(Agent agent) {
        if (agent instanceof Greedy) {
            this.game = ((Greedy) agent).performAction(game, turnNumber);
        }
    }


    public Game playAITurn() {

        if (turnNumber == 0) {
            this.initializeTurn(turnNumber);

            this.performAIAgentAction(this.agent0);

            turnNumber = 1;
        } else {
            this.initializeTurn(turnNumber);

            this.performAIAgentAction(this.agent1);

            turnNumber = 0;
        }

        this.game.getGraph().printGraph();

        return game;
    }


//    public Game playHumanDeploymentTurn(int vertexToDeployIn) {
//
//        if (turnNumber == 0) {
//            this.initializeTurn(turnNumber);
//
//            ((Human) this.agent0).agentDeploys(game, turnNumber, vertexToDeployIn);
//
//        } else {
//            this.initializeTurn(turnNumber);
//
//            ((Human) this.agent1).agentDeploys(game, turnNumber, vertexToDeployIn);
//
//        }
//
//        this.game.getGraph().printGraph();
//
//        return game;
//    }
//
//    public Game playHumanAttackTurn(int attackerVertex, int opponentVertex) {
//
//        if (turnNumber == 0) {
//
//            ((Human) this.agent0).agentAttacks(game, turnNumber, attackerVertex, opponentVertex);
//
//            turnNumber = 1;
//        } else {
//
//            ((Human) this.agent1).agentAttacks(game, turnNumber, attackerVertex, opponentVertex);
//
//            turnNumber = 0;
//        }
//
//        this.game.getGraph().printGraph();
//
//        return game;
//    }


}
