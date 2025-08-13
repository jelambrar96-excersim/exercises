import java.util.NoSuchElementException;
import java.lang.reflect.Array;

class SimpleLinkedList<T> {

    private int size;
    private Element<T> head;

    SimpleLinkedList() {
        this.size = 0;
        this.head = null;
    }

    SimpleLinkedList(T[] values) {
        // TODO: improve this
        for (T v: values) {
            this.push(v);
        }
    }

    void push(T value) {
        this.size += 1;
        Element<T> e = new Element<T>(value, null);
        if (this.head == null) {
            this.head = e;
            return;
        }
        Element<T> current = this.head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = e;
    }

    T pop() {
        Element<T> current = this.head;
        if (current == null) {
            throw new NoSuchElementException("Cannot pop from an empty list.");
        }
        Element<T> prev = null;
        while (current.next != null) {
            prev = current;
            current = current.next;
        }
        T value = current.value;
        if (prev == null) {
            this.head = prev;
        }
        else {
            prev.next = null;
        }
        this.size -= 1;
        return value;
    }

    void reverse() {
        Element<T> current = this.head;
        if (current == null) {
            return;
        }
        Element<T> newHead = current;
        Element<T> auxHead = null;
        while (current.next != null) {
            current = current.next;
            newHead.next = auxHead;
            auxHead = newHead;
            newHead = current;
        }
        if (auxHead != null) {
            newHead.next = auxHead;
        }
        this.head = newHead;
    }

    T[] asArray(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        T[] array = (T[])Array.newInstance(clazz, this.size);
        Element<T> e = this.head;
        for(int i = size - 1; e != null; e = e.next, i--) {
            array[i] = e.value;
        }
        return array;
    }

    int size() {
        return this.size;
    }


    private static final class Element<T> {
        private final T value;
        private Element<T> next;

        Element(T value, Element<T> next) {
            this.value = value;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        SimpleLinkedList<String> list = new SimpleLinkedList<String>();
        list.push("9");
        list.push("8");
        list.push("7");
        list.push("6");
        list.push("5");
        list.reverse();

        System.out.println(list.pop());
        System.out.println(list.pop());
        System.out.println(list.pop());
        System.out.println(list.pop());
        System.out.println(list.pop());
        System.out.println(list.pop());
        System.out.println(list.pop());
    }



}


