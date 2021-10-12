package com.richabaker.tests.collections;

import com.richabaker.collections.queues.RichArrayDeque;
import com.richabaker.collections.queues.RichCircularArrayDeque;
import com.richabaker.collections.queues.RichDeque;
import com.richabaker.collections.queues.RichLinkedListDeque;
import org.junit.Test;

import java.lang.annotation.Annotation;

public class DequeTests implements Test
{
    @Test
    public void testLinkedListDeque()
    {
        try
        {
            RichDeque<String> dequeue = new RichLinkedListDeque<>();
            dequeue.push("Rich");
            dequeue.push("Deb");
            assert dequeue.size() == 2;
            assert dequeue.pop().equals("Deb");
            assert dequeue.pop().equals("Rich");
            assert dequeue.size() == 0;
        }
        catch (Exception e)
        {
            assert false;
        }
    }

    @Test
    public void testArrayDeque()
    {
        try
        {
            RichDeque<String> dequeue = new RichArrayDeque<>(100);
            dequeue.push("Rich");
            dequeue.push("Deb");
            assert dequeue.size() == 2;
            assert dequeue.pop().equals("Deb");
            assert dequeue.pop().equals("Rich");
            assert dequeue.size() == 0;
            dequeue.push("Rich");
            dequeue.push("Deb");
            dequeue.clear();
            assert dequeue.size() == 0;
            for (int i = 1; i <= 1000; i++)
            {
                dequeue.push("Rich");
            }
            assert dequeue.size() == 1000;
            for (int i = 1; i <= 1000; i++)
            {
                assert dequeue.pop().equals("Rich");
            }
            assert dequeue.size() == 0;
        }
        catch (Exception e)
        {
            assert false;
        }
    }

    @Test
    public void testCircularArrayDeque()
    {
        try
        {
            RichDeque<String> dequeue = new RichCircularArrayDeque<>(2000);
            dequeue.push("Rich");
            dequeue.push("Deb");
            assert dequeue.size() == 2;
            assert dequeue.pop().equals("Deb");
            assert dequeue.pop().equals("Rich");
            assert dequeue.size() == 0;
            dequeue.push("Rich");
            dequeue.push("Deb");
            dequeue.clear();
            assert dequeue.size() == 0;
            for (int i = 1; i <= 1000; i++)
            {
                dequeue.push("Rich");
            }
            assert dequeue.size() == 1000;
            for (int i = 1; i <= 1000; i++)
            {
                assert dequeue.pop().equals("Rich");
            }
            assert dequeue.size() == 0;

            // test circular deque, that grows slowly
            for (int i = 1; i <= 1000; i++)
            {
                for (int j = 1; j <= 7; j++)
                {
                    dequeue.push("Rich");
                }

                for (int j = 1; j <= 6; j++)
                {
                    dequeue.pop();
                }
            }
            assert dequeue.size() == 1000;
        }
        catch (Exception e)
        {
            assert false;
        }
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
