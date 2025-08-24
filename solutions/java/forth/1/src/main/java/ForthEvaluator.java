import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

class ForthEvaluator {

    List<Integer> evaluateProgram(List<String> input) {
        
        List<Integer> stack = new java.util.ArrayList<>();
        Map<String, List<String>> customOperations = new HashMap<>();
        
        for (String line : input) {
            String[] tokens = line.toLowerCase().split(" ");
            int numTokens = tokens.length;

            // // custom operations
            if (tokens[0].equals(":")) {
                if (tokens.length < 3) {
                    throw new IllegalArgumentException("Invalid definition");
                }
                if (!tokens[numTokens - 1].equals(";")) {
                    throw new IllegalArgumentException("Invalid definition");
                }
                String operationName = tokens[1];
                if (isNumber(operationName)) {
                    throw new IllegalArgumentException("Cannot redefine numbers");
                }
                customOperations.put(operationName, 
                    translate(customOperations, List.of(tokens).subList(2, numTokens - 1)));
                continue;
            }

            tokens = translate(customOperations, List.of(tokens)).toArray(new String[0]);
            for (String token : tokens) {
                if (isNumber(token)) {
                    stack.add(Integer.parseInt(token));
                }
                else {
                    Operations operation = FactoryOperation.getOperation(token);
                    if (operation == null) {
                        throw new IllegalArgumentException("Unknown operation: " + token);
                    }
                    stack = operation.execute(stack);
                } 
            }
        }

        return stack;
    }

    private boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private List<String> translate(Map<String, List<String>>customOperations, List<String> tokens) {
        List<String> result = new ArrayList<>();
        for (String token : tokens) {
            if (customOperations.containsKey(token)) {
                result.addAll(customOperations.get(token));
            } else {
                result.add(token);
            }
        }
        return result;
    }

}
