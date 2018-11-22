package gameModeling;

import utilities.ArrayListUtilities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Graph implements Cloneable{
    public ArrayList<Integer>[] adjacencyList;
    private int[] nodeAndOwner;
    private ArrayList<Integer> continentsNumbers;
    private HashMap<Integer, ArrayList<Integer>> continents;
    private Vertex[] vertices;

    public Graph(int numberOfVertices) {
        this.adjacencyList = new ArrayList[numberOfVertices + 1];
        this.nodeAndOwner = new int[numberOfVertices + 1];
        this.continents = new HashMap<>();
        this.vertices = new Vertex[numberOfVertices + 1];
        this.initializeVerticesArray();
        this.initializeAdjacencyList();
    }

    private Graph(ArrayList<Integer>[] adjacencyList, int[] nodeAndOwner, ArrayList<Integer> continentsNumbers, HashMap<Integer, ArrayList<Integer>> continents, Vertex[] vertices) {
        this.adjacencyList = adjacencyList.clone();
        this.nodeAndOwner = nodeAndOwner.clone();
        this.continentsNumbers = (ArrayList<Integer>) continentsNumbers.clone();
        this.continents = (HashMap<Integer, ArrayList<Integer>>) continents.clone();
        this.vertices = vertices.clone();
    }

    private void initializeAdjacencyList() {
        for (int i = 0; i < this.adjacencyList.length; i++) {
            this.adjacencyList[i] = new ArrayList<>();
        }
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

    public int findMinVertex(int playerNumber) {
        int vertexWithMinTroops = 0;
        int minTroops = Integer.MAX_VALUE;

        for (int i = 1; i < this.nodeAndOwner.length; i++) {
            if (this.nodeAndOwner[i] == playerNumber) {
                int currentNumberOfTroops = vertices[i].getTroops();
                if (currentNumberOfTroops < minTroops) {
                    minTroops = currentNumberOfTroops;
                    vertexWithMinTroops = i;
                }
            }
        }
        return vertexWithMinTroops;
    }


    public int findMaxVertex(int playerNumber) {
        int vertexWithMaxTroops = 0;
        int maxTroops = Integer.MIN_VALUE;

        for (int i = 1; i < this.nodeAndOwner.length; i++) {
            if (this.nodeAndOwner[i] == playerNumber) {
                int currentNumberOfTroops = vertices[i].getTroops();
                if (currentNumberOfTroops > maxTroops) {
                    maxTroops = currentNumberOfTroops;
                    vertexWithMaxTroops = i;
                }
            }
        }
        return vertexWithMaxTroops;
    }

    public int getNumberOfVertices() {
        return this.adjacencyList.length - 1;
    }

    public int getContinentOfVertex(int vertexNumber) {

        for (int i = 0; i < this.continentsNumbers.size(); i++) {
            int continentNumber = this.continentsNumbers.get(i);
            if (this.continents.get(continentNumber).contains(vertexNumber)) {
                return continentNumber;
            }
        }
        return -1;
    }

    public ArrayList<Integer> getAdjacentToVertex(int vertexNumber) {
        return this.adjacencyList[vertexNumber];
    }

    public ArrayList<Point> getVertexAndTroops(int vertexNumber) {
        ArrayList<Integer> adjacent = this.adjacencyList[vertexNumber];
        ArrayList<Integer> troops = new ArrayList<>();
        for (int i = 0; i < adjacent.size(); i++) {
            troops.add(this.getTroopsInVertex(adjacent.get(i)));
        }

        ArrayList<Point> merged = ArrayListUtilities.mergeIntegerAL(adjacent, troops);
        ArrayListUtilities.sortPointsAL(merged);
        return merged;
    }

    public ArrayList<Integer> getVerticesOfPlayer(int playerNumber) {
        ArrayList<Integer> verticesOfPlayer = new ArrayList<>();
        for (int i = 1; i < adjacencyList.length; i++) {
            if (this.getOwner(i) == playerNumber) {
                verticesOfPlayer.add(i);
            }
        }
        return verticesOfPlayer;
    }

    public Object clone() throws CloneNotSupportedException {
        return (Graph)super.clone();
    }

//    @Override
//    public Graph clone() {
//        return new Graph(this.adjacencyList, this.nodeAndOwner, this.continentsNumbers, this.continents, this.vertices);
//    }


}
