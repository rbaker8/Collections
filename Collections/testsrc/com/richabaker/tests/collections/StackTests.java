package com.richabaker.tests.collections;

import com.richabaker.collections.stacks.RichArrayStack;
import com.richabaker.collections.stacks.RichGenericStack;
import com.richabaker.collections.stacks.RichLinkedListStack;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.NoSuchElementException;

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
       try {
           arrayStack.push("a");
           arrayStack.push("b");
           arrayStack.push("c");
           assert arrayStack.pop().equals("c");
           assert arrayStack.pop().equals("b");
           assert arrayStack.pop().equals("a");

           arrayStack.push("a");
           arrayStack.push("b");
           arrayStack.push("c");
           assert arrayStack.size() == 3;

           arrayStack.clear();
           assert arrayStack.size() == 0;

           assert arrayStack.pop() == null;
       }
       catch (Exception e)
       {
           assert false;
       }
    }

    @Test
    public void linkedListStackTests()
    {
        try
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
            assert linkedListStack.size() == 3;

            linkedListStack.clear();
            assert linkedListStack.size() == 0;
            try
            {
                linkedListStack.pop();
            }
            catch(Exception e)
            {

            }
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
