package Exercises.ex9.starfleet;

public class Cylon extends AbstractCrewMember {

	private final int modelNumber;

	public Cylon(String name, int age, int yearsInService, int modelNumber) {
		super(name, age,yearsInService);
		this.modelNumber = modelNumber;

	}
	
	public int getModelNumber(){
		return modelNumber;
	}

	@Override
	public String toString() {
		return "Cylon\n\t" +
				super.toString() +
				"\n\tModelNumber="+getModelNumber();
	}

}

