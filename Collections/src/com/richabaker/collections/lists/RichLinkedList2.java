package com.richabaker.collections.lists;

import java.util.Iterator;

public class RichLinkedList2<T> implements Iterable<T>, RichList<T>
{
    private RichListNode head;
    public RichLinkedList2()
    {
        RichListNode head = null;
    }

    private class RichListNode
    {
        public RichListNode(T data)
        {
            this.data = data;
        }
        public RichListNode next;
        public T data;
    }

    public class RichLinkedListIterator implements Iterator<T>
    {
        private RichListNode pos;

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
                return head.data;
            }
            if (hasNext())
            {
                pos = pos.next;
                return pos.data;
            }
            else
                return null;
        }
    }

    @Override
    public Iterator<T> iterator()
    {
        return new RichLinkedList2.RichLinkedListIterator();
    }

    @Override
    public boolean add(T data)
    {
        if (head == null)
        {
            head = new RichListNode(data);
            return true;
        }
        RichListNode pos = head;
        while (pos.next != null)
        {
            pos = pos.next;
        }
        pos.next = new RichListNode(data);
        return true;
    }

    @Override
    public void reverse()
    {
        RichListNode prev = null;
        RichListNode next;
        RichListNode n = head;
        while (n != null)
        {
            next = n.next;
            n.next = prev;
            prev = n;
            n = next;
        }
        head = prev;
    }
}
