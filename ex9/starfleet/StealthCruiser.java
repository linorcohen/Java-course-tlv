package Exercises.ex9.starfleet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class StealthCruiser extends Fighter {

	static int numOfStealthCruiser = 0;

	private static final Weapon weapon = new Weapon("Laser Cannons",10,100);

	private static final List<Weapon> weapons = new ArrayList<Weapon>(Arrays.asList(new Weapon[]{weapon}));


	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons) {
		super(name,commissionYear,maximalSpeed,crewMembers,weapons);
		numOfStealthCruiser++;
	}

	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers){
		super(name,commissionYear,maximalSpeed,crewMembers,weapons);
		numOfStealthCruiser++;

	}

	public int getNumOfStealthCruiser() {
		return numOfStealthCruiser;
	}



	@Override
	public int getAnnualMaintenanceCost() {
		return super.getAnnualMaintenanceCost() + getNumOfStealthCruiser()*50;
	}

	@Override
	public String toString() {
		return "StealthCruiser\n\t"+
				"Name="+getName()+"\n\t" +
				"CommissionYear="+getCommissionYear()+"\n\t" +
				"MaximalSpeed="+getMaximalSpeed()+"\n\t"+
				"FirePower="+getFirePower()+"\n\t"+
				"CrewMembers="+getCrewMembers().size()+"\n\t"+
				"AnnualMaintenanceCost="+getAnnualMaintenanceCost()+"\n\t"+
				"WeaponArray="+getWeaponArray();
	}
}

