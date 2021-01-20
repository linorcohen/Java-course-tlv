package Exercises.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class CylonRaider extends Fighter {

	private static final int BASIC_MAINTENANCE_COST = 3500;
	private static final int ENGINE_MAINTENANCE_COST = 1200;
	private static final int BASIC_CREW_MEMBER_MAINTENANCE_COST =500;

	public CylonRaider(String name, int commissionYear, float maximalSpeed, Set<Cylon> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers,weapons);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return BASIC_MAINTENANCE_COST + getWeaponsMaintenance() + BASIC_CREW_MEMBER_MAINTENANCE_COST*(getCrewMembers().size()) + (int) Math.ceil(getMaximalSpeed()*ENGINE_MAINTENANCE_COST);
	}


	@Override
	public String toString() {
		return "CylonRaider\n\t"+
				"Name="+getName()+"\n\t" +
				"CommissionYear="+getCommissionYear()+"\n\t" +
				"MaximalSpeed="+getMaximalSpeed()+"\n\t"+
				"FirePower="+getFirePower()+"\n\t"+
				"CrewMembers="+getCrewMembers().size()+"\n\t"+
				"AnnualMaintenanceCost="+getAnnualMaintenanceCost()+"\n\t"+
				"WeaponArray="+getWeaponArray();
	}
}
