package com.richabaker.collections.stacks;

import com.richabaker.collections.queues.RichLinkedListDeque;

public class RichLinkedListStack<E> extends RichLinkedListDeque<E> implements RichGenericStack<E>
{
    @Override
    public void addFirst(E e)
    {
        super.addFirst(e);
    }

    @Override
    public void addLast(E e)
    {
        super.addLast(e);
    }

    @Override
    public boolean offerFirst(E e)
    {
        return false;
    }

    @Override
    public boolean offerLast(E e)
    {
        return false;
    }

    @Override
    public boolean add(E e)
    {
        super.addFirst(e);
        return true;
    }

    @Override
    public boolean offer(E e)
    {
        return false;
    }

    @Override
    public void push(E e)
    {
        super.addFirst(e);
    }

    @Override
    public void clear()
    {
        super.clear();
    }
    //private RichLinkedListDeque<E> deque = new RichLinkedListDeque<E>();

    /*
     * Push implements LIFO insertion.
     * @param o the object to be stored.  This becomes the top element of the stack.
     */
    //@Override
    //public <T extends E>void push(T o)
    //{
    //    deque.push(o);
    //}

    /**
     * Pop implements LIFO retrieval.  Pop returns the top element of the stack, and removes it from the stack.
     * @return the top element of the stack, or null if the stack is empty.
     */
    @Override
    public E pop()
    {
        return super.removeFirst();
    }

    /**
     * returns the number of elements on the stack
     * @return the number of elements on the stack.
     */
    //@Override
    //public int getSize()
    //{
    //    return 0;
    //}

    /**
     * Empties the stack.  After a call to clear(), getSize() returns 0, and pop returns null.
     */
    //@Override
    //public void clear()
    //{

    //}
}
