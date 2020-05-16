package com.richabaker.collections.linkedlist;

import com.richabaker.collections.lists.RichList;

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

    public Iterator<T> iterator()
    {
        return new RichLinkedListIterator();
    }

    public void add(T data)
    {
        if (head == null)
        {
            head = new RichListNode(data);
            return;
        }
        RichListNode pos = head;
        while (pos.next != null)
        {
            pos = pos.next;
        }
        pos.next = new RichListNode(data);
    }

    public void reverse()
    {

    }
}
