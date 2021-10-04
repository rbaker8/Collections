package com.richabaker.collections.sets;

import com.richabaker.collections.maps.RichHashMap;

import java.util.*;

public class RichHashSet<E> extends RichAbstractSet<E> implements RichSet<E>
{
    private RichHashMap<E, Object> map = new RichHashMap<E, Object>();

    // Dummy value to associate with an Object in the backing Map
    private static final Object PRESENT = new Object();


    /**
     * Constructs a new, empty set; the backing {@code HashMap} instance has
     * the specified initial capacity and the specified load factor.
     *
     * @param      initialCapacity   the initial capacity of the hash map
     * @param      loadFactor        the load factor of the hash map
     * @throws     IllegalArgumentException if the initial capacity is less
     *             than zero, or if the load factor is nonpositive
     */
    public RichHashSet(int initialCapacity, float loadFactor) {
        map = new RichHashMap<>(initialCapacity, loadFactor);
    }


    /**
     * Constructs a new, empty set; the backing {@code HashMap} instance has
     * the specified initial capacity and default load factor (0.75).
     *
     * @param      initialCapacity   the initial capacity of the hash table
     * @throws     IllegalArgumentException if the initial capacity is less
     *             than zero
     */
    public RichHashSet(int initialCapacity) {
        map = new RichHashMap<>(initialCapacity);
    }

    /**
     * Returns the number of elements in this set (its cardinality).  If this
     * set contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of elements in this set (its cardinality)
     */
    public int size()
    {
        return map.size();
    }

    /**
     * Returns {@code true} if this set contains no elements.
     *
     */
    public boolean isEmpty()
    {
        return map.size() == 0;
    }

    /**
     * Returns {@code true} if this set contains the specified element.
     * More formally, returns {@code true} if and only if this set
     * contains an element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this set is to be tested
     * @return {@code true} if this set contains the specified element
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this set
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         set does not permit null elements
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    public boolean contains(Object o)
    {
        return map.containsKey(o);
    }

    /**
     * Returns an iterator over the elements in this set.  The elements are
     * returned in no particular order (unless this set is an instance of some
     * class that provides a guarantee).
     *
     * @return an iterator over the elements in this set
     */
    public Iterator<E> iterator()
    {
        return map.keySet().iterator();
    }

    /**
     * Returns an array containing all of the elements in this set.
     * If this set makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the
     * elements in the same order.
     *
     * <p>The returned array will be "safe" in that no references to it
     * are maintained by this set.  (In other words, this method must
     * allocate a new array even if this set is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all the elements in this set
     */
    public Object[] toArray()
    {
        return null;
    }

    /**
     * Returns an array containing all of the elements in this set; the
     * runtime type of the returned array is that of the specified array.
     * If the set fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the
     * specified array and the size of this set.
     *
     * <p>If this set fits in the specified array with room to spare
     * (i.e., the array has more elements than this set), the element in
     * the array immediately following the end of the set is set to
     * {@code null}.  (This is useful in determining the length of this
     * set <i>only</i> if the caller knows that this set does not contain
     * any null elements.)
     *
     * <p>If this set makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements
     * in the same order.
     *
     * <p>Like the {@link #toArray()} method, this method acts as bridge between
     * array-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs.
     *
     * <p>Suppose {@code x} is a set known to contain only strings.
     * The following code can be used to dump the set into a newly allocated
     * array of {@code String}:
     *
     * <pre>
     *     String[] y = x.toArray(new String[0]);</pre>
     *
     * Note that {@code toArray(new Object[0])} is identical in function to
     * {@code toArray()}.
     *
     * @param a the array into which the elements of this set are to be
     *        stored, if it is big enough; otherwise, a new array of the same
     *        runtime type is allocated for this purpose.
     * @return an array containing all the elements in this set
     * @throws ArrayStoreException if the runtime type of the specified array
     *         is not a supertype of the runtime type of every element in this
     *         set
     * @throws NullPointerException if the specified array is null
     */
    public <T> T[] toArray(T[] a)
    {
        return null;
    }


    // Modification Operations

