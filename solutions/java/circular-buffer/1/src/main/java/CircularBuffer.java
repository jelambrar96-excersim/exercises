class CircularBuffer<T> {

    private final int size;
    private T[] buffer;
    private int readIndex;
    private int writeIndex;
    private int count;

    CircularBuffer(final int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than zero.");
        }
        this.size = size;
        clear();
    }

    T read() throws BufferIOException {
        if (count == 0) {
            throw new BufferIOException("Tried to read from empty buffer");
        }
        T data = buffer[readIndex];
        buffer[readIndex] = null; // Clear the slot
        readIndex = (readIndex + 1) % size;
        count--;
        return data;
    }

    void write(T data) throws BufferIOException {
        if (count == size) {
            throw new BufferIOException("Tried to write to full buffer");
        }
        buffer[writeIndex] = data;
        writeIndex = (writeIndex + 1) % size;
        count++;
    }

    void overwrite(T data) {
        if (count == size) {
            readIndex = (readIndex + 1) % size; // Overwrite the oldest data
        } else {
            count++;
        }
        buffer[writeIndex] = data;
        writeIndex = (writeIndex + 1) % size;
    }

    @SuppressWarnings("unchecked")
    void clear() {
        this.buffer = (T[]) new Object[size];
        this.readIndex = 0;
        this.writeIndex = 0;
        this.count = 0;
    }

}