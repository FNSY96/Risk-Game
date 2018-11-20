package gameModeling;

import fileReader.FileReturns;
import fileReader.InputFileReader;

public class GameSetup {

    private Graph graph;

    public void GameSetup() {
        InputFileReader in = new InputFileReader();
        FileReturns fileReturns = in.readFile();
        this.graph = new Graph(fileReturns.getNumberOfVertices());
        graph.buildGraph(fileReturns.getEdges());
        graph.buildContinents(fileReturns.getContinents());
    }

}