    /**
     * Adds the specified element to this set if it is not already present
     * (optional operation).  More formally, adds the specified element
     * {@code e} to this set if the set contains no element {@code e2}
     * such that
     * {@code Objects.equals(e, e2)}.
     * If this set already contains the element, the call leaves the set
     * unchanged and returns {@code false}.  In combination with the
     * restriction on constructors, this ensures that sets never contain
     * duplicate elements.
     *
     * <p>The stipulation above does not imply that sets must accept all
     * elements; sets may refuse to add any particular element, including
     * {@code null}, and throw an exception, as described in the
     * specification for {@link Collection#add Collection.add}.
     * Individual set implementations should clearly document any
     * restrictions on the elements that they may contain.
     *
     * @param e element to be added to this set
     * @return {@code true} if this set did not already contain the specified
     *         element
     * @throws UnsupportedOperationException if the {@code add} operation
     *         is not supported by this set
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this set
     * @throws NullPointerException if the specified element is null and this
     *         set does not permit null elements
     * @throws IllegalArgumentException if some property of the specified element
     *         prevents it from being added to this set
     */
    public boolean add(E e)
    {
        return map.put(e, PRESENT)==null;
    }


    /**
     * Removes the specified element from this set if it is present
     * (optional operation).  More formally, removes an element {@code e}
     * such that
     * {@code Objects.equals(o, e)}, if
     * this set contains such an element.  Returns {@code true} if this set
     * contained the element (or equivalently, if this set changed as a
     * result of the call).  (This set will not contain the element once the
     * call returns.)
     *
     * @param o object to be removed from this set, if present
     * @return {@code true} if this set contained the specified element
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this set
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         set does not permit null elements
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws UnsupportedOperationException if the {@code remove} operation
     *         is not supported by this set
     */
    public boolean remove(Object o)
    {
        return map.remove(o)==PRESENT;
    }


    // Bulk Operations

    /**
     * Returns {@code true} if this set contains all of the elements of the
     * specified collection.  If the specified collection is also a set, this
     * method returns {@code true} if it is a <i>subset</i> of this set.
     *
     * @param  c collection to be checked for containment in this set
     * @return {@code true} if this set contains all of the elements of the
     *         specified collection
     * @throws ClassCastException if the types of one or more elements
     *         in the specified collection are incompatible with this
     *         set
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified collection contains one
     *         or more null elements and this set does not permit null
     *         elements
     * (<a href="Collection.html#optional-restrictions">optional</a>),
     *         or if the specified collection is null
     * @see    #contains(Object)
     */
    public boolean containsAll(Collection<?> c)
    {
        return false;
    }

    /**
     * Adds all of the elements in the specified collection to this set if
     * they're not already present (optional operation).  If the specified
     * collection is also a set, the {@code addAll} operation effectively
     * modifies this set so that its value is the <i>union</i> of the two
     * sets.  The behavior of this operation is undefined if the specified
     * collection is modified while the operation is in progress.
     *
     * @param  c collection containing elements to be added to this set
     * @return {@code true} if this set changed as a result of the call
     *
     * @throws UnsupportedOperationException if the {@code addAll} operation
     *         is not supported by this set
     * @throws ClassCastException if the class of an element of the
     *         specified collection prevents it from being added to this set
     * @throws NullPointerException if the specified collection contains one
     *         or more null elements and this set does not permit null
     *         elements, or if the specified collection is null
     * @throws IllegalArgumentException if some property of an element of the
     *         specified collection prevents it from being added to this set
     * @see #add(Object)
     */
    public boolean addAll(Collection<? extends E> c)
    {
        return false;
    }

    /**
     * Retains only the elements in this set that are contained in the
     * specified collection (optional operation).  In other words, removes
     * from this set all of its elements that are not contained in the
     * specified collection.  If the specified collection is also a set, this
     * operation effectively modifies this set so that its value is the
     * <i>intersection</i> of the two sets.
     *
     * @param  c collection containing elements to be retained in this set
     * @return {@code true} if this set changed as a result of the call
     * @throws UnsupportedOperationException if the {@code retainAll} operation
     *         is not supported by this set
     * @throws ClassCastException if the class of an element of this set
     *         is incompatible with the specified collection
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if this set contains a null element and the
     *         specified collection does not permit null elements
     *         (<a href="Collection.html#optional-restrictions">optional</a>),
     *         or if the specified collection is null
     * @see #remove(Object)
     */
    public boolean retainAll(Collection<?> c)
    {
        return false;
    }

