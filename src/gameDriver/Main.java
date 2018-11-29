package gameDriver;

import gameModeling.Game;

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
        GameDriver driver = new GameDriver(PlayersTypes.PASSIVE, PlayersTypes.REAL_TIME_A_STAR);

        while (true) {
            driver.playDeploymentTurn();
            Game game = driver.playAttackTurn();

            if (game.gameEnded())
                break;
            game = driver.playAITurn();
            if (game.gameEnded())
                break;
        }

    }

}
