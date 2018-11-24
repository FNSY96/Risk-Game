package fileReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;

public class InputFileReader {
    private int numOfEdges;
    private int numOfContinents;
    private int lineNum = 0;


    public FileReturns readFile() {
        return this.readFile("input.txt");
    }

    public FileReturns readFile(String filePath) {
        ArrayList<Point> edges = new ArrayList<>();
        ArrayList<ArrayList<Integer>> continents = new ArrayList<>();

        BufferedReader reader;
        int numberOfVertices = 0;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine().toUpperCase();
            while (line != null) {
                line = line.toUpperCase();
                if (lineNum == 0) {
                    numberOfVertices = getNumOfVertices(line);
                } else if (lineNum == 1) {
                    getNumOfEdges(line);
                } else if (lineNum > 1 && lineNum <= (this.numOfEdges + 1)) {
                    extractEdge(line, edges);
                } else if (lineNum == (this.numOfEdges + 2)) {
                    getNumOfContinents(line);
                } else if (lineNum > (this.numOfEdges + 2)) {
                    extractContinent(line, continents);
                }
                line = reader.readLine();
                lineNum++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new FileReturns(edges, continents, numberOfVertices);
    }

    private int getNumOfVertices(String line) {
        return Integer.valueOf(line.replace("V", "").replaceAll("\\s+", ""));
    }

    private void getNumOfContinents(String line) {
        line = line.replace("P", "").replaceAll("\\s+", "");
        this.numOfContinents = Integer.valueOf(line);
    }

    private void getNumOfEdges(String line) {
        line = line.replace("E", "").replaceAll("\\s+", "");
        this.numOfEdges = Integer.valueOf(line);
    }

    private void extractEdge(String line, ArrayList<Point> edges) {
        Point p = new Point();
        line = line.replace("(", "").replace(")", "");
        String[] split = line.split(" ");
        p.x = Integer.parseInt(split[0]);
        p.y = Integer.parseInt(split[1]);
        edges.add(p);
    }

    private void extractContinent(String line, ArrayList<ArrayList<Integer>> continents) {
        ArrayList<Integer> list = new ArrayList<>();
        String[] split = line.split(" ");
        for (int i = 0; i < split.length; i++) {
            list.add(Integer.parseInt(split[i]));
        }

        continents.add(list);
    }
}