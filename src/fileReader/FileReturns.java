package fileReader;

import java.awt.*;
import java.util.ArrayList;

public class FileReturns {
    private ArrayList<Point> edges;
    private ArrayList<ArrayList<Integer>> continents;
    private int numberOfVertices;

    public FileReturns(ArrayList<Point> edges, ArrayList<ArrayList<Integer>> continents, int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.edges = edges;
        this.continents = continents;
    }

    public ArrayList<Point> getEdges() {
        return this.edges;
    }

    public ArrayList<ArrayList<Integer>> getContinents() {
        return this.continents;
    }

    public int getNumberOfVertices() {
        return this.numberOfVertices;
    }
}
