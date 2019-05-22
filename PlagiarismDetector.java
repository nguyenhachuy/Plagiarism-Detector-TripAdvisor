import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class PlagiarismDetector {
	public static void main(String args[]) {
		String synonyms = args[0];
		Map<String, Integer> dict = new HashMap<>();
		int counter = 0;
		try (BufferedReader br = Files.newBufferedReader(Paths.get(synonyms))) {

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


		String file1 = args[1];
		List<String> list1 = new ArrayList<>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(file1))) {
			
			String line = "";
			while ( (line = br.readLine()) != null) {
				list1.addAll(Arrays.asList(line.toLowerCase().split(" ")));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String file2 = args[2];

		List<String> list2 = new ArrayList<>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(file2))) {
			
			String line = "";
			while ( (line = br.readLine()) != null) {
				list2.addAll(Arrays.asList(line.toLowerCase().split(" ")));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		int i = 0;
		int j = 2;
		int res = 0;
		int list1Tuples = 0;
		while (j < list1.size()) {
			list1Tuples++;
			int k = 0;
			int l = 2;
			while(l < list2.size()) {
				boolean sameTuple = compareTuple(dict, list1, list2, i,j,k,l);
				if(sameTuple) {
					res += 1;
				}
				k++;
				l++;
			}
			i++;
			j++;
		}

		System.out.println(res/list1Tuples);

		//Read both files

	}

	public static boolean compareTuple(Map<String, Integer> dict, List<String> list1, List<String> list2, int i, int j, int k, int l) {
		boolean match = true;
		for(int c = 0; c <= 2; c++) {
			match = match && (dict.get(list1.get(i + c)) == dict.get(list2.get(k + c)));
			if (!match) {
				return match;
			}
		}
		return match;
	}
}