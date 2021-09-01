package com.richabaker.tests.codingchallenges.codechef;

import com.richabaker.codingchallenges.codechef.CodeChef;
import org.junit.Test;
import java.lang.annotation.Annotation;

public class CodeChefTests implements Test
{
    private CodeChef chef;

    public CodeChefTests ()
    {
        chef = new CodeChef();
    }

    @Test
    public void testCodeChef()
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
