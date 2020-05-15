package com.richabaker.tests.algorithms;
import com.richabaker.algorithms.StringAlgorithms;
import org.junit.Test;

import java.lang.annotation.Annotation;

public class StringAlgorithmsTests extends StringAlgorithms implements Test
{
    private void pasteCode()
    {

    }

    @Test
    public void testRepeatedStrings()
    {
        long counta = repeatedString("aba", 10);
        assert counta == 7;
    }

    @Test
    public void testReverseString()
    {

        pasteCode();

        String input = "abcdefg";
        String output = reverseString(input);
        assert output.equals("gfedcba");
    }

    @Test
    public void testReverseString2()
    {
        String input = "abcdefg";
        String output = reverseString(input);
        assert output.equals("gfedcba");
    }

    @Test
    public void testCountChars()
    {
        String input = "abcdefg";
        countChars(input);
    }

    @Test
    public void  testSubStrings()
    {
        subStrings("Hi", "World");
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
