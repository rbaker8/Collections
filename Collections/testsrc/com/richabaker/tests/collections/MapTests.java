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
        map.put('A', 0);
        map.put('B', 0);
        map.put('C', 0);
        map.put('D', 0);
        map.put('E', 0);
        map.put('F', 0);
        map.put('G', 0);
        map.put('H', 0);
        assert map.size() == 8;
        assert map.containsKey('A');
        assert map.get('A').equals(0);
        map.put('A', 1);
        assert map.get('A').equals(1);
        assert !map.containsKey('I');
        map.remove('A');
        assert !map.containsKey('A');
        assert map.size() == 7;
        map.clear();
        assert map.size() == 0;
        assert !map.containsKey('B');
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
