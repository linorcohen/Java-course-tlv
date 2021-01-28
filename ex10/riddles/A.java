package Exercises.ex10.riddles;


public class A implements Comparable<A> {
	
	protected int i;
	protected int j;

	public A(int i, int j) {
		this.i = i;
		this.j = j;
	}

	@Override
	public int hashCode() {
		return this.i;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		A that = (A) obj;
		return i == that.i;
	}

	@Override
	public int compareTo(A o) {
		return this.j - o.j;
	}


	public String toString() {return "("+this.i+" "+this.j+")";}

}
