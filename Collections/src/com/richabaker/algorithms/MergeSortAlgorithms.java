package com.richabaker.algorithms;

import java.util.Arrays;

public class MergeSortAlgorithms<T extends Comparable<T>>
{
    private T[] aux;

    public void sort(T[] arr)
    {
        int length = arr.length;
        merge(arr, 0, length - 1);
    }

    public void merge(T[] arr, int low, int high)
    {
        if (high <= low)
            return;

        int mid = low + (high - low)/2;
        merge(arr, low, mid);
        merge(arr, mid + 1, high);

        aux = Arrays.copyOf(arr, arr.length);
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++)
        {
            // check if done with left side
            if (i > mid)
                arr[k] = aux[j++];
            else if (j > high)
                arr[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0)
                arr[k] = aux[j++];
            else
                arr[k] = aux[i++];
        }
    }
}
