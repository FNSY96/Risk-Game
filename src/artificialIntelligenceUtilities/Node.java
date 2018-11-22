package artificialIntelligenceUtilities;

import gameModeling.Game;

import java.util.*;

public class Node {

    public ArrayList<Node> children = new ArrayList<>();
    public Node parent;
    public Game game;
    public int level = 0;

    public Node(Game game) {
        this.game = game;
    }
}