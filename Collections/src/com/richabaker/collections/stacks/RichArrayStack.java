package com.richabaker.collections.stacks;

import java.util.Arrays;

public class RichArrayStack<E> implements RichGenericStack<E>
{
    private E[] items;
    private int size = 0;
    private static final int INITIAL_SIZE = 1;
    private int capacity;

    public RichArrayStack()
    {
        items = (E[])new Object[INITIAL_SIZE];
        capacity = INITIAL_SIZE;
    }

    /**
     * Push implements LIFO insertion.
     * @param o the object to be stored.  This becomes the top element of the stack.
     */
    public <T extends E>void push(T o)
    {
        if (size == capacity)
        {
            capacity += INITIAL_SIZE;
            E[] newItems = (E[])new Object[capacity];
            newItems = Arrays.copyOf(items, capacity);
            //Arrays.cop
            items = newItems;
        }
        items[size++] = o;

    }

    /**
     * Pop implements LIFO retrieval.  Pop returns the top element of the stack, and removes it from the stack.
     * @return the top element of the stack, or null if the stack is empty.
     */
    public E pop()
    {
        if (size == 0)
            return null;
        else
            return items[size--];

    }

    /**
     * returns the number of elements on the stack
     * @return the number of elements on the stack.
     */
    public int getSize()
    {
        return size;
    }

    /**
     * Empties the stack.  After a call to clear(), getSize() returns 0, and pop returns null.
     */
    public void clear()
    {
        size=0;
    }

}
