package com.richabaker.collections.lists;

public interface RichList<T> extends Iterable<T>
{
    void add(T value);
    void reverse();
}
