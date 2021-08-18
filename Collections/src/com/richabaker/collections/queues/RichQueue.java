package com.richabaker.collections.queues;

public interface RichQueue
{
    /**
     * Add adds an element to the end of the queue.
     * @param o  the element to be added.
     */
    void add(Object o);

    /**
     * Next returns the first item in the queue, removing it from the container.
     * @return The first item in the queue, or null if the queue is empty.
     */
    Object next();

}
