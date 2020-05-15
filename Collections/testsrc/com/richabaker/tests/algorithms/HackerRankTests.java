package com.richabaker.tests.algorithms;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.*;

public class HackerRankTests implements Test
{
    private int testFunction(String a, String b)
    {
        Map<Character, Integer> aMap, bMap;
        aMap = new HashMap<Character, Integer>();
        bMap = new HashMap<Character, Integer>();
        for (char aChar: a.toCharArray())
        {
            if (aMap.containsKey(aChar))
            {
                aMap.put(aChar, aMap.get(aChar) + 1);
            }
            else
            {
                aMap.put(aChar, 1);
            }
        }
        for (char bChar: b.toCharArray())
        {
            if (bMap.containsKey(bChar))
            {
                bMap.put(bChar, bMap.get(bChar) + 1);
            }
            else
            {
                bMap.put(bChar, 1);
            }
        }
        Set<Character> aKeySet = aMap.keySet();
        Set<Character> bKeySet = bMap.keySet();


        int totalDeletes = 0;

        for (Character aChar: aKeySet)
        {
            int deletes = 0;
            int aOccurances = aMap.get(aChar);
            if (bKeySet.contains(aChar))
            {
                int bOccurances = bMap.get(aChar);
                if (bOccurances > 0)
                {
                    bMap.put(aChar, 0);
                    if (aOccurances > bOccurances)
                        deletes = aOccurances - bOccurances;
                    else
                        deletes = bOccurances - aOccurances;
                }
            }

            else
            {
                deletes = aOccurances;
            }
            totalDeletes += deletes;
        }

        for (Character bChar: bKeySet)
        {
            totalDeletes += bMap.get(bChar);
        }

            return totalDeletes;
    }

    private int testFunction2(String a, String b)
    {
        int charsA[]  = new int[26];

        for (char aChar: a.toCharArray())
            charsA[aChar - 'a']++;

        int letter = 0;
        for (int i : charsA)
        {
            if (i > 0)
                System.out.print(";" + (char)(letter + 'a') + ": " + i);
            letter++;
        }

        // -------------------------
        Map<Character, Integer> aMap, bMap;
        aMap = new HashMap<Character, Integer>();
        bMap = new HashMap<Character, Integer>();
        for (char aChar: a.toCharArray())
        {
            if (aMap.containsKey(aChar))
            {
                aMap.put(aChar, aMap.get(aChar) + 1);
            }
            else
            {
                aMap.put(aChar, 1);
            }
        }
        for (char bChar: b.toCharArray())
        {
            if (bMap.containsKey(bChar))
            {
                bMap.put(bChar, bMap.get(bChar) + 1);
            }
            else
            {
                bMap.put(bChar, 1);
            }
        }
        Set<Character> aKeySet = aMap.keySet();
        Set<Character> bKeySet = bMap.keySet();


        int totalDeletes = 0;

        for (Character aChar: aKeySet)
        {
            int deletes = 0;
            int aOccurances = aMap.get(aChar);
            if (bKeySet.contains(aChar))
            {
                int bOccurances = bMap.get(aChar);
                if (bOccurances > 0)
                {
                    bMap.put(aChar, 0);
                    if (aOccurances > bOccurances)
                        deletes = aOccurances - bOccurances;
                    else
                        deletes = bOccurances - aOccurances;
                }
            }

            else
            {
                deletes = aOccurances;
            }
            totalDeletes += deletes;
        }

        for (Character bChar: bKeySet)
        {
            totalDeletes += bMap.get(bChar);
        }

        return totalDeletes;
    }

    @Test
    public void HackerRankTest()
    {
        int a = testFunction("fcrxzwscanmligyxyvym", "jxwtrhvujlmrpdoqbisbwhmgpmeoke");
        System.out.println(a);

        int b = testFunction2("fcrxzwscanmligyxyvym", "jxwtrhvujlmrpdoqbisbwhmgpmeoke");
    }

    int getMedian(int[] array1, int[] array2)
    {
        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));

        int[] newArray = new int[array1.length * 2];
        int a = 0;
        int b = 0;
        int c = 0;
        while (a < array1.length && b < array2.length)
        {
            if (array1[a] < array2[b])
            {
                newArray[c++] = array1[a++];
            }
            else
            {
                newArray[c++] = array2[b++];
            }
        }

        while (a < array1.length)
        {
            newArray[c++] = array1[a++];
        }

        while (b < array2.length)
        {
            newArray[c++] = array2[b++];
        }

        System.out.println("Result is:" + Arrays.toString(newArray));
        return (newArray[(newArray.length / 2) - 1] + newArray[newArray.length / 2]) / 2;
    }

    @Test
    public void HackerRankTest2()
    {
        try
        {
            int b[] = new int[]{1, 2, 3, 4, 5};
            int c[] = new int[0];

            //a[0] = 1;
            //a[1] = 12;
            //a[2] = 15;
            //a[3] = 26;
            //a[4] = 38;

            int a[] = new int[]{6, 7, 8, 9, 10};
            //b[0] = 2;
            //b[1] = 13;
            //b[2] = 17;
            //b[3] = 30;
            //b[4] = 45;
            int median = getMedian(a, b);
            System.out.println("Median is: " + median);

            System.out.println("c length is: " + c.length);
            median = getMedian(c, c);
            System.out.println("Median is: " + median);

        }
        catch (Exception e)
        {
            System.out.print ("Error calculating median: ");
            System.out.println(e.toString());
            e.printStackTrace();
        }
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
