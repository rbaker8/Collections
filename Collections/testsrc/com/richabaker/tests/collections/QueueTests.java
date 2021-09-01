package com.richabaker.tests.collections;

import com.richabaker.collections.queues.RichArrayQueue;
import com.richabaker.collections.queues.RichLinkedListQueue;
import org.junit.Test;

import java.lang.annotation.Annotation;

public class QueueTests implements Test
{
    @Test
    public void testRichArrayQueue()
    {
        RichArrayQueue<String> queue = new RichArrayQueue<>(100);
    }

    @Test
    public void testRichLinkedListQueue()
    {
        RichLinkedListQueue<String> queue = new RichLinkedListQueue<>();
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
