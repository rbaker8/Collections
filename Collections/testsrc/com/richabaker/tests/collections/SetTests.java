package com.richabaker.tests.collections;

import org.junit.Test;

import java.lang.annotation.Annotation;

public class SetTests implements Test
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
