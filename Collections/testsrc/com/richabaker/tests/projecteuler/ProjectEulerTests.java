package com.richabaker.tests.projecteuler;
import com.richabaker.codingchallenges.projecteuler.ProjectEuler;
import org.junit.Test;

import java.lang.annotation.Annotation;

public class ProjectEulerTests implements Test
{
    private ProjectEuler euler;

    public ProjectEulerTests ()
    {
        euler = new ProjectEuler();
    }

    @Test
    public void testProjectEuler()
    {

        // problem 1
        int multiples = euler.multiplesOf3or5();
        assert multiples == 233168;


        // problem 2
        int number = euler.evenFibonacciNumbers();
        assert number == 4613732;

        /*
        // problem 3
        long factor = euler.largestPrimeFactor();
        assert factor == 6857L;

        // problem 4
        long paliddrome - euler.largestPalindromeProduct();

        // problem 5
        long smallestNumber = euler.smallestNumber()

        // problem 6
        long sumSquares = euler.sumSquares();
        assert sumSquares == 25164150L;

        // problem 7
        long prime = euler.primeNumber();
        assert prime == 104743L;

        // problem 8
        int product = euler.largestProduct();
        assert product == 70600674;

        // problem 9
        int triplet = euler.pythagoreanttriplet();
        assert triplet == 31875000;

        // problem 10
        long sum = euler.sumPrimes();
        assert sum == 142913828922L;

        // problem 11
        int product2 = euler.greatestProduct();
        assert product2 == 70600674;

        // problem 12
        long triangle = euler.triangleNumber();
        assert triangle == 76576500L;

        */

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
