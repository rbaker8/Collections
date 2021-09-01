package com.richabaker.collections.lists;

import java.util.Iterator;

public class RichLinkedList<T> implements RichList<T>
{
    private class Node
    {
        T value;
        Node next;
    }

    private Node head;
    private Node tail;
    private int length;

    @Override
    public boolean add(T value)
    {

        Node n = new Node();
        n.value = value;
        n.next = null;
        if (head == null)
        {
            head = n;
            return true;
        }
        length++;
        Node iter = head;
        while (iter.next != null)
        {
            iter = iter.next;
        }
        iter.next = n;

        return true;
    }

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
