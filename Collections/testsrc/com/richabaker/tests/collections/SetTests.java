package com.richabaker.tests.collections;

import com.richabaker.collections.maps.RichHashMap;
import com.richabaker.collections.sets.RichHashSet;
import com.richabaker.collections.sets.RichSet;
import org.junit.Test;

import java.lang.annotation.Annotation;

public class SetTests implements Test
{
    public SetTests()
    {

    }

    @Test
    public void testRichHashSet()
    {
        RichSet<String> set = new RichHashSet<>();
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
