package gameModeling;

public class Player {
    int playerNumber;
    private int availableTroops;
    private boolean ownsContinent;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        this.ownsContinent = false;
    }

    public void addTroops(int newTroops) {
        this.availableTroops += newTroops;
    }

    public void removeTroops() {
        this.availableTroops = 0;
    }

    public void setOwnsContinent(boolean ownsContinent) {
        this.ownsContinent = ownsContinent;
    }
}
