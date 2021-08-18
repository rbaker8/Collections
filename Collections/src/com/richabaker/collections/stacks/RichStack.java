package com.richabaker.collections.stacks;

public interface RichStack
{
        /**
         * Push implements LIFO insertion.
         * @param o the object to be stored.  This becomes the top element of the stack.
         */
        void push(Object o);

        /**
         * Pop implements LIFO retrieval.  Pop returns the top element of the stack, and removes it from the stack.
         * @return the top element of the stack, or null if the stack is empty.
         */
        Object pop();

        /**
         * returns the number of elements on the stack
         * @return the number of elements on the stack.
         */
        int getSize();

        /**
         * Empties the stack.  After a call to clear(), getSize() returns 0, and pop returns null.
         */
        void clear();
}
