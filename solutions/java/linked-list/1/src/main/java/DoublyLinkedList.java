class DoublyLinkedList<T> {
    private Element<T> head;

    void push(T value) {
        if (head == null) {
            head = new Element<>(value, null, null);
            return;
        } 
        Element<T> newElement = new Element<>(value, null, null);
        Element<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newElement;
        newElement.prev = current;
    }

    T pop() {
        Element<T> current = head;
        if (current == null)
            throw new IllegalStateException("Cannot pop from an empty list.");
        while (current.next != null) {
            current = current.next;
        }
        T value = current.value;
        Element<T> prev = current.prev;
        if (prev != null) {
            prev.next = null;
        } else {
            head = null; // List is now empty
        }
        return value;
    }

    void unshift(T value) {
        if (head == null) {
            head = new Element<>(value, null, null);
            return;
        }
        Element<T> newElement = new Element<>(value, null, head);
        head.prev = newElement;
        head = newElement;
    }

    T shift() {
        if (head == null)
            throw new IllegalStateException("Cannot shift from an empty list.");
        T value = head.value;
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        return value;
    }

    private static final class Element<T> {
        private final T value;
        private Element<T> prev;
        private Element<T> next;

        Element(T value, Element<T> prev, Element<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
