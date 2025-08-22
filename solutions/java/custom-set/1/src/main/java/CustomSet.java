import java.util.Collection;
import java.util.ArrayList;

class CustomSet<T> {
    
    private Node head;
    int size;

    CustomSet() {
        this.head = null;
        this.size = 0;
    }

    CustomSet(Collection<T> data) {
        this();
        if (data != null) {
            for (T element : data) {
                add(element);
            }
        }
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean contains(T data) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    boolean isDisjoint(CustomSet<T> other) {
        if (other == null || other.isEmpty() || this.isEmpty()) {
            return true;
        }
        Node current = this.head;
        while (current != null) {
            if (other.contains(current.data)) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    boolean add(T element) {
        if (element == null || contains(element)) {
            return false;
        }
        Node newNode = new Node(element);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CustomSet)) return false;
        CustomSet<T> other = (CustomSet<T>) obj;
        if (this.size != other.size) return false;

        Node current = this.head;
        while (current != null) {
            if (!other.contains(current.data)) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    CustomSet<T> getIntersection(CustomSet<T> other) {
        CustomSet<T> intersection = new CustomSet<T>();
        if (other == null || other.isEmpty() || this.isEmpty()) {
            return intersection;
        }
        Node current = this.head;
        while (current != null) {
            if (other.contains(current.data)) {
                intersection.add(current.data);
            }
            current = current.next;
        }
        return intersection;
    }

    CustomSet<T> getUnion(CustomSet<T> other) {
        CustomSet<T> union = new CustomSet<T>();
        if (other == null || other.isEmpty()) {
            return this;
        }
        Node current = this.head;
        while (current != null) {
            union.add(current.data);
            current = current.next;
        }
        current = other.head;
        while (current != null) {
            union.add(current.data);
            current = current.next;
        }
        return union;
    }

    CustomSet<T> getDifference(CustomSet<T> other) {
        CustomSet<T> difference = new CustomSet<T>();
        if (other == null || other.isEmpty()) {
            return this;
        }
        Node current = this.head;
        while (current != null) {
            if (!other.contains(current.data)) {
                difference.add(current.data);
            }
            current = current.next;
        }
        return difference;
    }

    boolean isSubset(CustomSet<T> other) {
        if (other == null) {
            return false;
        }
        if (other.isEmpty()) {
            return true; // An empty set is a subset of any set
        }
        Node current = other.head;
        while (current != null) {
            if (!this.contains(current.data)) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

}
