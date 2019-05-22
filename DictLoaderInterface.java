import java.util.Map;
import java.util.String;
import java.util.Integer;

public interface DictLoaderInterface<T,L> {
    Map<String, Integer> convertFileToDictionary(String filename);
}