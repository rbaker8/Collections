package com.richabaker.tests.collections;

import com.richabaker.collections.lists.RichLinkedList;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTests implements Test
{

    @Test
    public void test()
    {
        RichLinkedList<String> list = new RichLinkedList<>();
        list.add("Rich");
        list.add("Deb");
        Iterator<String> itor = list.iterator();
        System.out.println(itor.next());
        System.out.println(itor.next());

        List<String> list2 = new ArrayList<>();
        list2.add("Rich");
        list2.add("Deb");
        for (String name : list2)
        {
            System.out.println(name);
        }

        for (String name:list)
        {
            System.out.println(name);
        }

        for (Iterator<String> itor2 = list.iterator(); itor2.hasNext(); )
        {
            System.out.println(itor2.next());
        }
        assert(list.toString().equals("{Rich, Deb}"));
        list.reverse();
        assert(list.toString().equals("{Deb, Rich}"));
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
