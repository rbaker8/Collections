package com.richabaker.collections.lists;

public class RichLinkedList<T>
{
    private class Node
    {
        T value;
        Node next;
    }

    private Node head;
    private Node Tail;
    private int length;

    public void add(T value)
    {

        Node n = new Node();
        n.value = value;
        n.next = null;
        if (head == null)
        {
            head = n;
            return;
        }
        length++;
        Node iter = head;
        while (iter.next != null)
        {
            iter = iter.next;
        }
        iter.next = n;
    }

    public void reverse()
    {

    }
}
