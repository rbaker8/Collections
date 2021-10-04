package com.richabaker.collections.queues;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RichArrayQueue<E> implements RichGenericQueue<E>
{
    // Initial Capacity of Buffer
    protected int capacity;
    private static final int INITIAL_CAPACITY = 100;
    // growth factor
    private static final int GROWTH_FACTOR = 100;
    // Initial Size of Buffer
    protected int size = 0;
    // Head pointer
    protected int head = 0;
    // Tail pointer
    protected int tail = -1;
    protected E[] array;

    // Constructors

    public RichArrayQueue()
    {
        // Initializing the capacity of the array
        this.capacity = INITIAL_CAPACITY;
        init();
    }

    public RichArrayQueue(int capacity)
    {
        // Initializing the capacity of the array
        this.capacity = capacity;
        init();
    }

    protected void init()
    {

        // Initializing the array
        array = (E[]) new Object[this.capacity];
        size = 0;
        // Head pointer
        head = 0;
        // Tail pointer
        tail = -1;
    }

    protected void checkCapacity()
    {
        if (size == capacity)
        {
            capacity += GROWTH_FACTOR;
            E[] newItems;// = (E[])new Object[capacity];
            newItems = Arrays.copyOf(array, capacity);
            //Arrays.cop
            array = newItems;
        }
    }

    // Addition of elements
    @Override
    public boolean add(E element) throws Exception
    {
        if (element == null)
            throw new NullPointerException();

        // Size of the array increases as elements are added
        size++;

        //checkCapacity();

        // Checking if the array is full
        if (size > capacity)
        {
            throw new Exception("Buffer Overflow");
        }

        // Calculating the index to add the element
        int index = (tail + 1) % capacity;

        // Storing the element in the array
        array[index] = element;

        // Incrementing the tail pointer to point
        // to the element added currently
        tail++;
        return true;
    }

    // Deletion of elements
    @Override
    public E remove() throws Exception
    {

        // Checking if the array is empty
        if (size == 0) {
            throw new Exception("Empty Buffer");
        }

        // Calculating the index of the element to be
        // deleted
        int index = head % capacity;

        // Getting the element
        E element = array[index];

        // don't store a reference to the element anymore, so it can get garbage collected
        array[index] = null;

        // Incrementing the head pointer to point
        // to the next element
        head++;

        // Decrementing the size of the array as the
        // elements are deleted
        size--;

        // Returning the first element
        return element;
    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */

    @Override
    public E peek() throws Exception
    {

        // Checking if the array is empty
        if (size == 0) {
            return null;
        }

        // Calculating the index of the
        // element to be deleted
        int index = head % capacity;

        // Getting the element
        E element = array[index];

        // Returning the element
        return element;
    }

    // Checking if the array is empty
    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    // Size of the array
    @Override
    public int size()
    {
        return size;
    }

    /**
     * Inserts the specified element into this queue if it is possible to do
     * so immediately without violating capacity restrictions.
     * When using a capacity-restricted queue, this method is generally
     * preferable to {@link #add}, which can fail to insert an element only
     * by throwing an exception.
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this queue, else
     *         {@code false}
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this queue
     * @throws NullPointerException if the specified element is null and
     *         this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *         prevents it from being added to this queue
     */
    @Override
    public boolean offer(E e) throws Exception
    {
        if (e == null)
            throw new NullPointerException();
        if (size == capacity)
            return false;
        return add(e);
    }

    // empty the queue
    @Override
    public void clear()
    {
        init();
    }

    /**
     * Retrieves and removes the head of this queue.  This method differs
     * from {@link #poll() poll()} only in that it throws an exception if
     * this queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */

    /*
    @Override
    public E remove() throws Exception
    {
        if (size == 0)
            return new NoSuchElementException();
        return poll();
    }
    */

    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    @Override
    public E poll() throws Exception
    {
       if (size == 0)
           return null;
       return remove();
    }

    /**
     * Retrieves, but does not remove, the head of this queue.  This method
     * differs from {@link #peek peek} only in that it throws an exception
     * if this queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    @Override
    public E element() throws Exception
    {
        if (size == 0)
            throw new NoSuchElementException();
        return null;
    }

    @Override
    public Iterator<E> iterator()
    {
        return new RichArrayQueue<E>.RichArrayQueueIterator();
    }

    private class RichArrayQueueIterator implements Iterator<E>
    {
        private int pos;

        public RichArrayQueueIterator()
        {
            pos = 0;
        }

        public boolean hasNext()
        {
            return pos < size;
        }

        public E next()
        {
            return array[pos++];
        }
    }
}
