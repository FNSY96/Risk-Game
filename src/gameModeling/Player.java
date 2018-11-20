package gameModeling;

public class Player {
    private int availableTroops;
//    private boolean ownsContinent;

    public Player() {
//        this.ownsContinent = false;
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

//    public void setOwnsContinent(boolean ownsContinent) {
//        this.ownsContinent = ownsContinent;
//    }
}