    /**
     * Removes from this set all of its elements that are contained in the
     * specified collection (optional operation).  If the specified
     * collection is also a set, this operation effectively modifies this
     * set so that its value is the <i>asymmetric set difference</i> of
     * the two sets.
     *
     * @param  c collection containing elements to be removed from this set
     * @return {@code true} if this set changed as a result of the call
     * @throws UnsupportedOperationException if the {@code removeAll} operation
     *         is not supported by this set
     * @throws ClassCastException if the class of an element of this set
     *         is incompatible with the specified collection
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if this set contains a null element and the
     *         specified collection does not permit null elements
     *         (<a href="Collection.html#optional-restrictions">optional</a>),
     *         or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    public boolean removeAll(Collection<?> c)
    {
        return false;
    }

    /**
     * Removes all of the elements from this set (optional operation).
     * The set will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the {@code clear} method
     *         is not supported by this set
     */
    public void clear()
    {
        map.clear();
    }

    // Comparison and hashing

    /**
     * Compares the specified object with this set for equality.  Returns
     * {@code true} if the specified object is also a set, the two sets
     * have the same size, and every member of the specified set is
     * contained in this set (or equivalently, every member of this set is
     * contained in the specified set).  This definition ensures that the
     * equals method works properly across different implementations of the
     * set interface.
     *
     * @param o object to be compared for equality with this set
     * @return {@code true} if the specified object is equal to this set
     */
    public boolean equals(Object o)
    {
        return false;
    }

    /**
     * Returns the hash code value for this set.  The hash code of a set is
     * defined to be the sum of the hash codes of the elements in the set,
     * where the hash code of a {@code null} element is defined to be zero.
     * This ensures that {@code s1.equals(s2)} implies that
     * {@code s1.hashCode()==s2.hashCode()} for any two sets {@code s1}
     * and {@code s2}, as required by the general contract of
     * {@link Object#hashCode}.
     *
     * @return the hash code value for this set
     * @see Object#equals(Object)
     * @see Set#equals(Object)
     */
    public int hashCode()
    {
        return 0;
    }

    /**
     * Creates a {@code Spliterator} over the elements in this set.
     *
     * <p>The {@code Spliterator} reports {@link Spliterator#DISTINCT}.
     * Implementations should document the reporting of additional
     * characteristic values.
     *
     * @implSpec
     * The default implementation creates a
     * <em><a href="Spliterator.html#binding">late-binding</a></em> spliterator
     * from the set's {@code Iterator}.  The spliterator inherits the
     * <em>fail-fast</em> properties of the set's iterator.
     * <p>
     * The created {@code Spliterator} additionally reports
     * {@link Spliterator#SIZED}.
     *
     * @implNote
     * The created {@code Spliterator} additionally reports
     * {@link Spliterator#SUBSIZED}.
     *
     * @return a {@code Spliterator} over the elements in this set
     * @since 1.8
     */
    //@Override
    //default Spliterator<E> spliterator() {
    //    return Spliterators.spliterator(this, Spliterator.DISTINCT);
    //}

    /**
     * Returns an unmodifiable set containing zero elements.
     * See <a href="#unmodifiable">Unmodifiable Sets</a> for details.
     *
     * @param <E> the {@code Set}'s element type
     * @return an empty {@code Set}
     *
     * @since 9
     */
    //static <E> Set<E> of()
    //{
    //    return ImmutableCollections.emptySet();
    //}

    /**
     * Returns an unmodifiable set containing one element.
     * See <a href="#unmodifiable">Unmodifiable Sets</a> for details.
     *
     * @param <E> the {@code Set}'s element type
     * @param e1 the single element
     * @return a {@code Set} containing the specified element
     * @throws NullPointerException if the element is {@code null}
     *
     * @since 9
     */

    /*
    static <E> Set<E> of(E e1)
    {
        return new ImmutableCollections.Set12<>(e1);
    }
    */
    /**
     * Returns an unmodifiable set containing two elements.
     * See <a href="#unmodifiable">Unmodifiable Sets</a> for details.
     *
     * @param <E> the {@code Set}'s element type
     * @param e1 the first element
     * @param e2 the second element
     * @return a {@code Set} containing the specified elements
     * @throws IllegalArgumentException if the elements are duplicates
     * @throws NullPointerException if an element is {@code null}
     *
     * @since 9
     */
    //static <E> Set<E> of(E e1, E e2) {
    //    return new ImmutableCollections.Set12<>(e1, e2);
    //}

