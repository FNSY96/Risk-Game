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
        ArrayList<Point> edges = new ArrayList<>();
        ArrayList<ArrayList<Integer>> continents = new ArrayList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            while (line != null) {
                if (lineNum == 1) {
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

        return new FileReturns(edges, continents);
    }

    private void getNumOfContinents(String line) {
        line = line.replace("P ", "");
        this.numOfContinents = Integer.valueOf(line);
    }

    private void getNumOfEdges(String line) {
        line = line.replace("E ", "");
        this.numOfEdges = Integer.valueOf(line);
    }

    private void extractEdge(String line, ArrayList<Point> edges) {
        Point p = new Point();
        line = line.substring(1, 4);
        p.x = convertCharToInt(line, 0);
        p.y = convertCharToInt(line, 2);
        edges.add(p);
    }

    private void extractContinent(String line, ArrayList<ArrayList<Integer>> continents) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(convertCharToInt(line, 0));
        list.add(convertCharToInt(line, 2));
        list.add(convertCharToInt(line, 4));
        continents.add(list);
    }

    private int convertCharToInt(String line, int index) {
        return (int) line.charAt(index) - 48;
    }

    public static void main(String[] args) {
        InputFileReader in = new InputFileReader();
        in.readFile();
    }

}