import java.lang.Integer;
import java.util.List;


public abstract class Operations {
    public abstract List<Integer> execute(List<Integer> stack);
}

class PlusOperation extends Operations {
    @Override
    public List<Integer> execute(List<Integer> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("Addition requires that the stack contain at least 2 values");
        }
        int b = stack.remove(stack.size() - 1);
        int a = stack.remove(stack.size() - 1);
        stack.add(a + b);
        return stack;
    }
}

class MinusOperation extends Operations {
    @Override
    public List<Integer> execute(List<Integer> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("Subtraction requires that the stack contain at least 2 values");
        }
        int b = stack.remove(stack.size() - 1);
        int a = stack.remove(stack.size() - 1);
        stack.add(a - b);
        return stack;
    }
}

class MultiplyOperation extends Operations {
    @Override
    public List<Integer> execute(List<Integer> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("Multiplication requires that the stack contain at least 2 values");
        }
        int b = stack.remove(stack.size() - 1);
        int a = stack.remove(stack.size() - 1);
        stack.add(a * b);
        return stack;
    }
}

class DivideOperation extends Operations {
    @Override
    public List<Integer> execute(List<Integer> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("Division requires that the stack contain at least 2 values");
        }
        int b = stack.remove(stack.size() - 1);
        int a = stack.remove(stack.size() - 1);
        if (b == 0) {
            throw new IllegalArgumentException("Division by 0 is not allowed");
        }
        stack.add(a / b);
        return stack;
    }
}

class DupOperation extends Operations {
    @Override
    public List<Integer> execute(List<Integer> stack) {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Duplicating requires that the stack contain at least 1 value");
        }
        int a = stack.get(stack.size() - 1);
        stack.add(a);
        return stack;
    }
}

class DropOperation extends Operations {
    @Override
    public List<Integer> execute(List<Integer> stack) {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Dropping requires that the stack contain at least 1 value");
        }
        stack.remove(stack.size() - 1);
        return stack;
    }
}

class SwapOperation extends Operations {
    @Override
    public List<Integer> execute(List<Integer> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("Swapping requires that the stack contain at least 2 values");
        }
        int b = stack.remove(stack.size() - 1);
        int a = stack.remove(stack.size() - 1);
        stack.add(b);
        stack.add(a);
        return stack;
    }
}

class OverOperation extends Operations {
    @Override
    public List<Integer> execute(List<Integer> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("Overing requires that the stack contain at least 2 values");
        }
        int b = stack.remove(stack.size() - 1);
        int a = stack.remove(stack.size() - 1);
        stack.add(a);
        stack.add(b);
        stack.add(a);
        return stack;
    }
}

class FactoryOperation {
    public static Operations getOperation(String token) {
        switch (token) {
            case "+":
                return new PlusOperation();
            case "-":
                return new MinusOperation();
            case "*":
                return new MultiplyOperation();
            case "/":
                return new DivideOperation();
            case "dup":
                return new DupOperation();
            case "drop":
                return new DropOperation();
            case "swap":
                return new SwapOperation();
            case "over":
                return new OverOperation();
            default:
                throw new IllegalArgumentException("No definition available for operator \"" + token + "\"");
        }
    }
}
