package Exercises.ex8.wordsRank;

import java.util.Comparator;

import Exercises.ex8.wordsRank.RankedWord.rankType;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

class RankedWordComparator implements Comparator<RankedWord>{

	private final rankType cType;

	public RankedWordComparator(rankType cType) {
		this.cType = cType;
	}
	
	@Override
	public int compare(RankedWord o1, RankedWord o2) {
		int result = 0;
		if (cType == rankType.average) {
			result = o1.getRankByType(cType) - o2.getRankByType(cType);
		}
		if (cType == rankType.min) {
			result = o1.getRankByType(cType) - o2.getRankByType(cType);
		}
		if (cType == rankType.max){
			result = o1.getRankByType(cType) - o2.getRankByType(cType);
		}
		return result;
	}
}
