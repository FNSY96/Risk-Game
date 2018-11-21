package gameModeling;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    public ArrayList<Integer>[] adjacencyList;
    private int[] nodeAndOwner;
    private ArrayList<Integer> continentsNumbers;
    private HashMap<Integer, ArrayList<Integer>> continents;
    private Vertex[] vertices;
//    private int numberOfVertices;

    public Graph(int numberOfVertices) {
//        this.numberOfVertices = numberOfVertices;
        this.adjacencyList = new ArrayList[numberOfVertices + 1];
        this.nodeAndOwner = new int[numberOfVertices + 1];
        this.continents = new HashMap<>();
        this.vertices = new Vertex[numberOfVertices + 1];
        this.initializeVerticesArray();
    }

    private void initializeVerticesArray() {
        for (int i = 1; i < vertices.length; i++) {
            vertices[i] = new Vertex();
        }
    }

    public void buildGraph(ArrayList<Point> edges) {
        for (Point p : edges) {
            adjacencyList[p.x].add(p.y);
            adjacencyList[p.y].add(p.x);
        }
    }

    public void buildContinents(ArrayList<ArrayList<Integer>> lines) {
        this.continentsNumbers = new ArrayList<>();
        for (ArrayList arrayList : lines) {
            this.addContinent(arrayList);
        }
    }

    private void addContinent(ArrayList<Integer> line) {
        int continentNumber = line.get(0);
        this.continentsNumbers.add(continentNumber);
        this.continents.put(continentNumber, new ArrayList<>());
        for (int i = 1; i < line.size(); i++) {
            this.continents.get(continentNumber).add(line.get(i));
        }
    }


    public ArrayList<Point> ownedContinents(int playerNumber) {
        ArrayList<Point> ownedContinents = new ArrayList<>();

        for (int i = 0; i < continentsNumbers.size(); i++) {
            int currentContinentNumber = this.continentsNumbers.get(i);
            int ownedCountriesCount = 0;

            ArrayList<Integer> countries = this.continents.get(currentContinentNumber);
            for (int j = 0; j < countries.size(); j++) {
                if (this.nodeAndOwner[countries.get(j)] != playerNumber)
                    break;
                ownedCountriesCount++;
            }

            if (countries.size() == ownedCountriesCount)
                ownedContinents.add(new Point(currentContinentNumber, ownedCountriesCount));
        }
        return ownedContinents;
    }

    public int getOwner(int vertexNumber) {
        return this.nodeAndOwner[vertexNumber];
    }

    public int getTroopsInVertex(int vertexNumber) {
        return this.vertices[vertexNumber].getTroops();
    }

    public boolean isNeighbour(int v1, int v2) {
        return this.adjacencyList[v1].contains(v2);
    }

    public void setNumberOfTroopsInVertex(int vertexNumber, int numberOfTroops) {
        this.vertices[vertexNumber].setTroops(numberOfTroops);
    }

    public void setNodeOwner(int vertexNumber, int ownerNumber) {
        this.nodeAndOwner[vertexNumber] = ownerNumber;
    }
}
