package com.richabaker.collections.queues;

public interface RichQueue
{
    // Addition of elements
    void add(Object element) throws Exception;

    // Deletion of elements
    Object get() throws Exception;

    // Retrieving the first element without deleting it
    Object peek() throws Exception;

    // Checking if the array is empty
    boolean isEmpty();

    // Size of the array
    int size();
}
