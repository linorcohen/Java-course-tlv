package Exercises.ex9.starfleet;

public class CrewWoman extends AbstractCrewMember {


	public CrewWoman(int age, int yearsInService, String name) {
		super(name,age, yearsInService);
	}

	@Override
	public String toString() {
		return "CrewWoman\n\t" +
				super.toString();
	}
}

