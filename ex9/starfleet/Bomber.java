package Exercises.ex9.starfleet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Bomber extends AbstractSpaceship {

	private List<Weapon> weapons;
	private final int numberOfTechnicians;
	private static final int BASIC_MAINTENANCE_COST = 5000;

	public Bomber(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons, int numberOfTechnicians){
		super(name, commissionYear, maximalSpeed, crewMembers);
		this.weapons = weapons;
		this.numberOfTechnicians = numberOfTechnicians;

	}

	public List<Weapon> getWeapon() {
		return weapons;
	}

	@Override
	public int getFirePower() {
		int firePowerSum = super.getFirePower();
		for (Weapon i: getWeapon()) {
			firePowerSum += i.getFirePower();
		}
		return firePowerSum;
	}

	public int getNumberOfTechnicians() {
		return numberOfTechnicians;
	}

	public List<String> getWeaponArray(){
		List<String> arr = new ArrayList<>();
		for (Weapon i : getWeapon()){
			arr.add(i.toString());
		}
		return arr;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		int maintenanceSum = 0;
		for (Weapon i: getWeapon()) {
			maintenanceSum += i.getAnnualMaintenanceCost();
		}
		return (maintenanceSum*(100 - getNumberOfTechnicians()*10))/100 + BASIC_MAINTENANCE_COST;
	}

	@Override
	public String toString() {
		return "Bomber\n\t"+
				super.toString()+
				"FirePower="+getFirePower()+"\n\t"+
				"CrewMembers="+getCrewMembers().size()+"\n\t"+
				"AnnualMaintenanceCost="+getAnnualMaintenanceCost()+"\n\t"+
				"WeaponArray="+getWeaponArray() +"\n\t"+
				"NumberOfTechnicians="+getNumberOfTechnicians();
	}

}
