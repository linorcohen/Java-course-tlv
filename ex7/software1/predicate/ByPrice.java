package Exercises.ex7.software1.predicate;

public class ByPrice implements Predicate<SmartPhone>{

	private final double maxPrice;

	public ByPrice(double maxPrice) { // Q2
		this.maxPrice = maxPrice;
	}

	@Override
	public boolean test(SmartPhone phone) { // Q2
		return phone.getPrice() <= maxPrice;
	}
	
	

}
