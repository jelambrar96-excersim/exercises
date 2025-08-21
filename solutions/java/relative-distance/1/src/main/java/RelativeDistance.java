import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RelativeDistance {

    private final Map<String, List<String>> familyTree;
    private Map<String, Map<String, Integer>> distanceCache;

    RelativeDistance(Map<String, List<String>>  familyTree) {
        this.familyTree = generateFullFamilyTree(familyTree);
        this.distanceCache = new java.util.HashMap<String, Map<String, Integer>>();
    }

    private Map<String, List<String>> generateFullFamilyTree(Map<String, List<String>> familyTree) {
        Map<String, List<String>> fullFamilyTree = new HashMap<String, List<String>>();
        // Add all relationships from the original family tree
        for (String person : familyTree.keySet()) {
            fullFamilyTree.putIfAbsent(person, new ArrayList<>());
            fullFamilyTree.get(person).addAll(familyTree.get(person));
        }
        // Make relationships bidirectional chid <-> parent
        for (String person : familyTree.keySet()) {
            List<String> relatives = familyTree.get(person);
            for (String relative : relatives) {
                fullFamilyTree.putIfAbsent(relative, new ArrayList<>());
                if (!fullFamilyTree.get(relative).contains(person)) {
                    fullFamilyTree.get(relative).add(person);
                }
            }
        }
        //  make relationships bidirectional sibling <-> sibling
        for (String person : familyTree.keySet()) {
            List<String> relatives = familyTree.get(person);
            for (String relative : relatives) {
                for (String sibling : relatives) {
                    if (sibling.equals(relative)) { continue; }
                    fullFamilyTree.putIfAbsent(sibling, new ArrayList<>());
                    // Avoid adding a sibling to their own list or to a 
                    // relative's list if already present
                    if (!fullFamilyTree.get(relative).contains(sibling)) {
                        fullFamilyTree.get(relative).add(sibling);
                    }
                }
            } 
        }
        return fullFamilyTree;
    }

    int degreeOfSeparation(String personA, String personB) {
        return degreeOfSeparation(personA, personB, new ArrayList<String>());
    }

    private int degreeOfSeparation(String personA, String personB, List<String> visited) {
        if (personA.equals(personB)) { return 0; }

        // cache lookup
        if (distanceCache.containsKey(personA)) {
            Map<String, Integer> cachedDistances = distanceCache.get(personA);
            if (cachedDistances.containsKey(personB)) {
                return cachedDistances.get(personB);
            }
        }
        else {
            distanceCache.putIfAbsent(personA, new HashMap<>()); 
        }
        if (distanceCache.containsKey(personB)) {
            Map<String, Integer> cachedDistances = distanceCache.get(personB);
            if (cachedDistances.containsKey(personA)) {
                return cachedDistances.get(personA);
            }
        }
        else {
            distanceCache.putIfAbsent(personB, new HashMap<>());
        }

        List<String> relatives = familyTree.get(personA);
        
        if (relatives == null || relatives.isEmpty()) {
            distanceCache.get(personA).put(personB, -1);
            return -1;
        }

        int minDistance = -1; // family.size() + 1;
        boolean found = false;
        for (String relative : relatives) {
            if (visited.contains(relative)) { continue; }
                        
            List<String> newVisited = new ArrayList<>(visited);
            newVisited.add(relative);
            
            int distance = degreeOfSeparation(relative, personB, newVisited);
            if (distance == -1) { continue; }
            distance += 1; // account for the step to the relative
            if (!found || distance < minDistance) {
                minDistance = distance;
                found = true;
            }
        }
        
        // Store in cache (both directions)
        distanceCache.get(personA).put(personB, minDistance);
        distanceCache.get(personB).put(personA, minDistance);
        // If no relatives found, return -1
        return minDistance;
    }
}
