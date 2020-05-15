package com.richabaker.tests.algorithms;
import com.richabaker.algorithms.Algorithms;
import com.richabaker.algorithms.StringAlgorithms;
import org.junit.Test;

import java.lang.annotation.Annotation;

public class AlgorithmsTests extends Algorithms implements Test
{

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
