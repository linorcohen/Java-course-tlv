package Exercises.ex9.starfleet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Fighter extends AbstractSpaceship {

	private final List<Weapon> weapons;
	protected static final int BASIC_MAINTENANCE_COST = 2500;
	protected static final int ENGINE_MAINTENANCE_COST = 1000;


	public Fighter(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers, List<Weapon> weapons){
		super(name,commissionYear,maximalSpeed,crewMembers);
		this.weapons = weapons;
	}

	public List<Weapon> getWeapon() {
		return weapons;
	}

	public List<String> getWeaponArray(){
		List<String> arr = new ArrayList<>();
		for (Weapon i : getWeapon()){
			arr.add(i.toString());
		}
		return arr;
	}

	@Override
	public int getFirePower() {
		int firePowerSum = super.getFirePower();
		for (Weapon i: getWeapon()) {
			firePowerSum += i.getFirePower();
		}
		return firePowerSum;
	}

	public int getWeaponsMaintenance(){
		int maintenanceSum =0;
		for (Weapon i: this.getWeapon()) {
			maintenanceSum += i.getAnnualMaintenanceCost();
		}
		return maintenanceSum;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return getWeaponsMaintenance() + BASIC_MAINTENANCE_COST+ (int) Math.ceil(getMaximalSpeed()*ENGINE_MAINTENANCE_COST);
	}

	@Override
	public String toString() {
		return "Fighter\n\t"+
				super.toString()+
				"FirePower="+getFirePower()+"\n\t"+
				"CrewMembers="+getCrewMembers().size()+"\n\t"+
				"AnnualMaintenanceCost="+getAnnualMaintenanceCost()+"\n\t"+
				"WeaponArray="+getWeaponArray();
	}
}
