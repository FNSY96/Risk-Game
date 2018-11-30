package gameDriver;

public class Main {

//    private static void printGraph(Game game) {
//        for (int i = 1; i < game.getGraph().adjacencyList.length; i++) {
//            System.out.print(i + " Owner: " + game.getGraph().getOwner(i) + ": ");
//            System.out.println(game.getGraph().getTroopsInVertex(i));
//            System.out.println();
//        }
//        System.out.println();
//        System.out.println();
//    }
//
//    public static void main(String[] args) {
//
//        GameDriver driver = new GameDriver(PlayersTypes.HUMAN, PlayersTypes.PASSIVE);
//        driver.getGame().getGraph().printGraph();
//
//        while (true) {
//            Scanner s = new Scanner(System.in);
//
//            driver.playHumanDeploymentTurn(s.nextInt());
//            Game game = driver.playHumanAttackTurn(s.nextInt(), s.nextInt());
//
//            if (game.gameEnded())
//                break;
//            game = driver.playDeploymentTurn();
//            game = driver.playAttackTurn();
//            if (game.gameEnded())
//                break;
//        }
//
//    }

    public static void main(String[] args) {
        GameDriver driver = new GameDriver(PlayersTypes.A_STAR, PlayersTypes.PASSIVE);
        while (!driver.getGame().gameEnded()) {
            driver.playAITurn();
//          driver.getGame().getGraph().printGraph();
            driver.changeTurn();
            driver.initializeTurn(driver.getTurn());
            driver.playDeploymentTurn();
            driver.changeTurn();
            driver.initializeTurn(driver.getTurn());
           // System.out.println("PASSIVE TURN");
          //driver.getGame().getGraph().printGraph();
         // System.out.println("DD");
        }
    }

}
