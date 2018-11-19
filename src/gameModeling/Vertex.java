package gameModeling;

public class Vertex {
    private int troops;

    public Vertex() {
        this.troops = 0;
    }

    public int getTroops() {
        return this.troops;
    }

    public void setTroops(int troops) {
        this.troops = troops;
    }

    public void addTroops(int troops) {
        this.troops += troops;
    }
}
