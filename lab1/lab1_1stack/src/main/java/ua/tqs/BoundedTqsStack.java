package ua.tqs;

public class BoundedTqsStack<T> extends TqsStack<T> {

    private final int maxSize;

    public BoundedTqsStack(int maxSize) {
        super();
        this.maxSize = maxSize;

    }

    @Override
    public void push(T item) {
        if(this.size() == maxSize) {
            throw new IllegalStateException();
        }
        super.push(item);
    }
}
