package com.richabaker.tests.projecteuler;
import com.richabaker.projecteuler.ProjectEuler;
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

        int multiples = euler.multiplesOf3or5();
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
