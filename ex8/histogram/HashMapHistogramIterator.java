package Exercises.ex8.histogram;

import java.util.*;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogramIterator<T extends Comparable<T>> implements Iterator<T>{

	private int index;
	private final List<Map.Entry<T, Integer>> entryList;

	public HashMapHistogramIterator(HashMap<T,Integer> mapForIter){
		this.entryList = new ArrayList<>(mapForIter.entrySet());
		this.entryList.sort(new SortBySize());
		this.index = -1;
	}

	
	@Override
	public boolean hasNext() {
		int nextIndex =index+1;
		return (entryList.size() > nextIndex);

	}

	@Override
	public T next() {
		index++;
		return entryList.get(index).getKey();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException(); //no need to change this
	}

	public class SortBySize implements Comparator<Map.Entry<T, Integer>> {
		@Override
		public int compare(Map.Entry<T, Integer> o1, Map.Entry<T, Integer> o2) {
			int result = -o1.getValue().compareTo(o2.getValue());
			if (result == 0) {
				result = o1.getKey().compareTo(o2.getKey());
			}
			return result;
		}
	}

}
