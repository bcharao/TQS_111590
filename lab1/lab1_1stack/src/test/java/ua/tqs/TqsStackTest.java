package ua.tqs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class TqsStackTest {
    TqsStack tqsStack;

    @BeforeEach
    public void setup() {
        tqsStack = new TqsStack();
    }

    // Test case 1: A stack is empty on construction
    @Test
    public void stackIsEmptyOnConstruction() {
        assertTrue(tqsStack.isEmpty());
    }

    // Test case 2: A stack has size 0 on construction
    @Test
    public void stackHasSizeZeroOnConstruction() {
        assertEquals(0, tqsStack.size());
    }

    // Test case 3: After n pushes, stack is not empty and size is n
    @Test
    public void stackSizeAfterNPushesOnConstruction() {
        assertTrue(tqsStack.isEmpty());
        for (int i = 0; i < 3; i++) {
            tqsStack.push(i);
        }
        assertFalse(tqsStack.isEmpty());
        assertEquals(3, tqsStack.size());
    }

    // Test case 4: Push x then pop returns x
    @Test
    public void pushXInStackThenPopX() {
        tqsStack.push(1);
        assertEquals(1, tqsStack.pop());
    }

    // Test case 5: Push x then peek returns x and size remains the same
    @Test
    public void pushThenPeekAndSizeUnchanged() {
        tqsStack.push(1);
        assertEquals(1, tqsStack.size());
        assertEquals(1, tqsStack.peek());
        assertEquals(1, tqsStack.size());
    }

    // Test case 6: After n pops, stack is empty and size is 0
    @Test
    public void popNTimesMakesAEmptyStack() {
        int n = 3;
        for (int i = 0; i < n; i++) {
            tqsStack.push(i);
        }
        for (int i = 0; i < n; i++) {
            tqsStack.pop();
        }
        assertTrue(tqsStack.isEmpty());
        assertEquals(0, tqsStack.size());
    }

    // Test case 7: Pop from empty stack throws NoSuchElementException
    @Test
    public void popEmptyStackThrowsException() {
        assertThrows(NoSuchElementException.class, tqsStack::pop);
    }

    // Test case 8: Peek into empty stack throws NoSuchElementException
    @Test
    public void peekEmptyStackThrowsException() {
        assertThrows(NoSuchElementException.class, tqsStack::peek);
    }

    // Test case 9: Push into a full stack throws IllegalStateException
    @Test
    public void pushFullStackThrowsException() {
        BoundedTqsStack boundedStack = new BoundedTqsStack(1);
        boundedStack.push(1);

        assertThrows(IllegalStateException.class, () -> boundedStack.push(2));
    }

    @Test
    public void testPopTopN(){
        tqsStack.push(1);
        tqsStack.push(2);
        tqsStack.push(3);

        assertEquals(2, tqsStack.popTopN(2));
        assertEquals(1, tqsStack.size());

        assertEquals(3, tqsStack.popTopN(1));
        assertTrue(tqsStack.isEmpty());

    }
}