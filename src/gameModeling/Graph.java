package gameModeling;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    public ArrayList<Integer>[] adjacencyList;
    public HashMap<Integer, Integer> nodeAndOwner;
    public HashMap<Integer, HashSet<Integer>> continents;
    private int numberOfVertices;

    public Graph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.adjacencyList = new ArrayList[numberOfVertices + 1];
        this.nodeAndOwner = new HashMap<>();
        this.continents = new HashMap<>();
    }

    public void buildGraph(ArrayList<Point> edges) {
        for (Point p : edges) {
            adjacencyList[p.x].add(p.y);
            adjacencyList[p.y].add(p.x);
        }
    }

    public void addToContinent(ArrayList<ArrayList<Integer>> lines) {
        for (ArrayList arrayList : lines) {
            this.addContinent(arrayList);
        }
    }

    private void addContinent(ArrayList<Integer> line) {
        int continentNumber = line.get(0);
        this.continents.put(continentNumber, new HashSet<>());

        for (int i = 1; i < line.size(); i++) {
            this.continents.get(continentNumber).add(line.get(i));
        }
    }

}
