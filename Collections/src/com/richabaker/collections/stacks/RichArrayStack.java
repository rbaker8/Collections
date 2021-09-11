package com.richabaker.collections.stacks;

import com.richabaker.collections.lists.RichArrayList;

import java.util.Arrays;
import java.util.Iterator;

public class RichArrayStack<E> extends RichArrayList<E> implements RichGenericStack<E>
{
    private E[] items;
    private int size = 0;
    private static final int INITIAL_SIZE = 1;
    private static final int GROWTH_FACTOR = 100;
    private int capacity;

    public RichArrayStack()
    {
        items = (E[]) new Object[INITIAL_SIZE];
        capacity = INITIAL_SIZE;
    }

    private void checkCapacity()
    {
        if (size == capacity) {
            capacity += GROWTH_FACTOR;
            E[] newItems = (E[]) new Object[capacity];
            newItems = Arrays.copyOf(items, capacity);
            //Arrays.cop
            items = newItems;
        }
    }

    /**
     * Push implements LIFO insertion.
     *
     * @param o the object to be stored.  This becomes the top element of the stack.
     */
    public void push(E o)
    {
        checkCapacity();
        items[size++] = o;
    }

    /**
     * Pop implements LIFO retrieval.  Pop returns the top element of the stack, and removes it from the stack.
     *
     * @return the top element of the stack, or null if the stack is empty.
     */
    public E pop()
    {
        if (size == 0)
            return null;
        else
            return items[--size];

    }

    public E remove()
    {
        return pop();
    }

    public E removeFirst()
    {
        return null;
    }

    public E removeLast()
    {
        return null;
    }

    public boolean offerFirst(E e)
    {
        return false;
    }

    public boolean offerLast(E e)
    {
        return false;
    }

    public void addFirst(E e)
    {

    }

    public void addLast(E e)
    {

    }

    public boolean offer(E e)
    {
        return false;
    }

    public E peek()
    {
        return null;
    }

    public E element()
    {
        return null;
    }

    public E poll()
    {
        return null;
    }

    /**
     * returns the number of elements on the stack
     * @return the number of elements on the stack.
     */

    public int size()
    {
        return size;
    }

    /**
     * Empties the stack.  After a call to clear(), getSize() returns 0, and pop returns null.
     */
    public void clear()
    {
        size = 0;
    }

    @Override
    public Iterator<E> iterator()
    {
        return null;
    }

}
