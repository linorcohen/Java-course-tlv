package Exercises.ex9.starfleet;

import java.util.Set;

public class TransportShip extends AbstractSpaceship{


	private int cargoCapacity;
	private int passengerCapacity;
	private static final  int BASIC_MAINTENANCE_COST = 3000;
	private static final  int COST_PER_CARGO_TON = 5;
	private static final  int COST_PER_PASSENGER = 3;


	public TransportShip(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, int cargoCapacity, int passengerCapacity){
		super(name, commissionYear, maximalSpeed, crewMembers);
		this.cargoCapacity = cargoCapacity;
		this.passengerCapacity = passengerCapacity;
	}

	public int getCargoCapacity() {
		return cargoCapacity;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return BASIC_MAINTENANCE_COST+COST_PER_CARGO_TON*getCargoCapacity()+COST_PER_PASSENGER*getPassengerCapacity();
	}

	@Override
	public String toString() {
		return "TransportShip\n\t"+
				super.toString()+
				"FirePower="+getFirePower()+"\n\t"+
				"CrewMembers="+getCrewMembers().size()+"\n\t"+
				"AnnualMaintenanceCost="+getAnnualMaintenanceCost()+"\n\t"+
				"CargoCapacity="+getCargoCapacity()+"\n\t"+
				"PassengerCapacity="+getPassengerCapacity();
	}
}
