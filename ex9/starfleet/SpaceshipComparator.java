package Exercises.ex9.starfleet;

import java.util.Comparator;

public class SpaceshipComparator implements Comparator<Spaceship> {

    @Override
    public int compare(Spaceship o1, Spaceship o2) {
        int result = -Integer.compare(o1.getFirePower(), o2.getFirePower());
        if (result ==0) {
            result = -Integer.compare(o1.getCommissionYear(), o2.getCommissionYear());
            if (result == 0) {
                result = CharSequence.compare(o1.getName(), o2.getName());
            }
        }
        return result;
    }
}
