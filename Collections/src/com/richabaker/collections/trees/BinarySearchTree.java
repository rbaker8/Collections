package com.richabaker.collections.trees;

public class BinarySearchTree<K extends Comparable<K>, V>
{
    private class Node
    {
        public Node(K key, V value)
        {
            this.key = key;
            this.value = value;
        }

        K key;
        V value;
        Node left;
        Node right;
    }

    Node root;

    public void insert(K key, V value)
    {
        if (root == null)
        {
            root = new Node(key, value);
            return;
        }
        insert(root, key, value);
    }

    public void insert(Node node, K key, V value)
    {
        if (key.compareTo(node.key) <= 0)
        {
            if (node.left == null)
            {
                node.left = new Node(key, value);
            }
            else
                insert(node.left, key, value);
        } else
        {
            if (node.right == null)
            {
                node.right = new Node(key, value);
            }
            else
                insert(node.right, key, value);
        }
    }

    public Node delete(K key)
    {
        return null;
    }

    public V find(K key)
    {
        return find(root, key);
    }

    private V find(Node node, K key)
    {
        if (node == null)
            return null;

        int compare = key.compareTo(node.key);
        if (compare == 0) return node.value;
        if (compare < 0)  return find(node.left, key);
        return find(node.right, key);
    }
}
