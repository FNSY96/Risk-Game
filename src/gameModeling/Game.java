package gameModeling;

import fileReader.FileReturns;
import fileReader.InputFileReader;

import java.awt.*;
import java.util.ArrayList;

public class Game {

    private static final int DEFAULT_TROOPS = 3;
    private Graph graph;
    private Player[] players;

    public void GameSetup() {
        InputFileReader in = new InputFileReader();
        FileReturns fileReturns = in.readFile();
        this.graph = new Graph(fileReturns.getNumberOfVertices());
        graph.buildGraph(fileReturns.getEdges());
        graph.buildContinents(fileReturns.getContinents());
        graph.assignOwners();
        players = new Player[2];
        this.initializePlayers();
    }

    private void initializePlayers() {
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player();
        }
    }

    private void addTroops(int playerNumber) {
        this.giveDefaultTroops(playerNumber);
        this.giveBonusTroops(playerNumber);
    }

    private void giveDefaultTroops(int playerNumber) {
        this.players[playerNumber].setAvailableTroops(DEFAULT_TROOPS);
    }

    private void giveBonusTroops(int playerNumber) {
        ArrayList<Point> ownedContinents = this.graph.ownedContinents(playerNumber);
        for (int i = 0; i < ownedContinents.size(); i++) {
            this.players[playerNumber].addTroops(ownedContinents.get(i).y);
        }
    }

//    private boolean battle()


    public Graph getGraph() {
        return this.graph;
    }

    public Player[] getPlayers() {
        return this.players;
    }


}
