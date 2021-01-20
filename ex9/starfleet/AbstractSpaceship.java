package Exercises.ex9.starfleet;

import java.util.Objects;
import java.util.Set;

public abstract class AbstractSpaceship implements Spaceship {

    private String name;
    private int commissionYear;
    private float maximalSpeed;
    private int firePower;
    private Set<? extends CrewMember> crewMembers;

    public AbstractSpaceship(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers) {
        this.name = name;
        this.commissionYear = commissionYear;
        this.maximalSpeed = maximalSpeed;
        this.firePower = 10;
        this.crewMembers = crewMembers;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCommissionYear() {
        return commissionYear;
    }

    @Override
    public float getMaximalSpeed() {
        return maximalSpeed;
    }

    @Override
    public int getFirePower() {
        return firePower;
    }

    @Override
    public Set<? extends CrewMember> getCrewMembers() {
        return crewMembers;
    }

    @Override
    public abstract int getAnnualMaintenanceCost();

    public String toString(){
        return "Name="+getName()+"\n\t" +
                "CommissionYear="+getCommissionYear()+"\n\t" +
                "MaximalSpeed="+getMaximalSpeed()+"\n\t";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractSpaceship that = (AbstractSpaceship) o;
        return commissionYear == that.commissionYear && Float.compare(that.maximalSpeed, maximalSpeed) == 0 && firePower == that.firePower && Objects.equals(name, that.name) && Objects.equals(crewMembers, that.crewMembers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, commissionYear, maximalSpeed, firePower, crewMembers);
    }
}