    /**
     * Returns an unmodifiable set containing three elements.
     * See <a href="#unmodifiable">Unmodifiable Sets</a> for details.
     *
     * @param <E> the {@code Set}'s element type
     * @param e1 the first element
     * @param e2 the second element
     * @param e3 the third element
     * @return a {@code Set} containing the specified elements
     * @throws IllegalArgumentException if there are any duplicate elements
     * @throws NullPointerException if an element is {@code null}
     *
     * @since 9
     */
    //static <E> Set<E> of(E e1, E e2, E e3) {
    //    return new ImmutableCollections.SetN<>(e1, e2, e3);
    //}

    /**
     * Returns an unmodifiable set containing four elements.
     * See <a href="#unmodifiable">Unmodifiable Sets</a> for details.
     *
     * @param <E> the {@code Set}'s element type
     * @param e1 the first element
     * @param e2 the second element
     * @param e3 the third element
     * @param e4 the fourth element
     * @return a {@code Set} containing the specified elements
     * @throws IllegalArgumentException if there are any duplicate elements
     * @throws NullPointerException if an element is {@code null}
     *
     * @since 9
     */
    //static <E> Set<E> of(E e1, E e2, E e3, E e4)
    // {
    //    return new ImmutableCollections.SetN<>(e1, e2, e3, e4);
    //}

    /**
     * Returns an unmodifiable set containing five elements.
     * See <a href="#unmodifiable">Unmodifiable Sets</a> for details.
     *
     * @param <E> the {@code Set}'s element type
     * @param e1 the first element
     * @param e2 the second element
     * @param e3 the third element
     * @param e4 the fourth element
     * @param e5 the fifth element
     * @return a {@code Set} containing the specified elements
     * @throws IllegalArgumentException if there are any duplicate elements
     * @throws NullPointerException if an element is {@code null}
     *
     * @since 9
     */
    //static <E> Set<E> of(E e1, E e2, E e3, E e4, E e5)
    // {
    //    return new ImmutableCollections.SetN<>(e1, e2, e3, e4, e5);
    //}

    /**
     * Returns an unmodifiable set containing six elements.
     * See <a href="#unmodifiable">Unmodifiable Sets</a> for details.
     *
     * @param <E> the {@code Set}'s element type
     * @param e1 the first element
     * @param e2 the second element
     * @param e3 the third element
     * @param e4 the fourth element
     * @param e5 the fifth element
     * @param e6 the sixth element
     * @return a {@code Set} containing the specified elements
     * @throws IllegalArgumentException if there are any duplicate elements
     * @throws NullPointerException if an element is {@code null}
     *
     * @since 9
     */
    //static <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
    //    return new ImmutableCollections.SetN<>(e1, e2, e3, e4, e5,
    //            e6);
    //}

    /**
     * Returns an unmodifiable set containing seven elements.
     * See <a href="#unmodifiable">Unmodifiable Sets</a> for details.
     *
     * @param <E> the {@code Set}'s element type
     * @param e1 the first element
     * @param e2 the second element
     * @param e3 the third element
     * @param e4 the fourth element
     * @param e5 the fifth element
     * @param e6 the sixth element
     * @param e7 the seventh element
     * @return a {@code Set} containing the specified elements
     * @throws IllegalArgumentException if there are any duplicate elements
     * @throws NullPointerException if an element is {@code null}
     *
     * @since 9
     */
    //static <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
    //    return new ImmutableCollections.SetN<>(e1, e2, e3, e4, e5,
    //            e6, e7);
    //}

    /**
     * Returns an unmodifiable set containing eight elements.
     * See <a href="#unmodifiable">Unmodifiable Sets</a> for details.
     *
     * @param <E> the {@code Set}'s element type
     * @param e1 the first element
     * @param e2 the second element
     * @param e3 the third element
     * @param e4 the fourth element
     * @param e5 the fifth element
     * @param e6 the sixth element
     * @param e7 the seventh element
     * @param e8 the eighth element
     * @return a {@code Set} containing the specified elements
     * @throws IllegalArgumentException if there are any duplicate elements
     * @throws NullPointerException if an element is {@code null}
     *
     * @since 9
     */
    //static <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8)
    //{
    //    return new ImmutableCollections.SetN<>(e1, e2, e3, e4, e5,
    //            e6, e7, e8);
    //}

