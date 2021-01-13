package Exercises.ex7.software1.predicate;

import java.util.List;

public class Store<T extends Product> {
	private List<T> inventory;
	
	public Store(List <T> inventory) {
		this.inventory = inventory;
	}

	public List<T> getInventory() {
		return inventory;
	}

	public String getInventoryDescription() { // Q4
		String description = "";
		for (T i: getInventory() ) {
			description += i.getDescription();
		}
		return description;
	}

	public void transform(Predicate<T> pred, Action<T> action) { // Q5
		for (T i: inventory ) {
			if (pred.test(i)){
				action.performAction(i);
			}
		}
	}
}
