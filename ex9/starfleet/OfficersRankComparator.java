package Exercises.ex9.starfleet;

import java.util.Comparator;

public class OfficersRankComparator implements Comparator<Officer> {


    @Override
    public int compare(Officer o1, Officer o2) {
        return -o1.getRank().compareTo(o2.getRank());
    }
}
