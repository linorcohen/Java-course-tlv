package Exercises.ex6.riddle.a;

/**
 * Do not change this code!
 */
public class B {
	private int i;

	public B(int i) {
		this.i = i;
		System.out.println("B");
	}

	public int getI() {
		return i;
	}
	
	public static void main(String[] args) {
		int i = Integer.parseInt(args[0]);
		B b = new B(i);
		A a = new A(b);
		a.printA();
	}
}
