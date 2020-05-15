package com.richabaker.tests.algorithms;

import com.richabaker.algorithms.ListAlgorithms;
import com.richabaker.collections.lists.RichLinkedList;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListAlgorithmTests implements Test
{
    @Test
    public void testRansomNote()
    {
        String []magazineWords = {"Hello", "World"};
        String []noteWords = {"Hello", "World"};

        ListAlgorithms.ransomNote( magazineWords, noteWords);
    }

    @Test
    public void testReverseLinkedList()
    {
        RichLinkedList<String> list = new RichLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");

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
