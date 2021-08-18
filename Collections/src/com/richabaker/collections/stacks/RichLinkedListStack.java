package com.richabaker.collections.stacks;

public class RichLinkedListStack<E> implements RichGenericStack<E>
{
    /**
     * Push implements LIFO insertion.
     * @param o the object to be stored.  This becomes the top element of the stack.
     */
    public <T extends E>void push(T o)
    {

    }

    /**
     * Pop implements LIFO retrieval.  Pop returns the top element of the stack, and removes it from the stack.
     * @return the top element of the stack, or null if the stack is empty.
     */
    public E pop()
    {
        return null;
    }

    /**
     * returns the number of elements on the stack
     * @return the number of elements on the stack.
     */
    public int getSize()
    {
        return 0;
    }

    /**
     * Empties the stack.  After a call to clear(), getSize() returns 0, and pop returns null.
     */
    public void clear()
    {

    }

}
