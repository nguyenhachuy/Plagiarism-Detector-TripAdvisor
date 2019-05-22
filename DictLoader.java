import java.util.*;

public interface DictLoader<String, Integer> {
    Map<String, Integer> convertFileToDictionary(String filename);
}