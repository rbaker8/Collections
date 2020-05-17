package com.richabaker.tests.algorithms;

import com.richabaker.algorithms.MergeSortAlgorithms;
import com.richabaker.collections.trees.RichBinarySearchTree;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public class BinarySearchTests implements Test
{
    private RichBinarySearchTree<String, String> tree = new RichBinarySearchTree<>();

    @Test
    public void BinarySearchTreeTests()
    {
        tree.insert("c", "c");
        tree.insert("a", "a");
        tree.insert("b", "b");
        tree.insert("d", "d");
        tree.insert("e", "e");
        String value = tree.find("e");
        assert(value.equals("e"));
    }

    @Override
    public Class<? extends Throwable> expected()
    {
        return null;
    }

    @Override
    public long timeout()
    {
        return 0;
    }

    @Override
    public Class<? extends Annotation> annotationType()
    {
        return null;
    }

}
