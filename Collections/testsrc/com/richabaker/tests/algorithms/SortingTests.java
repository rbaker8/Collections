package com.richabaker.tests.algorithms;

import com.richabaker.algorithms.MergeSortAlgorithms;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public class SortingTests implements Test
{
    @Test
    public void testMergeSort()
    {
        String[] names = new String[]{"c", "d", "a", "b" };
        MergeSortAlgorithms<String> sorter = new MergeSortAlgorithms<>();
        sorter.sort(names);
        System.out.println(Arrays.toString(names));
        assert Arrays.toString(names).equals("[a, b, c, d]");

        names = new String[]{"c", "b", "a"};
        sorter.sort(names);
        System.out.println(Arrays.toString(names));
        assert Arrays.toString(names).equals("[a, b, c]");
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
