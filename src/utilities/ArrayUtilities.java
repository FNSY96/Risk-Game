package utilities;

import gameModeling.Player;
import gameModeling.Vertex;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayUtilities {
    public static Player[] copyPlayerArray(Player[] players) {
        Player[] copy = new Player[players.length];

        for (int i = 0; i < players.length; i++) {
            copy[i] = new Player(players[i]);
        }
        return copy;
    }

    public static ArrayList<Integer>[] copyAdjacencyList(ArrayList<Integer>[] adjacencyList) {
        ArrayList<Integer>[] copy = new ArrayList[adjacencyList.length];

        for (int i = 0; i < adjacencyList.length; i++) {
            copy[i] = new ArrayList<>(adjacencyList[i]);
            if (copy[i] == adjacencyList[i]) {
                throw new RuntimeException("SHALLOW COPY!");
            }
        }

        return copy;
    }

    public static Vertex[] copyVertexArray(Vertex[] vertices) {
        Vertex[] copy = new Vertex[vertices.length];

        for (int i = 1; i < vertices.length; i++) {
            copy[i] = new Vertex(vertices[i]);
        }

        return copy;
    }

//    public static int[] copyIntArray(int[] array) {
//        int[] copy = new int[array.length];
//        for (int i = 1; i < array.length; i++) {
//            copy[i] = array[i];
//        }
//
//        return copy;
//    }
}
