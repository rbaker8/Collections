package com.richabaker.collections.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RichCircularArrayDeque<E> implements RichDeque<E>
{
    private static final int MAX = 100;
    private E  arr[];
    private int  front;
    private int  rear;
    private int  size;
    private int  length;

    public RichCircularArrayDeque()
    {
        this(MAX);
    }

    public RichCircularArrayDeque(int size)
    {
        front = -1;
        rear = 0;
        this.size = size;
        arr=  (E[]) new Object[size];
    }

    /**
     * Returns the number of elements in this deque.
     *
     */

    @Override
    public int size()
    {
        return length;
    }

    // Checks whether Deque is full or not.
    public boolean isFull()
    {
        return ((front == 0 && rear == size - 1) || front == rear + 1);
    }

    // Checks whether Deque is empty or not.
    public boolean isEmpty ()
    {
        return (front == -1);
    }

    // Inserts an element at front
    @Override
    public void addFirst(E e)
    {
        // check whether Deque is full or not
        if (isFull())
        {
            return;
        }

        // If queue is initially empty
        if (front == -1)
        {
            front = 0;
            rear = 0;
        }

        // front is at first position of queue
        else if (front == 0)
            front = size - 1 ;

        else // decrement front end by '1'
            front = front - 1;

        // insert current element into Deque
        arr[front] = e ;
        length++;
    }

    // function to inset element at rear end
    // of Deque.

    @Override
    public void addLast(E e)
    {
        if (isFull())
        {
            return;
        }

        // If queue is initially empty
        if (front == -1)
        {
            front = 0;
            rear = 0;
        }

        // rear is at last position of queue
        else if (rear == size-1)
            rear = 0;

        // increment rear end by '1'
        else
            rear = rear+1;

        // insert current element into Deque
        arr[rear] = e ;
        length++;
    }

    // Deletes element at front end of Deque
    @Override
    public E removeFirst()
    {
        // check whether Deque is empty or not
        if (isEmpty())
        {
            return null;
        }

        E e = arr[front];
        arr[front] = null;
        // Deque has only one element
        if (front == rear)
        {
            front = -1;
            rear = -1;
        }
        else
            // back to initial position
            if (front == size - 1)
                front = 0;

            else // increment front by '1' to remove current
                // front value from Deque
                front = front + 1;
        length--;
        return e;
    }

    // Delete element at rear end of Deque
    @Override
    public E removeLast()
    {
        if (isEmpty())
        {
            return null;
        }

        E e = arr[rear];
        arr[rear] = null;
        // Deque has only one element
        if (front == rear)
        {
            front = -1;
            rear = -1;
        }
        else if (rear == 0)
            rear = size - 1;
        else
            rear = rear - 1;

        length--;
        return e;
    }

    // Returns front element of Deque
    public E getFront()
    {
        // check whether Deque is empty or not
        if (isEmpty())
        {
            return null ;
        }
        return arr[front];
    }

    // function return rear element of Deque
    public E getRear()
    {
        // check whether Deque is empty or not
        if(isEmpty() || rear < 0)
        {
            return null;
        }
        return arr[rear];
    }

    /**
     * Returns an iterator over the elements in this deque in proper sequence.
     * The elements will be returned in order from first (head) to last (tail).
     *
     * @return an iterator over the elements in this deque in proper sequence
     */

    @Override
    public Iterator<E> iterator()
    {
        return null;
    }

    /**
     * Returns {@code true} if this deque contains the specified element.
     * More formally, returns {@code true} if and only if this deque contains
     * at least one element {@code e} such that {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this deque is to be tested
     * @return {@code true} if this deque contains the specified element
     * @throws ClassCastException if the class of the specified element
     *         is incompatible with this deque
     * (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     * (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     *
     */

    @Override
    public boolean contains(Object o)
    {
        return false;
    }

    // *** Stack methods ***

    /**
     * Pushes an element onto the stack represented by this deque (in other
     * words, at the head of this deque) if it is possible to do so
     * immediately without violating capacity restrictions, throwing an
     * {@code IllegalStateException} if no space is currently available.
     *
     * <p>This method is equivalent to {@link #addFirst}.
     *
     * @param e the element to push
     * @throws IllegalStateException if the element cannot be added at this
     *         time due to capacity restrictions
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this deque
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this deque
     */
    @Override
    public void push(E e)
    {
        addFirst(e);
    }

    /**
     * Pops an element from the stack represented by this deque.  In other
     * words, removes and returns the first element of this deque.
     *
     * <p>This method is equivalent to {@link #removeFirst()}.
     *
     * @return the element at the front of this deque (which is the top
     *         of the stack represented by this deque)
     * @throws NoSuchElementException if this deque is empty
     */

    @Override
    public E pop() throws Exception
    {
        return removeFirst();
    }

    @Override
    public E remove() throws Exception
    {
        return removeFirst();
    }

    @Override
    public boolean add(E e)
    {
        addLast(e);
        return true;
    }

    /**
     * Inserts the specified element at the end of this deque unless it would
     * violate capacity restrictions.  When using a capacity-restricted deque,
     * this method is generally preferable to the {@link #addLast} method,
     * which can fail to insert an element only by throwing an exception.
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this deque, else
     *         {@code false}
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this deque
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this deque
     */

    @Override
    public boolean offerLast(E e)
    {
        return false;
    }

    /**
     * Inserts the specified element at the front of this deque unless it would
     * violate capacity restrictions.  When using a capacity-restricted deque,
     * this method is generally preferable to the {@link #addFirst} method,
     * which can fail to insert an element only by throwing an exception.
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this deque, else
     *         {@code false}
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this deque
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this deque
     */

    @Override
    public boolean offerFirst(E e)
    {
        return false;
    }

    @Override
    public void clear()
    {
        // don't hold on to old references
        for (int i = 0; i < size; i++)
        {
            arr[i] = null;
        }
        front = -1;
        rear = 0;
        length = 0;
    }

    @Override
    public E peek() throws Exception
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
    public E element() throws Exception
    {
        return null;
    }

    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */

    @Override
    public E poll() throws Exception
    {
        return null;
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
        return false;
    }
}
