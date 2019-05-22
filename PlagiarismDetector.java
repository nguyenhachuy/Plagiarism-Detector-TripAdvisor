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

		DictLoader dictConverter = new FileToDictConverter();
		TextLoader listConverter = new FileToListConverter();

		Map<String, Integer> dict = dictConverter.convertFileToDictionary(synonyms);
		List<String> list1 = listConverter.convertFileToList(file1);
		List<String> list2 = listConverter.convertFileToList(file2);
		PlagiarismStrategy scorer = new PlagiarismScorer();

		float result = scorer.getPlagiarismScore(dict, list1, list2, tupleLength);
		String resultInPercent = convertToPercentage(result);
		System.out.println(resultInPercent);


	}

	//Helper method to format string
	public static String convertToPercentage(float score) {
		if(score < 0) {
			return "Plagiarism Algorithm has failed";
		}
		DecimalFormat df = new DecimalFormat("#%");
		return df.format(score);
	}
}