package com.richabaker.tests.codingchallenges.hackerranks;

import com.richabaker.codingchallenges.hackerranks.HackerRanks;

import org.junit.Test;
import java.lang.annotation.Annotation;

public class HackerRankTests implements Test
{
    private HackerRanks ranks;

    public HackerRankTests ()
    {
        ranks= new HackerRanks();
    }

    @Test
    public void testHackerRanks()
    {

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
