import java.util.*; 

public interface PlagiarismStrategy {
    public float getPlagiarismScore(Map<String, Integer> dict, List<String> list1, List<String> list2, int tupleLength);
}