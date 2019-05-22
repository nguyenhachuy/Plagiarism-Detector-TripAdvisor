import java.util.*;

public class PlagiarismScorer implements PlagiarismStrategy {
    public float getPlagiarismScore(Map<String, Integer> dict, List<String> list1, List<String> list2, int tupleLength) {
		//i is starting index of list1's tuple, j is the ending index
		int i = 0;
		int j = i + tupleLength - 1;
				
		//k is starting index of list2's tuple, l is the ending index

		int score = 0;
		int numberOfTuples = 0;
		int list1Size = list1.size();
		int list2Size = list2.size();
		while (j < list1Size) {
			numberOfTuples++;
			int k = 0;
			int l = k + tupleLength - 1;	
			while(l < list2Size) {
				boolean sameTuple = compareTuple(dict, list1, list2, i, k, tupleLength);
				if(sameTuple) {
					score += 1;
				}
				k++;
				l++;
			}
			i++;
			j++;
		}

		return (float) score/numberOfTuples;
    }

    public static boolean compareTuple(Map<String, Integer> dict, List<String> list1, List<String> list2, int index1, int index2, int tupleLength) {
		for(int c = 0; c < tupleLength; c++) {
			String word1 = list1.get(index1+ c);
			String word2 = list2.get(index2+ c);
			boolean match = true;
			if(dict.containsKey(word1)) {
				match = match &&  dict.get(list1.get(index1 + c)) == dict.get(list2.get(index2 + c));
			}
			else {
				match = match && word1.equals(word2);
			}

			if (!match) {
				return false;
			}
		}
		return true;
	}

}