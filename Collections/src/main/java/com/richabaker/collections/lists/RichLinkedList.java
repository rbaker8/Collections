package com.richabaker.collections.lists;

import java.util.Iterator;

public class RichLinkedList<T> implements RichGenericList<T>
{
    private class Node
    {
        T value;
        Node next;
    }

    private Node head = null;
    private Node tail = null;
    private Node prevTail = null;
    private int length = 0;

    public RichLinkedList()
    {

    }

    @Override
    public boolean add(T value)
    {

        Node n = new Node();
        n.value = value;
        n.next = null;
        tail = n;
        length++;
        if (head == null)
        {
            head = n;
            return true;
        }
        Node iter = head;
        while (iter != null)
        {
            prevTail = iter;
            iter = iter.next;
        }
        prevTail.next = n;
        return true;
    }

    @Override
    public boolean remove(Object o)
    {
        Node iter = head;
        Node prev = null;
        while (iter != null)
        {
            if (iter.value.equals(o))
            {
                length--;
                if (prev == null)
                {
                    head = head.next;
                    return true;
                }
                prev.next = iter.next;
                return true;
            }
            prev = iter;
            iter = iter.next;
        }
        return false;
    }

    @Override
    public void clear()
    {
        head = null;
        tail = null;
        prevTail = null;
        length = 0;
    }

    @Override
    public T get(int index)
    {
        return null;
    }

    @Override
    public T set(int index, T element)
    {
        return null;
    }

    @Override
    public void add(int index, T element)
    {
        int count = 0;
        Node n = new Node();
        n.value = element;
        n.next = null;

        if (index > length)
            return;

        tail = n;
        length++;
        if (head == null && index == 0)
        {
            head = n;
            prevTail = null;
            return;
        }
        Node iter = head;
        while (iter != null)
        {
            if (count == index)
            {
                break;
            }
            prevTail = iter;
            iter = iter.next;
            count++;
        }
        n.next = iter;
        prevTail.next = n;
        return;

    }

    @Override
    public T remove(int index)
    {
        Node iter = head;
        Node prev = null;
        int count = 0;
        while (iter != null)
        {
            if (index == count)
            {
                length--;
                if (prev == null)
                {
                    head = head.next;
                    return iter.value;
                }
                prev.next = iter.next;
                return iter.value;
            }
            prev = iter;
            iter = iter.next;
        }
        return null;
    }

    @Override
    public int indexOf(Object o)
    {
        int index = 0;
        Node iter = head;
        while (iter != null)
        {
            if (iter.value.equals(o))
                return index;
            iter = iter.next;
            index++;
        }
        return -1;

    }

    @Override
    public void reverse()
    {
        Node prev = null;
        Node next;
        Node n = head;
        while (n != null)
        {
            next = n.next;
            n.next = prev;
            prev = n;
            n = next;
        }
        head = prev;
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("{");
        Node iter = head;
        while (iter != null)
        {
            if (s.length() > 1)
                s.append(", ");
            s.append(iter.value);
            iter = iter.next;
        }
        s.append("}");
        return s.toString();
    }

    @Override
    public int size()
    {
        return length;
    }

    @Override
    public boolean isEmpty()
    {
        return length == 0;
    }

    @Override
    public boolean contains(Object o)
    {
        Node iter = head;
        while (iter != null)
        {
            if (iter.value.equals(o))
                return true;
            iter = iter.next;
        }
        return false;
    }

    public class RichLinkedListIterator implements Iterator<T>
    {
        private Node pos;

        public RichLinkedListIterator()
        {
            pos = null;
        }

        public boolean hasNext()
        {
            if (head == null)
                return false;
            if (pos == null)
                return true;
            return pos.next != null;
        }

        public T next()
        {
            if (pos == null)
            {
                pos = head;
                return head.value;
            }
            if (hasNext())
            {
                pos = pos.next;
                return pos.value;
            }
            else
                return null;
        }
    }

    public Iterator<T> iterator()
    {
        return new RichLinkedListIterator();
    }
}
