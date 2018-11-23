package gameModeling;

import fileReader.FileReturns;
import fileReader.InputFileReader;
import utilities.*;

import java.awt.*;
import java.util.ArrayList;

public class Game {

    private static final int DEFAULT_TROOPS = 3;
    private static final int ATTACK_BONUS = 2;
    private Graph graph;
    private Player[] players;

    public Game() {
        InputFileReader in = new InputFileReader();
        FileReturns fileReturns = in.readFile();
        this.graph = new Graph(fileReturns.getNumberOfVertices());
        graph.buildGraph(fileReturns.getEdges());
        graph.buildContinents(fileReturns.getContinents());
        players = new Player[2];
        this.initializePlayers();
        this.assignOwners(fileReturns.getNumberOfVertices() + 1);
    }

    public Game(Game game) {
        this.graph = new Graph(game.graph);
        this.players = ArrayUtilities.copyPlayerArray(game.players);
    }

    private void initializePlayers() {
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player();
        }
    }

    public void addTroops(int playerNumber) {
        this.giveDefaultTroops(playerNumber);
        this.giveContinentBonus(playerNumber);
    }

    public ArrayList<Integer> getOpponentContinents(int opponentNumber) {
        this.setOwnedContinents(opponentNumber);
        return this.players[opponentNumber].getOwnedContinents();
    }

    public void setOwnedContinents(int playerNumber) {
        ArrayList<Integer> converted = ArrayListUtilities.convertPointALToIntegerAL(this.graph.ownedContinents(playerNumber));
        this.players[playerNumber].setOwnedContinents(converted);
    }

    private void giveDefaultTroops(int playerNumber) {
        this.players[playerNumber].addTroops(DEFAULT_TROOPS);
    }

    private void giveContinentBonus(int playerNumber) {
        ArrayList<Point> ownedContinents = this.graph.ownedContinents(playerNumber);
        for (int i = 0; i < ownedContinents.size(); i++) {
            this.players[playerNumber].addTroops(ownedContinents.get(i).y);
        }
    }

    public boolean deployTroops(int playerNumber, int vertexNumber) {
        if (playerNumber == graph.getOwner(vertexNumber)) {
            int currentTroopsInVertex = graph.getTroopsInVertex(vertexNumber);
            int playerCurrentTroops = players[playerNumber].getAvailableTroops();
            players[playerNumber].setAvailableTroops(0);
            graph.setNumberOfTroopsInVertex(vertexNumber, playerCurrentTroops + currentTroopsInVertex);
            return true;
        }
        return false;
    }

    public boolean attack(int attackerVertex, int defenderVertex) {    //don't change the lines order in this function
        int remainingArmy = checkAttackingConditions(graph.getTroopsInVertex(attackerVertex), graph.getTroopsInVertex(defenderVertex));
        if (this.canAttack(attackerVertex, defenderVertex)) {
            graph.setNumberOfTroopsInVertex(attackerVertex, remainingArmy - 1);
            graph.setNumberOfTroopsInVertex(defenderVertex, 1);
            this.players[graph.getOwner(attackerVertex)].incrementNumberOfOwnedVertices();
            this.players[graph.getOwner(defenderVertex)].decrementNumberOfOwnedVertices();
            this.alternateOwner(defenderVertex);
            this.players[graph.getOwner(attackerVertex)].setAvailableTroops(ATTACK_BONUS);    //set may need to be removed and add troops should be added
            return true;
        }

        return false;
    }

    public boolean canAttack(int attackerVertex, int defenderVertex) {    //don't change the lines order in this function
        int remainingArmy = checkAttackingConditions(graph.getTroopsInVertex(attackerVertex), graph.getTroopsInVertex(defenderVertex));
        return (graph.isNeighbour(attackerVertex, defenderVertex) && graph.getOwner(attackerVertex) != graph.getOwner(defenderVertex) && remainingArmy > 1);
    }


    public Graph getGraph() {
        return this.graph;
    }

    public Player[] getPlayers() {
        return this.players;
    }

    private int checkAttackingConditions(int attackerArmy, int defenderArmy) {
        return (attackerArmy - defenderArmy);
    }

    private void assignOwners(int arrayLength) {
        for (int i = 1; i < arrayLength; i++) {
            if (i % 2 == 0) {
                graph.setNodeOwner(i, 0);
                this.players[0].incrementNumberOfOwnedVertices();
            } else {
                graph.setNodeOwner(i, 1);
                this.players[1].incrementNumberOfOwnedVertices();
            }
        }
    }

    private void alternateOwner(int vertexNumber) {
        if (graph.getOwner(vertexNumber) == 0) {
            graph.setNodeOwner(vertexNumber, 1);
        } else {
            graph.setNodeOwner(vertexNumber, 0);
        }
    }

    public int getOpponentNumber(int playerNumber) {
        return (playerNumber + 1) % 2;
    }


    public boolean gameEnded() {
        return this.players[0].doesNotOwnVertices() || this.players[1].doesNotOwnVertices();
    }

}
