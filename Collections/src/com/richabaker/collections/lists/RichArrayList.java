package com.richabaker.collections.lists;

import java.util.*;

public class RichArrayList<E> implements RichGenericList<E>
{
    private E[] items;
    private int size = 0;
    private static final int INITIAL_SIZE = 1;
    private static final int GROWTH_AMOUNT = 100;
    private int capacity;

    private void checkCapacity()
    {
        if (size == capacity)
        {
            capacity += GROWTH_AMOUNT;
            E[] newItems = (E[])new Object[capacity];
            newItems = Arrays.copyOf(items, capacity);
            //Arrays.cop
            items = newItems;
        }
    }

    public RichArrayList()
    {
        items = (E[])new Object[INITIAL_SIZE];
        capacity = INITIAL_SIZE;
    }

    @Override
    public void reverse()
    {

    }

    @Override
    public boolean add(E item)
    {
        checkCapacity();
        items[size++] = item;
        return true;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public boolean contains(Object o)
    {
        for (int i = 0; i < size; i++)
            if (o.equals(items[i]))
            {
                return true;
            }
        return false;
    }

    @Override
    public Iterator<E> iterator()
    {
        return new RichArrayListIterator();
    }

    /*
    @Override
    public Object[] toArray()
    {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a)
    {
        return null;
    }
    */

    @Override
    public boolean remove(Object o)
    {
        for (int i = 0; i < size; i++)
            if (o.equals(items[i]))
            {
                for (int j = i; j < size - 1; j++)
                    items[j] = items[j + 1];
                size--;
                return true;
            }
        return false;
    }

    /*
    @Override
    public boolean containsAll(Collection<?> c)
    {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c)
    {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c)
    {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        return false;
    }
    */

    @Override
    public void clear()
    {
        size = 0;
    }
    @Override
    public E get(int index)
    {
        if (index < size)
            return items[index];
        else
            return null;
    }

    @Override
    public E set(int index, E element)
    {
        return null;
    }

    @Override
    public void add(int index, E element)
    {

    }

    @Override
    public E remove(int index)
    {
        return null;
    }

    @Override
    public int indexOf(Object o) {

        int index = -1;

        //if (!(o instanceof E))
        //    return index;

        for (int i = 0; i < size; i++)
            if (o.equals(items[i]))
            {
                index = i;
                break;
            }
        return index;
    }

    /*
    @Override
    public int lastIndexOf(Object o)
    {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator()
    {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index)
    {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex)
    {
        return null;
    }
    */

    private class RichArrayListIterator implements Iterator<E>
    {
        private int pos = 0;

        public RichArrayListIterator()
        {
            pos = 0;
        }

        public boolean hasNext()
        {
            if (pos < size)
                return true;
            else
                return false;
        }

        public E next()
        {
            return items[pos++];
        }
    }
}
