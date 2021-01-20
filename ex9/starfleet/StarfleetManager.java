package Exercises.ex9.starfleet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

public class StarfleetManager {

    /**
     * Returns a list containing string representation of all fleet ships, sorted in descending order by
     * fire power, and then in descending order by commission year, and finally in ascending order by
     * name
     */
    public static List<String> getShipDescriptionsSortedByFirePowerAndCommissionYear(Collection<Spaceship> fleet) {
        Collections.sort((List<Spaceship>) fleet, new SpaceshipComparator());
        List<String> listOfFleetStrings = new ArrayList<>();
        for (Spaceship i : fleet) {
            listOfFleetStrings.add(i.toString());
        }
        return listOfFleetStrings;
    }

    /**
     * Returns a map containing ship type names as keys (the class name) and the number of instances created for each type as values
     */
    public static Map<String, Integer> getInstanceNumberPerClass(Collection<Spaceship> fleet) {
        Map<String, Integer> map = new HashMap<>();
        for (Spaceship i : fleet) {
            String className = i.getClass().getSimpleName();
            if (!map.containsKey(className)) {
                map.put(className, 1);
            } else {
                map.put(className, map.get(className) + 1);
            }
        }
        return map;

    }


    /**
     * Returns the total annual maintenance cost of the fleet (which is the sum of maintenance costs of all the fleet's ships)
     */
    public static int getTotalMaintenanceCost(Collection<Spaceship> fleet) {
        int sumMaintenanceCost = 0;
        for (Spaceship i : fleet) {
            sumMaintenanceCost += i.getAnnualMaintenanceCost();
        }
        return sumMaintenanceCost;

    }


    /**
     * Returns a set containing the names of all the fleet's weapons installed on any ship
     */
    public static Set<String> getFleetWeaponNames(Collection<Spaceship> fleet) {
        Set<String> fleetWeaponsName = new HashSet<>();
        for (Spaceship i : fleet) {
            if (i.getClass().getSimpleName().equals("Bomber")) {
                for (Weapon w : ((Bomber) i).getWeapon())
                    fleetWeaponsName.add(w.getName());
            }
            if (!i.getClass().getSimpleName().equals("TransportShip")) {
                if (i instanceof Fighter) {
                    for (Weapon w : ((Fighter) i).getWeapon())
                        fleetWeaponsName.add(w.getName());
                }
            }
        }
        return fleetWeaponsName;

    }

    /*
     * Returns the total number of crew-members serving on board of the given fleet's ships.
     */
    public static int getTotalNumberOfFleetCrewMembers(Collection<Spaceship> fleet) {
        int sumOfCrewMembers = 0;
        for (Spaceship i : fleet) {
            sumOfCrewMembers += i.getCrewMembers().size();
        }
        return sumOfCrewMembers;
    }

    /*
     * Returns the average age of all officers serving on board of the given fleet's ships.
     */
    public static float getAverageAgeOfFleetOfficers(Collection<Spaceship> fleet) {
        int sumOfficersAge = 0;
        float countOfficers = 0;
        for (Spaceship i : fleet) {
            for (CrewMember m : i.getCrewMembers()) {
                if (m.getClass().getSimpleName().equals("Officer")) {
                    countOfficers++;
                    sumOfficersAge += m.getAge();
                }
            }

        }
        return sumOfficersAge / countOfficers;
    }

    /*
     * Returns a map mapping the highest ranking officer on each ship (as keys), to his ship (as values).
     */
    public static Map<Officer, Spaceship> getHighestRankingOfficerPerShip(Collection<Spaceship> fleet) {
        Map<Officer, Spaceship> map = new HashMap<>();
        for (Spaceship i : fleet) {
            List<Officer> listOfOfficersInShip = new ArrayList<>();
            for (CrewMember m : i.getCrewMembers()) {
                if (m.getClass().getSimpleName().equals("Officer")) {
                    listOfOfficersInShip.add((Officer) m);
                }
            }
            listOfOfficersInShip.sort(new OfficersRankComparator());
            if (listOfOfficersInShip.size() != 0) {
                map.put(listOfOfficersInShip.get(0), i);
            }
        }
        return map;
    }

    /*
     * Returns a List of entries representing ranks and their occurrences.
     * Each entry represents a pair composed of an officer rank, and the number of its occurrences among starfleet personnel.
     * The returned list is sorted ascendingly based on the number of occurrences.
     */
    public static List<Map.Entry<OfficerRank, Integer>> getOfficerRanksSortedByPopularity(Collection<Spaceship> fleet) {
        Map<OfficerRank, Integer> map = new HashMap<>();
        for (Spaceship i : fleet) {
            for (CrewMember m : i.getCrewMembers()) {
                if (m.getClass().getSimpleName().equals("Officer")) {
                    OfficerRank rank = ((Officer) m).getRank();
                    if (!map.containsKey(rank)) {
                        map.put(rank, 1);
                    } else {
                        map.put(rank, map.get(rank) + 1);
                    }
                }
            }
        }
        List<Map.Entry<OfficerRank, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(new CrewMemberComparator());
        return list;
    }

}
