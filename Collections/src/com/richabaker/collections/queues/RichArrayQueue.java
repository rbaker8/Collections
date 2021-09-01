package com.richabaker.collections.queues;

import java.util.NoSuchElementException;

public class RichArrayQueue<E> implements RichGenericQueue<E>
{
    // Initial Capacity of Buffer
    private int capacity = 0;
    // Initial Size of Buffer
    private int size = 0;
    // Head pointer
    private int head = 0;
    // Tail pointer
    private int tail = -1;
    private E[] array;

    // Constructor
    public RichArrayQueue(int capacity)
    {
        // Initializing the capacity of the array
        this.capacity = capacity;

        // Initializing the array
        array = (E[]) new Object[capacity];
    }

    // Addition of elements
    @Override
    public boolean add(E element) throws Exception
    {

        // Calculating the index to add the element
        int index = (tail + 1) % capacity;

        // Size of the array increases as elements are added
        size++;

        // Checking if the array is full
        if (size == capacity) {
            throw new Exception("Buffer Overflow");
        }

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

        // Incrementing the head pointer to point
        // to the next element
        head++;

        // Decrementing the size of the array as the
        // elements are deleted
        size--;

        // Returning the first element
        return element;
    }

    // Retrieving the first element without deleting it
    @Override
    public E peek() throws Exception
    {

        // Checking if the array is empty
        if (size == 0) {
            throw new Exception("Empty Buffer");
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
    public boolean offer(E e)
    {
        return false;
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
    public E remove()
    {
        return null;
    }
    */
    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    @Override
    public E poll()
    {
        return null;
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
    public E element()
    {
        return null;
    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    /*
    E peek()
    {
        return null;
    }
    */
}
