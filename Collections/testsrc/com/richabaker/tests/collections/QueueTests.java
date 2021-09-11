package com.richabaker.tests.collections;

import com.richabaker.collections.queues.RichArrayQueue;
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
            RichGenericQueue<String> queue = new RichArrayQueue<>(100);
            queue.add("Deb");
            queue.add("Rich");
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
