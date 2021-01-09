package Exercises.ex6.partsA_C;

import java.util.LinkedList;

/**
 *  @inv !isEmpty() implies top() != null   //  no null objects are allowed
 */
public class SectionA {
	
	 private final LinkedList<Object> elements = new LinkedList<Object>();
	
	    /**
	     *  @post !isEmpty()
	     *  @post top() == o
	     */
	 public void push(Object o)
	    {
	        elements.add(o);
	    }
//	    match.

	    /**
	     *  @pre !isEmpty()
	     *  @post @return == top()@pre
	     */
	  public Object pop()
	    {
	        final Object popped = top();
	        elements.removeLast();
	        return popped;
	    }
//	    match.

	    /**
	     *  @pre !isEmpty()
	     *  @post @return != null
	     */
	  public Object top()
	    {
	        return elements.getLast();
	    }
//	   match.

	  /**
	     *  
	     *  @post @return == true if elements.size() > 0
	     */
	    public boolean isEmpty()
	    {
	        return elements.size() == 0;
	    }
//	    match.

	public static void main(String[] args) {
		LinkedList<Integer> ps = new LinkedList<Integer>();
		ps.push(5);
		ps.push(5);
		System.out.println(ps);
		ps.pop();
		System.out.println(ps);

	}
}
