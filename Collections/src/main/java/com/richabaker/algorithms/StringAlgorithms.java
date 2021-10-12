package com.richabaker.algorithms;

import java.util.HashMap;
import java.util.Map;

public class StringAlgorithms
{
    public String reverseString(String input)
    {
        char[] chars = input.toCharArray();

        int i = 0, j= chars.length - 1;

        while (i < j)
        {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++; j--;
        }
        return new String(chars);
    }

    public long repeatedString(String s, long n)
    {
        long repeats = n / s.length();
        long leftover = n % s.length();
        int acount = 0;
        for (int i = 0; i <= s.length() - 1; i++)
            if (s.charAt(i) == 'a')
                acount++;

        int acountleftover = 0;
        for (int i = 0; i < leftover; i++)
            if (s.charAt(i) == 'a')
                acountleftover++;

        return repeats * acount + acountleftover;
    }

    public void countChars(String s)
    {
        Map<Character, Integer> charsMap = new HashMap<>();

        byte[] bytes = new byte[256];
        byte[] bytes2 = new byte[10];
        char[] chars = s.toCharArray();

        for (char c: chars)
        {
            if (charsMap.containsKey(c))
            {
                charsMap.put(c, charsMap.get(c) + 1);
            }
            else
            {
                charsMap.put(c, 1);
            }
        }
        for (Character c: charsMap.keySet())
        {
            System.out.println(c + "=" + charsMap.get(c));
        }

        int c = chars[0];
        System.out.println(c);
    }

    public void subStrings(String a, String b)
    {
        boolean found = false;

        for (int start = 0; start<= a.length() - 1; start++)
        {
            for (int length = 1; length <= a.length(); length++)
            {
                String subString = a.substring(start, Math.min(start + length, a.length()));
                if (b.contains(subString))
                {
                    found = true;
                    System.out.println("Substring { " + start + ", " + length + "} is: " + subString);
                    break;
                }
            }
            if (found)
                break;
        }
        if (found)
            System.out.print("YES");
        else
            System.out.print("NO");
    }
}