import java.lang.ArithmeticException;

class CalculatorConundrum {
    public String calculate(int operand1, int operand2, String operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Operation cannot be null");
        }
        if (operation.isEmpty()) {
            throw new IllegalArgumentException("Operation cannot be empty");
        }
        switch (operation) {
            case "+":
                return String.format("%d + %d = %d", operand1, operand2, operand1 + operand2);
            case "*":
                return String.format("%d * %d = %d", operand1, operand2, operand1 * operand2);                
            case "/":
                if (operand2 == 0) {
                    throw new IllegalOperationException("Division by zero is not allowed", new ArithmeticException());
                }
                return String.format("%d / %d = %d", operand1, operand2, operand1 / operand2);
            default:
                throw new IllegalOperationException(String.format("Operation '%s' does not exist", operation));
        }
    }
}
