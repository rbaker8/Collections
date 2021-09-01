package com.richabaker.tests.collections;

import com.richabaker.collections.stacks.RichArrayStack;
import com.richabaker.collections.stacks.RichGenericStack;
import com.richabaker.collections.stacks.RichLinkedListStack;
import org.junit.Test;

import java.lang.annotation.Annotation;

public class StackTests implements Test
{
    RichGenericStack<String> arrayStack = new RichArrayStack<>();
    RichGenericStack<String> linkedListStack = new RichLinkedListStack<>();

    public StackTests()
    {

    }

    @Test
    public void arrayStackTests()
    {
       arrayStack.push("a");
       arrayStack.push("b");
       arrayStack.push("c");
       assert arrayStack.pop().equals("c");
       assert arrayStack.pop().equals("b");
       assert arrayStack.pop().equals("a");

       arrayStack.push("a");
       arrayStack.push("b");
       arrayStack.push("c");
       assert arrayStack.getSize() == 3;

       arrayStack.clear();
       assert arrayStack.getSize() == 0;

       assert arrayStack.pop() == null;
    }

    @Test
    public void linkedListStackTests()
    {
        linkedListStack.push("a");
        linkedListStack.push("b");
        linkedListStack.push("c");
        assert linkedListStack.pop().equals("c");
        assert linkedListStack.pop().equals("b");
        assert linkedListStack.pop().equals("a");

        linkedListStack.push("a");
        linkedListStack.push("b");
        linkedListStack.push("c");
        assert linkedListStack.getSize() == 3;

        linkedListStack.clear();
        assert linkedListStack.getSize() == 0;

        assert linkedListStack.pop() == null;
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
