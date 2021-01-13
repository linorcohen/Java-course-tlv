package Exercises.ex7.software1.predicate;

import java.util.List;

public interface Product {

	double getPrice();

	void setPrice(double newPrice);

	String getName();

	default String getDescription() {
		return String.format("Name: %s%nPrice: %s%n", getName(), getPrice());
	}
	
	static <T extends Product> double getTotalPrice(List<T> products) { // Q1
		double sum = 0;
		for (T i : products) {
			sum += i.getPrice();
		}
		return sum;
	}

}
