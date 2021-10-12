package com.richabaker.tests.collections;

import com.richabaker.collections.maps.RichHashMap;
import com.richabaker.collections.maps.RichLinkedHashMap;
import com.richabaker.collections.maps.RichMap;
import org.junit.Test;

import java.lang.annotation.Annotation;

public class MapTests implements Test
{

    // a key class that has a very bad hash implementation to stress test Map
    private static class BadKey
    {
        private String badKey;

        BadKey(String key)
        {
            badKey = key;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;

            if (o instanceof BadKey)
                return badKey.equals(((BadKey)o).badKey);
            return false;
        }

        // a bad hashcode method
        @Override
        public int hashCode()
        {
            return 0;
        }
    }

    public MapTests()
    {

    }

    @Test
    public void testRichHashMap()
    {
        RichMap<Character, Integer> map = new RichHashMap<>();
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

        RichMap<String, Integer> stringMap = new RichHashMap<>();
        for (int i = 0; i < 1000; i++)
        {
            String key = "Rich" + i;

            // breakpoint for debugging
            if (i == 300)
            {
                int j = i;
            }
            stringMap.put (key, i);
        }
        assert stringMap.get("Rich300").equals(300);

        RichMap<BadKey, Integer> badMap = new RichHashMap<>();
        for (int i = 0; i < 1000; i++)
        {
            String key = "Rich" + i;
            BadKey badKey = new BadKey(key);

            // breakpoint for debugging
            if (i == 300)
            {
                int j = i;
            }
            badMap.put (badKey, i);
        }
        for (int i = 0; i < 1000; i++)
        {
            String key = "Rich" + i;
            BadKey badKey = new BadKey(key);
            assert badMap.get(badKey).equals(i);
        }
    }

    @Test
    public void testRichLinkedHashMap()
    {
        RichMap<Character, Integer> map = new RichLinkedHashMap<>();
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
