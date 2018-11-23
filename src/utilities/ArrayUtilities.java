package utilities;

import gameModeling.Player;

import java.util.Arrays;

public class ArrayUtilities {
    public static Player[] copyPlayerArray(Player[] players) {
        Player[] copy = new Player[players.length];

        for (int i = 0; i < players.length; i++) {
            copy[i] = new Player(players[i]);
        }
        return copy;
    }
}
