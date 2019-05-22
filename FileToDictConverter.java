import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileToDictConverter implements DictLoaderInterface<String, Integer> {
	public Map<String, Integer> convertFileToDictionary(String filename) {
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
}