package com.richabaker.collections.stacks;

import com.richabaker.collections.queues.RichDeque;

public interface RichGenericStack<E> extends RichDeque<E>
{
    /**
     * Push implements LIFO insertion.
     * @param o the object to be stored.  This becomes the top element of the stack.
     */
    //<T extends E>void push(T o);

    /**
     * Pop implements LIFO retrieval.  Pop returns the top element of the stack, and removes it from the stack.
     * @return the top element of the stack, or null if the stack is empty.
     */
    //E pop();

    /**
     * returns the number of elements on the stack
     * @return the number of elements on the stack.
     */
    int size();

    /**
     * Empties the stack.  After a call to clear(), getSize() returns 0, and pop returns null.
     */
    void clear();

}
