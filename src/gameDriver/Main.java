package gameDriver;

public class Main {

	public static void main(String[] args) {
		GameDriver driver = new GameDriver(PlayersTypes.PASSIVE,PlayersTypes.PASSIVE); 
		driver.playTurn();
	}

}
