package Exercises.ex10.riddles;

public class C extends B {
	
	private int i;
	private int j;

	public C(int i, int j) {
		super(i,j);
		
	}

	@Override
	public int compareTo(A other) {
		int sum = this.i - other.i;
		if (sum == 0) {
			if (this.j - other.j == 0) {
				return 0;
			}
		}
		return sum;
	}
}