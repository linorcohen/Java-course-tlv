package Exercises.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class ColonialViper extends Fighter  {

	private static final int BASIC_MAINTENANCE_COST = 4000;
	private static final int ENGINE_MAINTENANCE_COST = 500;
	private static final int BASIC_CREW_MEMBER_MAINTENANCE_COST =500;

	public ColonialViper(String name, int commissionYear, float maximalSpeed, Set<CrewWoman> crewMembers,
			List<Weapon> weapons) {
		super(name,commissionYear,maximalSpeed,crewMembers,weapons);

	}

	@Override
	public int getAnnualMaintenanceCost() {
		return getWeaponsMaintenance() + BASIC_MAINTENANCE_COST+ (int) Math.ceil(getMaximalSpeed()*ENGINE_MAINTENANCE_COST) + getCrewMembers().size()*BASIC_CREW_MEMBER_MAINTENANCE_COST;
	}

	@Override
	public String toString() {
		return "ColonialViper\n\t"+
				"Name="+getName()+"\n\t" +
				"CommissionYear="+getCommissionYear()+"\n\t" +
				"MaximalSpeed="+getMaximalSpeed()+"\n\t"+
				"FirePower="+getFirePower()+"\n\t"+
				"CrewMembers="+getCrewMembers().size()+"\n\t"+
				"AnnualMaintenanceCost="+getAnnualMaintenanceCost()+"\n\t"+
				"WeaponArray="+getWeaponArray();
	}
}
