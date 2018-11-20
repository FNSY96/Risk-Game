package fileReader;
import java.awt.*;
import java.util.ArrayList;

public class FileReturns {
    private ArrayList<Point> edges;
    private ArrayList<ArrayList<Integer>> continents;

    public FileReturns(ArrayList<Point> edges, ArrayList<ArrayList<Integer>> continents) {
        this.edges = edges;
        this.continents = continents;
    }

    public ArrayList<Point> getEdges() {
        return this.edges;
    }

    public ArrayList<ArrayList<Integer>> getContinents() {
        return this.continents;
    }
}
