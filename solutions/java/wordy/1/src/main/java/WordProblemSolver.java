import java.lang.String;
import java.util.LinkedList;
import java.util.Arrays;


class WordProblemSolver {

    final String ERROR_MESSAGE = "I'm sorry, I don't understand the question!";

    int solve(final String wordProblem) {
        String cleaned = wordProblem
                .replace("What is ", "")
                .replace("by ", "")
                .replace("?", "")
                .trim();
        if (cleaned.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        LinkedList<String> tokens = new LinkedList<String>();
        tokens.addAll(Arrays.asList(cleaned.split(" ")));

        String currentitem = tokens.removeFirst().trim();
        if (!isNumber(currentitem)) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        int currentResult = Integer.parseInt(currentitem);
        
        while (!tokens.isEmpty()) {
            String operator = tokens.removeFirst().trim();
            if (tokens.isEmpty()) {
                throw new IllegalArgumentException(ERROR_MESSAGE);
            }
            String nextitem = tokens.removeFirst().trim();
            if (!isNumber(nextitem)) {
                throw new IllegalArgumentException(ERROR_MESSAGE);
            }
            int nextNumber = Integer.parseInt(nextitem);
            currentResult = operate(currentResult, nextNumber, operator);
        }
        return currentResult;
    }


    private int operate(int a, int b, String operator) {
        switch (operator) {
            case "plus":
                return a + b;
            case "minus":
                return a - b;
            case "multiplied":
                return a * b;
            case "divided":
                return a / b;
            default:
                throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }


    private boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
