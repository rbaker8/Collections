package com.richabaker.tests.collections;

import com.richabaker.collections.maps.RichHashMap;
import org.junit.Test;

import java.lang.annotation.Annotation;

public class MapTests implements Test
{

    public MapTests()
    {

    }

    @Test
    public void testRichHashMap()
    {
        RichHashMap<Character, Integer> map = new RichHashMap<>();
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
