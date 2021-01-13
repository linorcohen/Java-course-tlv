package Exercises.ex7.software1.predicate;

public interface Action<T extends Product> {
	void performAction(T product);
}
