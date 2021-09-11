package com.richabaker.collections.queues;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RichLinkedListQueue<E> implements RichGenericQueue<E>
{
        protected class Node
        {
                E value;
                Node next;
                Node ()
                {

                }
        }

        protected Node head = null;
        protected Node tail = null;
        protected Node prevTail = null;
        protected int length = 0;

        /**
         * Inserts the specified element into this queue if it is possible to do so
         * immediately without violating capacity restrictions, returning
         * {@code true} upon success and throwing an {@code IllegalStateException}
         * if no space is currently available.
         *
         * @param e the element to add
         * @return {@code true} (as specified by {@link Collection#add})
         * @throws IllegalStateException if the element cannot be added at this
         *         time due to capacity restrictions
         * @throws ClassCastException if the class of the specified element
         *         prevents it from being added to this queue
         * @throws NullPointerException if the specified element is null and
         *         this queue does not permit null elements
         * @throws IllegalArgumentException if some property of this element
         *         prevents it from being added to this queue
         */

        @Override
        public boolean add(E e)
        {
                //RichLinkedListQueue.Node<E> n = new RichLinkedListQueue<E>.Node();
                Node n = new Node();
                n.value = e;
                n.next = null;
                tail = n;
                length++;
                if (head == null)
                {
                        head = n;
                        return true;
                }
                //RichLinkedListQueue.Node<E> iter = head;
                Node iter = head;
                while (iter != null)
                {
                        prevTail = iter;
                        iter = iter.next;
                }
                prevTail.next = n;
                return true;

        }

        /**
         * Inserts the specified element into this queue if it is possible to do
         * so immediately without violating capacity restrictions.
         * When using a capacity-restricted queue, this method is generally
         * preferable to {@link #add}, which can fail to insert an element only
         * by throwing an exception.
         *
         * @param e the element to add
         * @return {@code true} if the element was added to this queue, else
         *         {@code false}
         * @throws ClassCastException if the class of the specified element
         *         prevents it from being added to this queue
         * @throws NullPointerException if the specified element is null and
         *         this queue does not permit null elements
         * @throws IllegalArgumentException if some property of this element
         *         prevents it from being added to this queue
         */
        @Override
        public boolean offer(E e)
        {
            return true;
        }

        /**
         * Retrieves and removes the head of this queue.  This method differs
         * from {@link #poll() poll()} only in that it throws an exception if
         * this queue is empty.
         *
         * @return the head of this queue
         * @throws NoSuchElementException if this queue is empty
         */
        @Override
        public E remove() throws NoSuchElementException
        {
                Node iter = head;

                if (iter == null)
                        throw new NoSuchElementException();

                length--;
                head = head.next;

                if (length == 0)
                {
                        tail = null;
                }
                return iter.value;
        }

        /**
         * Retrieves and removes the head of this queue,
         * or returns {@code null} if this queue is empty.
         *
         * @return the head of this queue, or {@code null} if this queue is empty
         */
        @Override
        public E poll()
        {
            return null;
        }

        /**
         * Retrieves, but does not remove, the head of this queue.  This method
         * differs from {@link #peek peek} only in that it throws an exception
         * if this queue is empty.
         *
         * @return the head of this queue
         * @throws NoSuchElementException if this queue is empty
         */
        @Override
        public E element()
        {
            return null;
        }

        /**
         * Retrieves, but does not remove, the head of this queue,
         * or returns {@code null} if this queue is empty.
         *
         * @return the head of this queue, or {@code null} if this queue is empty
         */
        @Override
        public E peek()
        {
            return null;
        }

        // Checking if the array is empty
        @Override
        public boolean isEmpty()
        {
            return true;
        }

        // Size of the queue
        @Override
        public int size()
        {
            return length;
        }

        // empty the queue
        @Override
        public void clear()
        {
                head = null;
                tail = null;
                prevTail = null;
                length = 0;
        }

        private class RichLinkedListQueueIterator implements Iterator<E>
        {
                private Node pos;

                public RichLinkedListQueueIterator()
                {
                        pos = null;
                }

                public boolean hasNext()
                {
                        if (head == null)
                                return false;
                        //if (pos == null)
                        //        return false;
                        return pos.next != null;
                }

                public E next()
                {
                        if (pos == null)
                        {
                                pos = head;
                                return (E) head.value;
                        }
                        if (hasNext())
                        {
                                pos = pos.next;
                                return (E)pos.value;
                        }
                        else
                                return null;
                }
        }

        public Iterator<E> iterator()
        {
                return new RichLinkedListQueue.RichLinkedListQueueIterator();
        }

}

