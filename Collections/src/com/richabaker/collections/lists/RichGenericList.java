package com.richabaker.collections.lists;

import java.util.Iterator;

public interface RichGenericList<T> extends Iterable<T>
{
    void reverse();

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<T> iterator();

    /*
    Object[] toArray();

    <T1> T1[] toArray(T1[] a);

    */

    boolean add(T t);

    boolean remove(Object o);

    /*
    boolean containsAll(Collection<?> c);

    boolean addAll(Collection<? extends T> c);

    boolean addAll(int index, Collection<? extends T> c);

    boolean removeAll(Collection<?> c);

    boolean retainAll(Collection<?> c);
    */

    void clear();

    T get(int index);

    T set(int index, T element);

    void add(int index, T element);

    T remove(int index);

    int indexOf(Object o);

    /*
    int lastIndexOf(Object o);

    ListIterator<T> listIterator();

    ListIterator<T> listIterator(int index);

    List<T> subList(int fromIndex, int toIndex);
    */

}
