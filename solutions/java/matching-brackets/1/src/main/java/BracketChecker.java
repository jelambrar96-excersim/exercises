import java.util.LinkedList;


class BracketChecker {

    String expression;

    BracketChecker(String expression) {
        this.expression = expression;
    }

    boolean areBracketsMatchedAndNestedCorrectly() {

        LinkedList<Character> stack = new LinkedList<>();
        for (char ch : expression.toCharArray()) {
            if (ch == '{' || ch == '[' || ch == '(') {
                stack.push(ch);
            } else if (ch == '}' || ch == ']' || ch == ')') {
                if (stack.isEmpty()) {
                    return false; // Unmatched closing bracket
                }
                char last = stack.pop();
                if (!isMatchingPair(last, ch)) {
                    return false; // Mismatched brackets
                }
            }
        }
        return stack.isEmpty(); // All brackets matched

    }

    private boolean isMatchingPair(char opening, char closing) {
        return (opening == '{' && closing == '}') ||
               (opening == '[' && closing == ']') ||
               (opening == '(' && closing == ')');
    }

}