    /**
     * Returns an unmodifiable set containing nine elements.
     * See <a href="#unmodifiable">Unmodifiable Sets</a> for details.
     *
     * @param <E> the {@code Set}'s element type
     * @param e1 the first element
     * @param e2 the second element
     * @param e3 the third element
     * @param e4 the fourth element
     * @param e5 the fifth element
     * @param e6 the sixth element
     * @param e7 the seventh element
     * @param e8 the eighth element
     * @param e9 the ninth element
     * @return a {@code Set} containing the specified elements
     * @throws IllegalArgumentException if there are any duplicate elements
     * @throws NullPointerException if an element is {@code null}
     *
     * @since 9
     */
    //static <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9)
    // {
    //    return new ImmutableCollections.SetN<>(e1, e2, e3, e4, e5,
    //            e6, e7, e8, e9);
    //}

    /**
     * Returns an unmodifiable set containing ten elements.
     * See <a href="#unmodifiable">Unmodifiable Sets</a> for details.
     *
     * @param <E> the {@code Set}'s element type
     * @param e1 the first element
     * @param e2 the second element
     * @param e3 the third element
     * @param e4 the fourth element
     * @param e5 the fifth element
     * @param e6 the sixth element
     * @param e7 the seventh element
     * @param e8 the eighth element
     * @param e9 the ninth element
     * @param e10 the tenth element
     * @return a {@code Set} containing the specified elements
     * @throws IllegalArgumentException if there are any duplicate elements
     * @throws NullPointerException if an element is {@code null}
     *
     * @since 9
     */
    //static <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10)
    // {
    //    return new ImmutableCollections.SetN<>(e1, e2, e3, e4, e5,
    //            e6, e7, e8, e9, e10);
    //}

    /**
     * Returns an unmodifiable set containing an arbitrary number of elements.
     * See <a href="#unmodifiable">Unmodifiable Sets</a> for details.
     *
     * @apiNote
     * This method also accepts a single array as an argument. The element type of
     * the resulting set will be the component type of the array, and the size of
     * the set will be equal to the length of the array. To create a set with
     * a single element that is an array, do the following:
     *
     * <pre>{@code
     *     String[] array = ... ;
     *     Set<String[]> list = Set.<String[]>of(array);
     * }</pre>
     *
     * This will cause the {@link Set#of(Object) Set.of(E)} method
     * to be invoked instead.
     *
     * @param <E> the {@code Set}'s element type
     * @param elements the elements to be contained in the set
     * @return a {@code Set} containing the specified elements
     * @throws IllegalArgumentException if there are any duplicate elements
     * @throws NullPointerException if an element is {@code null} or if the array is {@code null}
     *
     * @since 9
     */

    /*
    @SafeVarargs
    @SuppressWarnings("varargs")

    static <E> Set<E> of(E... elements) {
        switch (elements.length) { // implicit null check of elements
            case 0:
                return ImmutableCollections.emptySet();
            case 1:
                return new ImmutableCollections.Set12<>(elements[0]);
            case 2:
                return new ImmutableCollections.Set12<>(elements[0], elements[1]);
            default:
                return new ImmutableCollections.SetN<>(elements);
        }
    }
    */

    /**
     * Returns an <a href="#unmodifiable">unmodifiable Set</a> containing the elements
     * of the given Collection. The given Collection must not be null, and it must not
     * contain any null elements. If the given Collection contains duplicate elements,
     * an arbitrary element of the duplicates is preserved. If the given Collection is
     * subsequently modified, the returned Set will not reflect such modifications.
     *
     * @implNote
     * If the given Collection is an <a href="#unmodifiable">unmodifiable Set</a>,
     * calling copyOf will generally not create a copy.
     *
     * @param <E> the {@code Set}'s element type
     * @param coll a {@code Collection} from which elements are drawn, must be non-null
     * @return a {@code Set} containing the elements of the given {@code Collection}
     * @throws NullPointerException if coll is null, or if it contains any nulls
     * @since 10
     */
    /*
    @SuppressWarnings("unchecked")
    static <E> Set<E> copyOf(Collection<? extends E> coll) {
        if (coll instanceof ImmutableCollections.AbstractImmutableSet) {
            return (Set<E>)coll;
        } else {
            return (Set<E>)Set.of(new HashSet<>(coll).toArray());
        }
    }
    */

}
