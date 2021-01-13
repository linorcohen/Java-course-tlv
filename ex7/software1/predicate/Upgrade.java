package Exercises.ex7.software1.predicate;

public class Upgrade implements Action<SmartPhone> {

	@Override
	public void performAction(SmartPhone phone) { // Q3
		phone.upgrade();
	}

	
}
