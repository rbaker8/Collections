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
        RichSet<Character> set = new RichHashSet<>(10);
        set.add('A');
        set.add('B');
        set.add('C');
        set.add('D');
        set.add('E');
        set.add('F');
        set.add('G');
        set.add('H');
        assert set.size() == 8;
        assert set.contains('A');
        set.add('A');
        assert !set.contains('I');
        set.remove('A');
        assert !set.contains('A');
        assert set.size() == 7;
        set.clear();
        assert set.size() == 0;
        assert !set.contains('B');

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
