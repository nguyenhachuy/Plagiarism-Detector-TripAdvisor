import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class FileToListConverter implements TextLoader {
    public List<String> convertFileToList(String filename) {
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
}