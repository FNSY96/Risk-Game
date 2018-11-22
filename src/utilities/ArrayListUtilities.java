package utilities;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ArrayListUtilities {

    public static ArrayList<Integer> convertPointALToIntegerAL(ArrayList<Point> arrayList) {
        ArrayList<Integer> converted = new ArrayList<>();

        for (Point p : arrayList) {
            converted.add(p.x);
        }

        return converted;
    }

    public static ArrayList<Point> mergeIntegerAL(ArrayList<Integer> al1, ArrayList<Integer> al2) {
        if (al1.size() != al2.size())
            throw new RuntimeException("ARRAYLISTS SIZE MISMATCH");

        ArrayList<Point> merged = new ArrayList<>();

        for (int i = 0; i < al2.size(); i++) {
            merged.add(new Point(al1.get(i), al2.get(i)));
        }
        return merged;
    }


    public static void sortPointsAL(ArrayList<Point> arrayList) {
        Collections.sort(arrayList, new PointCompare());
    }


}

class PointCompare implements Comparator<Point> {
    public int compare(Point a, Point b) {
        if (a.y < b.y) {
            return -1;
        } else if (a.y > b.y) {
            return 1;
        } else {
            return 0;
        }
    }
}