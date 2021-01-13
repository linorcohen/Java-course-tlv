package Exercises.ex7.software1.predicate;

public class Discount implements Action<Book> {

	private final double percentage;

	public Discount(double percentage) { // Q3
		this.percentage = percentage;
	}
	
	
	@Override
	public void performAction(Book book) { // Q3
		double newPrice = (book.getPrice()*percentage)/100;
		book.setPrice(newPrice);
	}
	
}
