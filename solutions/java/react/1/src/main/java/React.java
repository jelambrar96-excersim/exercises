import java.util.List;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.Function;

public class React {

    public static class Cell<T> {
        
        protected T value= null;
        protected T prevValue = null;
        protected HashSet<Cell<T>> listeners = new HashSet<Cell<T>>();
        
        public T getValue() { return this.value; }
        
        public void addListener(Cell<T> cell) { this.listeners.add(cell); }
        public void removeListener(Cell<T> cell) { this.listeners.remove(cell); }
        
        protected void update() {
            if (this.value == this.prevValue ) { return; }
            for (Cell<T> c: this.listeners) { c.update(); }
        }
    }

    public static class InputCell<T> extends Cell<T> {
        
        public void setValue(T newValue) {
            this.prevValue = this.value;
            this.value = newValue;
            if (this.prevValue != this.value) {
                update();
            }
        }
    }

    public static class ComputeCell<T> extends Cell<T> {
        
        private Function<List<T>, T> function;
        private LinkedList<Cell<T>> inputs;
        private LinkedList<Consumer<T>> callbacks;
        
        public ComputeCell(Function<List<T>, T> function, List<Cell<T>> inputs) {
            this.callbacks = new LinkedList<Consumer<T>>();
            this.function = function;
            this.inputs = new LinkedList<>();
            for (Cell<T> c: inputs) {
                c.addListener(this);
                this.inputs.add(c);
            }
            compute();
        }
        
        public void addCallback(Consumer<T> callback) { callbacks.add(callback); }
        public void removeCallback(Consumer<T> callback) { callbacks.remove(callback); }

        void compute() {
            List<T> list_t = this.inputs.stream().map(c -> c.getValue()).toList();
            T outfunction = function.apply(list_t);
            this.prevValue = value;
            this.value = outfunction;
        }

        void runCallback() {
            for (Consumer<T> cb: callbacks) {
                cb.accept(this.value);
            }
        }

        @Override
        public void update() {
            compute();
            if (prevValue == value) { return; }
            runCallback();
            super.update();
        }

        @Override
        public T getValue() { update(); return this.value; }
    }

    public static <T> InputCell<T> inputCell(T initialValue) {
        InputCell<T> inputCell = new InputCell<>();
        inputCell.setValue(initialValue);
        return inputCell;
    }

    public static <T> ComputeCell<T> computeCell(Function<List<T>, T> function, List<Cell<T>> cells) {
        return new ComputeCell<>(function, cells);
    }
}
