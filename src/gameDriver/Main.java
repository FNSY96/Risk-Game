package gameDriver;

import gameModeling.Game;
import gameModeling.Graph;
import gameModeling.Player;
import utilities.ArrayListUtilities;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    private static void printGraph(Game game) {
        for (int i = 1; i < game.getGraph().adjacencyList.length; i++) {
            System.out.print(i + " Owner: " + game.getGraph().getOwner(i) + ": ");
            System.out.println(game.getGraph().getTroopsInVertex(i));
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        GameDriver driver = new GameDriver(PlayersTypes.AGGRESSIVE, PlayersTypes.PASSIVE);


//
//        driver.playDeploymentTurn();
//        driver.playAttackTurn();
//
//        driver.playDeploymentTurn();
//        driver.playAttackTurn();
//
//        driver.playDeploymentTurn();
//        driver.playAttackTurn();
//
//        driver.playDeploymentTurn();
//        driver.playAttackTurn();

//        Game game = driver.playDeploymentTurn();
//
//        while (!game.gameEnded()) {
//            game = driver.playAttackTurn();
//            if (!game.gameEnded())
//                game = driver.playDeploymentTurn();
//        }


    }

}
