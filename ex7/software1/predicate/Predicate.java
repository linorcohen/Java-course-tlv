package Exercises.ex7.software1.predicate;

public interface Predicate<T extends Product> {
	boolean test(T product);
}
