package gameDriver;

import gameModeling.Game;
import utilities.ArrayListUtilities;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        GameDriver driver = new GameDriver(PlayersTypes.PACIFIST, PlayersTypes.PASSIVE);


        Game game = driver.playDeploymentTurn();

        while (!game.gameEnded()) {
            game = driver.playAttackTurn();
            if (!game.gameEnded())
                game = driver.playDeploymentTurn();
        }
    }

}
