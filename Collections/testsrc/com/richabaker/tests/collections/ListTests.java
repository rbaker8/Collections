package com.richabaker.tests.collections;

import com.richabaker.collections.lists.RichArrayList;
import com.richabaker.collections.lists.RichGenericList;
import com.richabaker.collections.lists.RichLinkedList;
import com.richabaker.collections.lists.RichList;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTests implements Test
{
/*
    void reverse();

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<T> iterator();

    boolean add(T t);

    boolean remove(Object o);

    void clear();

    T get(int index);

    T set(int index, T element);

    void add(int index, T element);

    T remove(int index);

    int indexOf(Object o);
*/


    @Test
    public void testRichLinkedList()
    {
        RichGenericList<String> list = new RichLinkedList<String>();
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
        list.reverse();

        int count = 1;
        for (Iterator<String> itor2 = list.iterator(); itor2.hasNext(); )
        {
            String name = itor2.next();
            if (count == 1)
                assert name.equals("Rich");

            if (count == 2)
                assert name.equals("Deb");
            System.out.println(name);
            count++;
        }

        assert list.indexOf("Rich") == 0;
        assert list.indexOf("Deb") == 1;

        assert list.contains("Deb") == true;
        assert list.contains("joo") == false;

        assert list.remove("Joe") == false;
        assert list.remove("Rich") == true;
        assert list.size() == 1;
        assert list.remove("Deb") == true;
        assert list.size() == 0;
    }


    @Test
    public void testRichArrayList()
    {
        RichGenericList<String> list = new RichArrayList<>();
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

        int count = 1;
        for (Iterator<String> itor2 = list.iterator(); itor2.hasNext(); )
        {
            String name = itor2.next();
            if (count == 1)
                assert name.equals("Rich");

            if (count == 2)
                assert name.equals("Deb");
            System.out.println(name);
            count++;
        }

        assert list.indexOf("Rich") == 0;
        assert list.indexOf("Deb") == 1;

        assert list.contains("Deb") == true;
        assert list.contains("joo") == false;
        /*
        assert(list.toString().equals("{Rich, Deb}"));
        list.reverse();
        assert(list.toString().equals("{Deb, Rich}"));
        */

        assert list.remove("Joe") == false;
        assert list.remove("Rich") == true;
        assert list.size() == 1;
        assert list.remove("Deb") == true;
        assert list.size() == 0;
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
