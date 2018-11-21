package gameModeling;

import fileReader.FileReturns;
import fileReader.InputFileReader;

import java.awt.*;
import java.util.ArrayList;

public class Game {

    private static final int DEFAULT_TROOPS = 3;
    private static final int ATTACK_BONUS = 2;
    private Graph graph;
    private Player[] players;

    public void Game() {
        InputFileReader in = new InputFileReader();
        FileReturns fileReturns = in.readFile();
        this.graph = new Graph(fileReturns.getNumberOfVertices());
        graph.buildGraph(fileReturns.getEdges());
        graph.buildContinents(fileReturns.getContinents());
        this.assignOwners(fileReturns.getNumberOfVertices() + 1);
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
        this.giveContinentBonus(playerNumber);
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
    
    public boolean deployTroops(int playerNumber, int vertexNumber)
    {
    	if(playerNumber == graph.getOwner(vertexNumber))
    	{
    		int currentTroopsInVertex = graph.getTroopsInVertex(vertexNumber);
    		int playerCurrentTroops = players[playerNumber].getAvailableTroops();
    		players[playerNumber].setAvailableTroops(0); ;
    		graph.setNumberOfTroopsInVertex(vertexNumber, playerCurrentTroops + currentTroopsInVertex);
    		return true;
    	}
    	return false;
    }

    public boolean attack(int attackerVertex,int defenderVertex)
    {	//don't change the lines order in this function
    	int remainingArmy = checkAttackingConditions(graph.getTroopsInVertex(attackerVertex),graph.getTroopsInVertex(defenderVertex));
    	if(graph.isNeighbour(attackerVertex, defenderVertex)&& graph.getOwner(attackerVertex) != graph.getOwner(defenderVertex) && remainingArmy > 1)
    	{
    		graph.setNumberOfTroopsInVertex(attackerVertex, remainingArmy -1);
    		graph.setNumberOfTroopsInVertex(defenderVertex, 1);
    		this.players[graph.getOwner(attackerVertex)].incrementNumberOfOwnedVertices();
    		this.players[graph.getOwner(defenderVertex)].decrementNumberOfOwnedVertices();
    		this.alternateOwner(defenderVertex);
    		this.players[graph.getOwner(attackerVertex)].setAvailableTroops(ATTACK_BONUS);    //set may need to be removed and addtroops should be added
    		return true;
    	}
    	
    return false;	
    }
    

    public Graph getGraph() {
        return this.graph;
    }

    public Player[] getPlayers() {
        return this.players;
    }

    private int checkAttackingConditions(int attackerArmy,int defenderArmy)
    {
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
    
	private void alternateOwner(int vertexNumber)
	{
		if(graph.getOwner(vertexNumber) == 0)
		{
			graph.setNodeOwner(vertexNumber, 1);
		}else
		{
			graph.setNodeOwner(vertexNumber, 0);
		}
	}
}
