package gameModeling;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    private int availableTroops;
    private int numberOfOwnedVertices;
    private ArrayList<Integer> ownedContinents;

    public Player() {
        this.availableTroops = 0;
        this.numberOfOwnedVertices = 0;
        this.ownedContinents = new ArrayList<>();
    }

    public void setAvailableTroops(int troops) {
        this.availableTroops = troops;
    }

    public void addTroops(int newTroops) {
        this.availableTroops += newTroops;
    }

    public int getAvailableTroops() {
        return this.availableTroops;
    }

    public void removeTroops() {
        this.availableTroops = 0;
    }

    public int getNumberOfOwnedVertices() {
        return this.numberOfOwnedVertices;
    }

    public void incrementNumberOfOwnedVertices() {
        this.numberOfOwnedVertices++;
    }

    public void decrementNumberOfOwnedVertices() {
        this.numberOfOwnedVertices--;
    }

    public void setNumberOfOwnedVertices(int numberOfOwnedVertices) {
        this.numberOfOwnedVertices = numberOfOwnedVertices;
    }

    public boolean doesNotOwnVertices() {
        return (this.numberOfOwnedVertices == 0);
    }

    public void setOwnedContinents(ArrayList<Integer> ownedContinents) {
        this.ownedContinents = ownedContinents;
    }

    public ArrayList<Integer> getOwnedContinents() {
        return this.ownedContinents;
    }

}
