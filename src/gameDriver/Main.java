package gameDriver;

import utilities.ArrayListUtilities;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
//        GameDriver driver = new GameDriver(PlayersTypes.PASSIVE, PlayersTypes.AGGRESSIVE);
//		while (!driver.playTurn().gameEnded());
//        driver.playTurn();
//        driver.playTurn();
//        driver.playTurn();
//        driver.playTurn();
//        driver.playTurn();
        ArrayList<Point> arrayList = new ArrayList<Point>();
        arrayList.add(new Point(10, 4));
        arrayList.add(new Point(13, 4));
        arrayList.add(new Point(2, 7));
        arrayList.add(new Point(9, 9));
        ArrayListUtilities.sortPointsAL(arrayList);
        for (Point p : arrayList) {
            System.out.println(p);
        }
    }

}
