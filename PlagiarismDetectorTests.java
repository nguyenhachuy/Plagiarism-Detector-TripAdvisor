import java.util.*;

public class PlagiarismDetectorTests {

    private PlagiarismStrategy scorer;
    Map<String, Integer> dict;
    int tupleLength = 0;
    List<String> list1;
    List<String> list2;

    public PlagiarismDetectorTests() {
        //Do init
        scorer = new PlagiarismScorer();
        dict = new HashMap<String, Integer>();
        tupleLength = 3;
        dict.put("run", 0);
        dict.put("spring", 0);
        dict.put("jog", 0);
        list1 = Arrays.asList("go for a run".split(" "));
        list2 = Arrays.asList("go for a jog".split(" "));
    }

    public void test1() {
        float score = scorer.getPlagiarismScore(dict, list1, list2, tupleLength);
        System.out.println("Exepct score to be 1");
        System.out.println("Result: " + (score == 1));
    }

    public void test2() {
        list1 = Arrays.asList("go for a run".split(" "));
        list2 = Arrays.asList("went for a jog".split(" "));
        float score = scorer.getPlagiarismScore(dict, list1, list2, tupleLength);
        System.out.println("Exepct score to be 0.5");
        System.out.println("Result: " + (score == 0.5));
    }
    
    public void runTests() {
        test1();
        test2();
    }
}