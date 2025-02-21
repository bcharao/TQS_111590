package ua.tqs;

import java.util.LinkedList;
import java.util.NoSuchElementException;


public class TqsStack <T>{
    private LinkedList<T> collection;

    public TqsStack() {
        collection = new LinkedList<T>();
    }

    public T pop() {
        if (collection.isEmpty()) {
            throw new NoSuchElementException();
        }
        return collection.removeLast();
    }

    public int size() {
        return collection.size();
    }

    public T peek() {
        if(collection.isEmpty()){
            throw new NoSuchElementException();
        }
        return collection.peekLast();
    }

    public void push(T item) {
        if(item == null){
            throw new IllegalArgumentException();
        }
        collection.add(item);
    }

    public boolean isEmpty() {
        return collection.isEmpty();
    }

    public T popTopN(int n) {
        T top = null;
        for (int i = 0; i < n; i++) {
            top = collection.removeFirst();
        }
        return top;
    }

}
