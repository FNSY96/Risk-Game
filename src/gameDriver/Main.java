package gameDriver;

public class Main {

    public static void main(String[] args) {
        GameDriver driver = new GameDriver(PlayersTypes.A_STAR, PlayersTypes.PASSIVE);
        driver.getGame().getGraph().printGraph();
        while (!driver.getGame().gameEnded()) {
            driver.playAITurn();

            driver.getGame().getGraph().printGraph();

            driver.changeTurn();
            driver.initializeTurn(driver.getTurn());
            driver.playDeploymentTurn();
            driver.changeTurn();

            if (driver.getGame().gameEnded()) {
                break;
            }

            driver.initializeTurn(driver.getTurn());
            System.out.println("PASSIVE TURN");
            driver.getGame().getGraph().printGraph();
        }
    }

}
