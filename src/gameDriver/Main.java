package gameDriver;

import gameModeling.Game;
import gameModeling.Graph;
import gameModeling.Player;
import utilities.ArrayListUtilities;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        GameDriver driver = new GameDriver(PlayersTypes.PASSIVE, PlayersTypes.PASSIVE);

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

        Game game = new Game();

        Game game2 = new Game(game);

        game2.getGraph().adjacencyList[1].set(0, -111);
        game2.players[0].addTroops(1000);

        ArrayList<Integer> ad = new ArrayList<>();
        ad.add(3);
        ad.add(33);

        ArrayList<Integer> ad2 = new ArrayList<>();
        ad2.add(-1);
        ad2.add(-3);
        game.players[0].setOwnedContinents(ad);
        game2.players[0].setOwnedContinents(ad2);

        System.out.println(game.players[0].getOwnedContinents());
        System.out.println(game2.players[0].getOwnedContinents());

        System.out.println(game.players == game2.players);
        System.out.println(game.players[0].getOwnedContinents().get(0) == game2.players[0].getOwnedContinents().get(0));
//

        System.out.println(game.getGraph().hashCode());
        System.out.println(game2.getGraph().hashCode());
    }

}
