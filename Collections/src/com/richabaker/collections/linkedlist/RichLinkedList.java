package com.richabaker.collections.linkedlist;

import java.util.Iterator;

public class RichLinkedList<T> implements Iterable<T>
{
    private RichListNode<T> head;

    private class RichListNode<T>
    {
        public RichListNode(T data)
        {
            this.data = data;
        }
        public RichListNode next = null;
        public T data = null;
    }


    public class RichLinkedListIterator implements Iterator<T>
    {
        private RichListNode<T> pos;

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
            if (hasNext() == true)
            {
                pos = pos.next;
                return pos.data;
            }
            else
                return null;
        }
    }

    //public RichLinkedListIterator iterator()
    public Iterator<T> iterator()
    {
        return new RichLinkedListIterator();
        /*
        return new Iterator<T>()
        {
            RichListNode<T> pos;
            public boolean hasNext()
            {
                if (head == null)
                    return false;
                if (pos == null)
                    return false;
                return pos.next != null;
            }
            public T next()
            {
                if (head == null)
                    return null;
               if (pos == null)
              {
                    pos = head;
                    return head.data;
                }
                if (pos.next != null)
                {
                    pos = pos.next;
                    return pos.data;
                }
                else
                    return null;
            }
        };*/
    }

    public void RichLinkedList()
    {
        RichListNode<T> head = null;
    }

    public void add(T data)
    {
        if (head == null)
        {
            head = new RichListNode<T>(data);
            return;
        }
        RichListNode<T> pos = head;
        while (pos.next != null)
        {
            pos = pos.next;
        }
        pos.next = new RichListNode<T>(data);
    }
}
