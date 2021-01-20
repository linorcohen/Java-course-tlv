package Exercises.ex9.starfleet;


import java.util.Comparator;
import java.util.Map;

public class CrewMemberComparator implements Comparator<Map.Entry<OfficerRank, Integer>> {

    @Override
    public int compare(Map.Entry<OfficerRank, Integer> o1, Map.Entry<OfficerRank, Integer> o2) {
        int result = o1.getValue().compareTo(o2.getValue());
        if (o1.getValue().compareTo(o2.getValue()) == 0){
            result = o1.getKey().compareTo(o2.getKey());
        }
        return result;
    }
}
