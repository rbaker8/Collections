package collections.linkedlist;
import com.richabaker.collections.linkedlist.RichLinkedList;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RichLinkedListTests implements Test
{
    @Test
    public void test()
    {
        RichLinkedList<String> list = new RichLinkedList<String>();
        list.add("Rich");
        list.add("Deb");
        Iterator<String> itor = list.iterator();
        System.out.println(itor.next());
        System.out.println(itor.next());

        List<String> list2 = new ArrayList<String>();
        for (String str : list2)
        {

        }

        //for (String name:list)
        //{
        //    System.out.println(name);
        //}

        for (Iterator<String> itor2 = list.iterator(); itor2.hasNext(); )
        {
            System.out.println(itor2.next());
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
