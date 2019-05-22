import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class PlagiarismDetector {
	public static void main(String args[]) {

		if(args == null || args.length < 3 || args.length > 4) {
			System.out.println("Invalid number of arguments");
		}

		String synonyms = args[0];
		String file1 = args[1];
		String file2 = args[2];
		int tupleLength = args.length == 4 ? Integer.parseInt(args[3]) : 3;
		Map<String, Integer> dict = convertFileToDictionary(synonyms);
		List<String> list1 = convertFileToList(file1);
		List<String> list2 = convertFileToList(file2);

		float result = getPlagiarismScore(dict, list1, list2, tupleLength);
		String resultInPercent = convertToPercentage(result);
		System.out.println(resultInPercent);

		//Read both files

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

	public static List<String> convertFileToList(String filename) {
		List<String> list = new ArrayList<>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {
			
			String line = "";
			while ( (line = br.readLine()) != null) {
				list.addAll(Arrays.asList(line.toLowerCase().split(" ")));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Map<String, Integer> convertFileToDictionary(String filename) {
		Map<String, Integer> dict = new HashMap<>();
		int counter = 0;
		try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {

			//br returns as stream and convert it into a List
			String line = "";
			while ( (line = br.readLine()) != null) {
				String[] words = line.toLowerCase().split(" ");
				for (String w : words) {
					dict.put(w, counter);
				}
				counter++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return dict;
	}

	public static float getPlagiarismScore(Map<String, Integer> dict, List<String> list1, List<String> list2, int tupleLength) {
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

	public static String convertToPercentage(float score) {
		DecimalFormat df = new DecimalFormat("#%");
		return df.format(score);
	}
}