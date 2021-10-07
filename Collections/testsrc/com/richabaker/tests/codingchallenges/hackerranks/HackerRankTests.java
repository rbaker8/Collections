package com.richabaker.tests.codingchallenges.hackerranks;

import com.richabaker.codingchallenges.hackerranks.HackerRanks;

import com.richabaker.collections.maps.RichHashMap;
import com.richabaker.collections.maps.RichMap;
import com.richabaker.collections.sets.RichSet;
import org.junit.Test;
import java.lang.annotation.Annotation;
import java.util.Arrays;

public class HackerRankTests implements Test
{
    private HackerRanks ranks;

    public HackerRankTests ()
    {
        ranks = new HackerRanks();
    }

    @Test
    public void testHackerRanks()
    {
        testFunction("abc", "def");
        assert testFunction2("abc").equals(";a: 1;b: 1;c: 1");
        int a = testFunction3("fcrxzwscanmligyxyvym", "jxwtrhvujlmrpdoqbisbwhmgpmeoke");
        assert a == 30;

        assert testFunction4("aabcdefff").equals("a: 2;b: 1;c: 1;d: 1;e: 1;f: 3");
    }

    private int testFunction(String a, String b)
    {
        RichMap<Character, Integer> aMap, bMap;
        aMap = new RichHashMap<>();
        bMap = new RichHashMap<>();
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
        RichSet<Character> aKeySet = aMap.keySet();
        RichSet<Character> bKeySet = bMap.keySet();

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

    // determine the number of occurances of the characters a-z in a string
    private String testFunction2(String a)
    {
        int[] charsA = new int[26];

        for (char aChar : a.toCharArray())
            charsA[aChar - 'a']++;

        int letter = 0;
        StringBuffer occurancesBuffer = new StringBuffer();
        String occurances = "";
        for (int i : charsA) {
            if (i > 0) {
                System.out.print(";" + (char) (letter + 'a') + ": " + i);
                occurancesBuffer.append(";" + (char) (letter + 'a') + ": " + i);
                occurances = occurances + ";" + (char) (letter + 'a') + ": " + i;
            }
            letter++;
        }
        System.out.println();
        return occurances;
    }

    private int testFunction3(String a, String b)
    {
        RichMap<Character, Integer> aMap, bMap;
        aMap = new RichHashMap<>();
        bMap = new RichHashMap<>();
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
        RichSet<Character> aKeySet = aMap.keySet();
        RichSet<Character> bKeySet = bMap.keySet();

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

    private int getMedian(int[] array1, int[] array2)
    {
        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));

        int[] newArray = new int[array1.length + array2.length];
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
        if (c == 0)
            return 0;
        if (c == 1)
            return newArray[0];
        if (c % 2 == 1)
            return newArray[c / 2];
        return (newArray[(c / 2) - 1] + newArray[(c / 2)]) / 2;
    }

    @Test
    public void HackerRankTest2()
    {
        try
        {
            int[] a = new int[] {2, 13, 17, 30, 45};
            int[] b = new int[] {1, 12, 15, 26, 38};
            int[] c = new int[0];

            int median = getMedian(a, b);
            System.out.println("Median is: " + median);
            assert median == 16;

            System.out.println("c length is: " + c.length);
            median = getMedian(c, c);
            System.out.println("Median is: " + median);
            assert median == 0;
        }
        catch (Exception e)
        {
            System.out.print ("Error calculating median: ");
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private String testFunction4(String a)
    {
        RichMap<Character, Integer> aMap = new RichHashMap<>();
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
        RichSet<Character> aKeySet = aMap.keySet();

        String occurances = "";
        StringBuffer occurancesBuffer = new StringBuffer();
        int count = 0;
        for (Character c : aKeySet)
        {
                if (count > 0)
                {
                    occurances = occurances + ";";
                    occurancesBuffer.append(";");
                }
                occurances = occurances + c + ": " + aMap.get(c);
                occurancesBuffer.append(c  + ": " + aMap.get(c));
                count++;
        }

        return occurancesBuffer.toString();
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
