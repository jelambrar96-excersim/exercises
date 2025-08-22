import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Etl {
    Map<String, Integer> transform(Map<Integer, List<String>> old) {
        Map<String, Integer> outputMap = new HashMap<String, Integer>();
        for (Integer i: old.keySet()) {
            for (String s: old.get(i)) {
                outputMap.put(s.toLowerCase(), i);
            }
        }
        return outputMap;
    }
}
