package Exercises.ex9.starfleet;

public class Officer extends CrewWoman {

	private final OfficerRank rank;

	public Officer(String name, int age, int yearsInService, OfficerRank rank) {
		super(age,yearsInService,name);
		this.rank = rank;
	}

	public OfficerRank getRank(){
		return rank;
	}

	@Override
	public String toString() {
		return "Officer\n\t" +
				super.toString() +
				"\n\tOfficerRank="+getRank();
	}
}
