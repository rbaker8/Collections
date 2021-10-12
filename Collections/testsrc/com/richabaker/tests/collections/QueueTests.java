package com.richabaker.tests.collections;

import com.richabaker.collections.queues.RichCircularArrayQueue;
import com.richabaker.collections.queues.RichGenericQueue;
import com.richabaker.collections.queues.RichLinkedListQueue;
import org.junit.Test;

import java.lang.annotation.Annotation;

public class QueueTests implements Test
{
    @Test
    public void testRichArrayQueue()
    {
        try
        {
            RichGenericQueue<String> queue = new RichCircularArrayQueue<>(10);
            queue.add("Deb");
            queue.add("Rich");
            assert queue.size() == 2;
            assert !queue.isEmpty();
            assert queue.remove().equals("Deb");
            assert queue.remove().equals("Rich");
            assert queue.size() == 0;
            assert queue.isEmpty();

            // test circular array implementation of queue
            for (int i = 0; i < 10; i++)
            {
                queue.add("a");
                queue.add("b");
                queue.add("c");
                queue.add("d");
                queue.add("e");
                assert queue.remove().equals("a");
                assert queue.remove().equals("b");
                assert queue.remove().equals("c");
                assert queue.remove().equals("d");
                assert queue.remove().equals("e");
            }
        }
        catch (Exception e)
        {
        }
    }

    @Test
    public void testRichLinkedListQueue()

    {
        try
        {
            RichLinkedListQueue<String> queue = new RichLinkedListQueue<>();
            queue.add("Deb");
            queue.add("Rich");
            assert queue.size() == 2;
            assert !queue.isEmpty();
            assert queue.remove().equals("Deb");
            assert queue.remove().equals("Rich");
            assert queue.size() == 0;
            assert queue.isEmpty();
            queue.add("Deb");
            queue.add("Rich");
            queue.clear();
            assert queue.isEmpty();
            assert queue.size() == 0;

            for (int i = 0; i < 10; i++)
            {
                queue.add("a");
                queue.add("b");
                queue.add("c");
                queue.add("d");
                queue.add("e");
                assert queue.remove().equals("a");
                assert queue.remove().equals("b");
                assert queue.remove().equals("c");
                assert queue.remove().equals("d");
                assert queue.remove().equals("e");
            }
        }
        catch (Exception e)
        {
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
