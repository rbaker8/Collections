package com.richabaker.collections.maps;

import com.richabaker.collections.sets.RichAbstractSet;
import com.richabaker.collections.sets.RichSet;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Consumer;

public class RichHashMap<K, V> extends RichAbstractMap<K, V> implements RichMap<K, V>
{

    /**
     * The default initial capacity - MUST be a power of two.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load factor used when none specified in constructor.
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * The bin count threshold for using a tree rather than list for a
     * bin.  Bins are converted to trees when adding an element to a
     * bin with at least this many nodes. The value must be greater
     * than 2 and should be at least 8 to mesh with assumptions in
     * tree removal about conversion back to plain bins upon
     * shrinkage.
     */
    static final int TREEIFY_THRESHOLD = 8;

    /**
     * The bin count threshold for untreeifying a (split) bin during a
     * resize operation. Should be less than TREEIFY_THRESHOLD, and at
     * most 6 to mesh with shrinkage detection under removal.
     */
    static final int UNTREEIFY_THRESHOLD = 6;

    /**
     * The smallest table capacity for which bins may be treeified.
     * (Otherwise the table is resized if too many nodes in a bin.)
     * Should be at least 4 * TREEIFY_THRESHOLD to avoid conflicts
     * between resizing and treeification thresholds.
     */
    static final int MIN_TREEIFY_CAPACITY = 64;

    static class RichNode<K,V> implements RichHashMap.RichEntry<K,V>
    {
        final int hash;
        final K key;
        V value;
        RichHashMap.RichNode<K,V> next;

        RichNode(int hash, K key, V value, RichHashMap.RichNode<K,V> next)
        {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode()
        {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue)
        {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o)
        {
            if (o == this)
                return true;
            if (o instanceof RichMap.RichEntry)
            {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

    static final int hash(Object key)
    {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public RichHashMap(int initialCapacity, float loadFactor)
    {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    /**
     * Constructs an empty {@code HashMap} with the specified initial
     * capacity and the default load factor (0.75).
     *
     * @param  initialCapacity the initial capacity.
     * @throws IllegalArgumentException if the initial capacity is negative.
     */
    public RichHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs an empty {@code HashMap} with the default initial capacity
     * (16) and the default load factor (0.75).
     */
    public RichHashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }

    /**
     * Constructs a new {@code HashMap} with the same mappings as the
     * specified {@code Map}.  The {@code HashMap} is created with
     * default load factor (0.75) and an initial capacity sufficient to
     * hold the mappings in the specified {@code Map}.
     *
     * @param   m the map whose mappings are to be placed in this map
     * @throws  NullPointerException if the specified map is null
     */
    public RichHashMap(RichHashMap<? extends K, ? extends V> m)
    {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(m, false);
    }

    /**
     * Implements Map.putAll and Map constructor.
     *
     * @param m the map
     * @param evict false when initially constructing this map, else
     * true (relayed to method afterNodeInsertion).
     */
    final void putMapEntries(RichMap<? extends K, ? extends V> m, boolean evict)
    {
        int s = m.size();
        if (s > 0) {
            if (table == null) { // pre-size
                float ft = ((float)s / loadFactor) + 1.0F;
                int t = ((ft < (float)MAXIMUM_CAPACITY) ?
                        (int)ft : MAXIMUM_CAPACITY);
                if (t > threshold)
                    threshold = tableSizeFor(t);
            }
            else if (s > threshold)
                resize();
            // TODO: Fix compile error
            //for (RichMap.RichEntry<? extends K, ? extends V> e : m.entrySet()) {
            //    K key = e.getKey();
            //    V value = e.getValue();
            //    putVal(hash(key), key, value, false, evict);
            //}
        }
    }

    /**
     * Returns x's Class if it is of the form "class C implements
     * Comparable<C>", else null.
     */
    static Class<?> comparableClassFor(Object x)
    {
        if (x instanceof Comparable) {
            Class<?> c; Type[] ts, as; ParameterizedType p;
            if ((c = x.getClass()) == String.class) // bypass checks
                return c;
            if ((ts = c.getGenericInterfaces()) != null) {
                for (Type t : ts) {
                    if ((t instanceof ParameterizedType) &&
                            ((p = (ParameterizedType) t).getRawType() ==
                                    Comparable.class) &&
                            (as = p.getActualTypeArguments()) != null &&
                            as.length == 1 && as[0] == c) // type arg is c
                        return c;
                }
            }
        }
        return null;
    }

    /**
     * Returns k.compareTo(x) if x matches kc (k's screened comparable
     * class), else 0.
     */
    @SuppressWarnings({"rawtypes","unchecked"}) // for cast to Comparable
    static int compareComparables(Class<?> kc, Object k, Object x)
    {
        return (x == null || x.getClass() != kc ? 0 :
                ((Comparable)k).compareTo(x));
    }

    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap)
    {
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /* ---------------- Fields -------------- */

    /**
     * The table, initialized on first use, and resized as
     * necessary. When allocated, length is always a power of two.
     * (We also tolerate length zero in some operations to allow
     * bootstrapping mechanics that are currently not needed.)
     */
    transient RichHashMap.RichNode<K,V>[] table;

    /**
     * Holds cached entrySet(). Note that AbstractMap fields are used
     * for keySet() and values().
     */
    transient RichSet<RichEntry<K,V>> entrySet;

    /**
     * The number of key-value mappings contained in this map.
     */
    transient int size;

    /**
     * The number of times this HashMap has been structurally modified
     * Structural modifications are those that change the number of mappings in
     * the HashMap or otherwise modify its internal structure (e.g.,
     * rehash).  This field is used to make iterators on Collection-views of
     * the HashMap fail-fast.  (See ConcurrentModificationException).
     */
    transient int modCount;

    /**
     * The next size value at which to resize (capacity * load factor).
     *
     * @serial
     */
    // (The javadoc description is true upon serialization.
    // Additionally, if the table array has not been allocated, this
    // field holds the initial array capacity, or zero signifying
    // DEFAULT_INITIAL_CAPACITY.)
    int threshold;

    /**
     * The load factor for the hash table.
     *
     * @serial
     */
    final float loadFactor;

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    public int size() {
        return size;
    }

    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     */
    public void clear()
    {
        RichHashMap.RichNode<K,V>[] tab;
        modCount++;
        if ((tab = table) != null && size > 0)
        {
            size = 0;
            for (int i = 0; i < tab.length; ++i)
                tab[i] = null;
        }
    }
    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if this map contains no key-value mappings
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * <p>A return value of {@code null} does not <i>necessarily</i>
     * indicate that the map contains no mapping for the key; it's also
     * possible that the map explicitly maps the key to {@code null}.
     * The {@link #containsKey containsKey} operation may be used to
     * distinguish these two cases.
     *
     * @see #put(Object, Object)
     */
    public V get(Object key)
    {
        RichHashMap.RichNode<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

    /**
     * Implements Map.get and related methods.
     *
     * @param hash hash for key
     * @param key the key
     * @return the node, or null if none
     */
    final RichHashMap.RichNode<K,V> getNode(int hash, Object key)
    {
        RichHashMap.RichNode<K,V>[] tab; RichHashMap.RichNode<K,V> first, e; int n; K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (first = tab[(n - 1) & hash]) != null)
        {
            if (first.hash == hash && // always check first node
                    ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null)
            {
                if (first instanceof RichHashMap.RichTreeNode)
                    return ((RichHashMap.RichTreeNode<K,V>)first).getTreeNode(hash, key);
                do
                {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }


    // Create a regular (non-tree) node
    RichHashMap.RichNode<K,V> newNode(int hash, K key, V value, RichHashMap.RichNode<K,V> next)
    {
        return new RichHashMap.RichNode<>(hash, key, value, next);
    }

    // For conversion from TreeNodes to plain nodes
    RichHashMap.RichNode<K,V> replacementNode(RichHashMap.RichNode<K,V> p, RichHashMap.RichNode<K,V> next)
    {
        return new RichHashMap.RichNode<>(p.hash, p.key, p.value, next);
    }

    // Create a tree bin node
    RichHashMap.RichTreeNode<K,V> newTreeNode(int hash, K key, V value, RichHashMap.RichNode<K,V> next)
    {
        return new RichHashMap.RichTreeNode<>(hash, key, value, next);
    }

    // For treeifyBin
    RichHashMap.RichTreeNode<K,V> replacementTreeNode(RichHashMap.RichNode<K,V> p, RichHashMap.RichNode<K,V> next)
    {
        return new RichHashMap.RichTreeNode<>(p.hash, p.key, p.value, next);
    }

    /**
     * Reset to initial default state.  Called by clone and readObject.
     */
    void reinitialize()
    {
        table = null;
        entrySet = null;
        keySet = null;
        values = null;
        modCount = 0;
        threshold = 0;
        size = 0;
    }

    /**
     * Returns {@code true} if this map contains a mapping for the
     * specified key.
     *
     * @param   key   The key whose presence in this map is to be tested
     * @return {@code true} if this map contains a mapping for the specified
     * key.
     */
    public boolean containsKey(Object key)
    {
        return getNode(hash(key), key) != null;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     *         (A {@code null} return can also indicate that the map
     *         previously associated {@code null} with {@code key}.)
     */
    public V put(K key, V value)
    {
        return putVal(hash(key), key, value, false, true);
    }

    /**
     * Implements Map.put and related methods.
     *
     * @param hash hash for key
     * @param key the key
     * @param value the value to put
     * @param onlyIfAbsent if true, don't change existing value
     * @param evict if false, the table is in creation mode.
     * @return previous value, or null if none
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict)
    {
        RichHashMap.RichNode<K,V>[] tab; RichHashMap.RichNode<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            RichHashMap.RichNode<K,V> e; K k;
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof RichHashMap.RichTreeNode)
                e = ((RichHashMap.RichTreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                         p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param  key key whose mapping is to be removed from the map
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     *         (A {@code null} return can also indicate that the map
     *         previously associated {@code null} with {@code key}.)
     */
    public V remove(Object key)
    {
        RichHashMap.RichNode<K,V> e;
        return (e = removeNode(hash(key), key, null, false, true)) == null ?
                null : e.value;
    }

    /**
     * Implements Map.remove and related methods.
     *
     * @param hash hash for key
     * @param key the key
     * @param value the value to match if matchValue, else ignored
     * @param matchValue if true only remove if value is equal
     * @param movable if false do not move other nodes while removing
     * @return the node, or null if none
     */
    final RichHashMap.RichNode<K,V> removeNode(int hash, Object key, Object value,
                                       boolean matchValue, boolean movable)
    {
        RichHashMap.RichNode<K,V>[] tab; RichHashMap.RichNode<K,V> p; int n, index;
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (p = tab[index = (n - 1) & hash]) != null) {
            RichHashMap.RichNode<K,V> node = null, e; K k; V v;
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                node = p;
            else if ((e = p.next) != null)
            {
                if (p instanceof RichHashMap.RichTreeNode)
                    node = ((RichHashMap.RichTreeNode<K,V>)p).getTreeNode(hash, key);
                else {
                    do {
                        if (e.hash == hash &&
                                ((k = e.key) == key ||
                                        (key != null && key.equals(k)))) {
                            node = e;
                            break;
                        }
                        p = e;
                    } while ((e = e.next) != null);
                }
            }
            if (node != null && (!matchValue || (v = node.value) == value ||
                    (value != null && value.equals(v))))
            {
                if (node instanceof RichHashMap.RichTreeNode)
                    ((RichHashMap.RichTreeNode<K,V>)node).removeTreeNode(this, tab, movable);
                else if (node == p)
                    tab[index] = node.next;
                else
                    p.next = node.next;
                ++modCount;
                --size;
                afterNodeRemoval(node);
                return node;
            }
        }
        return null;
    }

    /**
     * Returns {@code true} if this map maps one or more keys to the
     * specified value.
     *
     * @param value value whose presence in this map is to be tested
     * @return {@code true} if this map maps one or more keys to the
     *         specified value
     */
    public boolean containsValue(Object value)
    {
        RichHashMap.RichNode<K,V>[] tab; V v;
        if ((tab = table) != null && size > 0) {
            for (RichHashMap.RichNode<K,V> e : tab)
            {
                for (; e != null; e = e.next)
                {
                    if ((v = e.value) == value ||
                            (value != null && value.equals(v)))
                        return true;
                }
            }
        }
        return false;
    }


    /**
     * Returns a {@link Set} view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own {@code remove} operation, or through the
     * {@code setValue} operation on a map entry returned by the
     * iterator) the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding
     * mapping from the map, via the {@code Iterator.remove},
     * {@code Set.remove}, {@code removeAll}, {@code retainAll} and
     * {@code clear} operations.  It does not support the
     * {@code add} or {@code addAll} operations.
     *
     * @return a set view of the mappings contained in this map
     */
    public RichSet<RichMap.RichEntry<K,V>> entrySet() {
        RichSet<RichMap.RichEntry<K,V>> es;
        return (es = entrySet) == null ? (entrySet = new RichHashMap.RichEntrySet()) : es;
    }

    final class RichEntrySet extends RichAbstractSet<RichMap.RichEntry<K,V>>
    {
        public final int size()                 { return size; }
        public final void clear()               { RichHashMap.this.clear(); }
        public final Iterator<RichMap.RichEntry<K,V>> iterator() {
            return new RichHashMap.RichEntryIterator();
        }
        public final boolean contains(Object o) {
            if (!(o instanceof RichMap.RichEntry))
                return false;
            RichMap.RichEntry<?,?> e = (RichMap.RichEntry<?,?>) o;
            Object key = e.getKey();
            RichHashMap.RichNode<K,V> candidate = getNode(hash(key), key);
            return candidate != null && candidate.equals(e);
        }
        public final boolean remove(Object o)
        {
            if (o instanceof RichMap.RichEntry) {
                RichMap.RichEntry<?,?> e = (RichMap.RichEntry<?,?>) o;
                Object key = e.getKey();
                Object value = e.getValue();
                return removeNode(hash(key), key, value, true, true) != null;
            }
            return false;
        }
        public final Spliterator<RichEntry<K, V>> spliterator()
        {
            return new RichHashMap.RichEntrySpliterator<K, V> (RichHashMap.this, 0, -1, 0, 0);
        }
        public final void forEach(Consumer<? super RichMap.RichEntry<K,V>> action)
        {
            RichHashMap.RichNode<K,V>[] tab;
            if (action == null)
                throw new NullPointerException();
            if (size > 0 && (tab = table) != null) {
                int mc = modCount;
                for (RichHashMap.RichNode<K,V> e : tab) {
                    for (; e != null; e = e.next)
                        action.accept(e);
                }
                if (modCount != mc)
                    throw new ConcurrentModificationException();
            }
        }
    }


    /**
     * Returns a {@link Set} view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own {@code remove} operation), the results of
     * the iteration are undefined.  The set supports element removal,
     * which removes the corresponding mapping from the map, via the
     * {@code Iterator.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retainAll}, and {@code clear}
     * operations.  It does not support the {@code add} or {@code addAll}
     * operations.
     *
     * @return a set view of the keys contained in this map
     */
    public RichSet<K> keySet()
    {
        RichSet<K> ks = keySet;
        if (ks == null)
        {
            ks = new RichHashMap.RichKeySet();
            keySet = ks;
        }
        return ks;
    }

    final class RichKeySet extends RichAbstractSet<K>
    {
        public final int size()
        {
            return size;
        }

        public final void clear()
        {
            RichHashMap.this.clear();
        }

        public final Iterator<K> iterator()
        {
            return new RichHashMap.RichKeyIterator();
        }

        public final boolean contains(Object o)
        {
            return containsKey(o);
        }

        public final boolean remove(Object key)
        {
            return removeNode(hash(key), key, null, false, true) != null;
        }

        public final Spliterator<K> spliterator()
        {
            return new RichHashMap.RichKeySpliterator<K, V>(RichHashMap.this, 0, -1, 0, 0);
        }

        public final void forEach(Consumer<? super K> action)
        {
            RichHashMap.RichNode<K, V>[] tab;
            if (action == null)
                throw new NullPointerException();
            if (size > 0 && (tab = table) != null)
            {
                int mc = modCount;
                for (RichHashMap.RichNode<K, V> e : tab)
                {
                    for (; e != null; e = e.next)
                        action.accept(e.key);
                }
                if (modCount != mc)
                    throw new ConcurrentModificationException();
            }
        }
    }


    /**
     * Initializes or doubles table size.  If null, allocates in
     * accord with initial capacity target held in field threshold.
     * Otherwise, because we are using power-of-two expansion, the
     * elements from each bin must either stay at same index, or move
     * with a power of two offset in the new table.
     *
     * @return the table
     */
    final RichHashMap.RichNode<K,V>[] resize()
    {
        RichHashMap.RichNode<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                    oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else
        {               // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0)
        {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                    (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
        RichHashMap.RichNode<K,V>[] newTab = (RichHashMap.RichNode<K,V>[])new RichHashMap.RichNode[newCap];
        table = newTab;
        if (oldTab != null)
        {
            for (int j = 0; j < oldCap; ++j) {
                RichHashMap.RichNode<K,V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    else if (e instanceof RichHashMap.RichTreeNode)
                        ((RichHashMap.RichTreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        RichHashMap.RichNode<K,V> loHead = null, loTail = null;
                        RichHashMap.RichNode<K,V> hiHead = null, hiTail = null;
                        RichHashMap.RichNode<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }

    /* ------------------------------------------------------------ */
    // iterators

    abstract class RichHashIterator
    {
        RichHashMap.RichNode<K,V> next;        // next entry to return
        RichHashMap.RichNode<K,V> current;     // current entry
        int expectedModCount;  // for fast-fail
        int index;             // current slot

        RichHashIterator() {
            expectedModCount = modCount;
            RichHashMap.RichNode<K,V>[] t = table;
            current = next = null;
            index = 0;
            if (t != null && size > 0) { // advance to first entry
                do {} while (index < t.length && (next = t[index++]) == null);
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final RichHashMap.RichNode<K,V> nextNode()
        {
            RichHashMap.RichNode<K,V>[] t;
            RichHashMap.RichNode<K,V> e = next;
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (e == null)
                throw new NoSuchElementException();
            if ((next = (current = e).next) == null && (t = table) != null) {
                do {} while (index < t.length && (next = t[index++]) == null);
            }
            return e;
        }

        public final void remove()
        {
            RichHashMap.RichNode<K,V> p = current;
            if (p == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            current = null;
            removeNode(p.hash, p.key, null, false, false);
            expectedModCount = modCount;
        }
    }

    final class RichKeyIterator extends RichHashMap.RichHashIterator
            implements Iterator<K> {
        public final K next() { return (K)nextNode().key; }
    }

    final class RichValueIterator extends RichHashMap.RichHashIterator
            implements Iterator<V> {
        public final V next() { return (V)nextNode().value; }
    }

    final class RichEntryIterator extends RichHashMap.RichHashIterator
            implements Iterator<RichMap.RichEntry<K,V>>
    {
        public final RichMap.RichEntry<K,V> next() { return nextNode(); }
    }

    /* ------------------------------------------------------------ */
    // spliterators

    static class RichHashMapSpliterator<K,V>
    {
        final RichHashMap<K,V> map;
        RichHashMap.RichNode<K,V> current;          // current node
        int index;                  // current index, modified on advance/split
        int fence;                  // one past last index
        int est;                    // size estimate
        int expectedModCount;       // for comodification checks

        RichHashMapSpliterator(RichHashMap<K,V> m, int origin,
                           int fence, int est,
                           int expectedModCount)
        {
            this.map = m;
            this.index = origin;
            this.fence = fence;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        final int getFence()
        { // initialize fence and size on first use
            int hi;
            if ((hi = fence) < 0) {
                RichHashMap<K,V> m = map;
                est = m.size;
                expectedModCount = m.modCount;
                RichHashMap.RichNode<K,V>[] tab = m.table;
                hi = fence = (tab == null) ? 0 : tab.length;
            }
            return hi;
        }

        public final long estimateSize()
        {
            getFence(); // force init
            return (long) est;
        }
    }

    static final class RichKeySpliterator<K,V>
            extends RichHashMap.RichHashMapSpliterator<K,V>
            implements Spliterator<K>
        {
        RichKeySpliterator(RichHashMap<K,V> m, int origin, int fence, int est,
                       int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public RichHashMap.RichKeySpliterator<K,V> trySplit()
        {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid || current != null) ? null :
                    new RichHashMap.RichKeySpliterator<>(map, lo, index = mid, est >>>= 1,
                            expectedModCount);
        }

        public void forEachRemaining(Consumer<? super K> action)
        {
            int i, hi, mc;
            if (action == null)
                throw new NullPointerException();
            RichHashMap<K,V> m = map;
            RichHashMap.RichNode<K,V>[] tab = m.table;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = (tab == null) ? 0 : tab.length;
            }
            else
                mc = expectedModCount;
            if (tab != null && tab.length >= hi &&
                    (i = index) >= 0 && (i < (index = hi) || current != null)) {
                RichHashMap.RichNode<K,V> p = current;
                current = null;
                do {
                    if (p == null)
                        p = tab[i++];
                    else {
                        action.accept(p.key);
                        p = p.next;
                    }
                } while (p != null || i < hi);
                if (m.modCount != mc)
                    throw new ConcurrentModificationException();
            }
        }

        public boolean tryAdvance(Consumer<? super K> action)
        {
            int hi;
            if (action == null)
                throw new NullPointerException();
            RichHashMap.RichNode<K,V>[] tab = map.table;
            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null)
                        current = tab[index++];
                    else {
                        K k = current.key;
                        current = current.next;
                        action.accept(k);
                        if (map.modCount != expectedModCount)
                            throw new ConcurrentModificationException();
                        return true;
                    }
                }
            }
            return false;
        }

        public int characteristics()
        {
            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0) |
                    Spliterator.DISTINCT;
        }
    }

    static final class RichValueSpliterator<K,V>
            extends RichHashMap.RichHashMapSpliterator<K,V>
            implements Spliterator<V>
    {
        RichValueSpliterator(RichHashMap<K,V> m, int origin, int fence, int est,
                         int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public RichHashMap.RichValueSpliterator<K,V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid || current != null) ? null :
                    new RichHashMap.RichValueSpliterator<>(map, lo, index = mid, est >>>= 1,
                            expectedModCount);
        }

        public void forEachRemaining(Consumer<? super V> action) {
            int i, hi, mc;
            if (action == null)
                throw new NullPointerException();
            RichHashMap<K,V> m = map;
            RichHashMap.RichNode<K,V>[] tab = m.table;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = (tab == null) ? 0 : tab.length;
            }
            else
                mc = expectedModCount;
            if (tab != null && tab.length >= hi &&
                    (i = index) >= 0 && (i < (index = hi) || current != null)) {
                RichHashMap.RichNode<K,V> p = current;
                current = null;
                do {
                    if (p == null)
                        p = tab[i++];
                    else {
                        action.accept(p.value);
                        p = p.next;
                    }
                } while (p != null || i < hi);
                if (m.modCount != mc)
                    throw new ConcurrentModificationException();
            }
        }

        public boolean tryAdvance(Consumer<? super V> action) {
            int hi;
            if (action == null)
                throw new NullPointerException();
            RichHashMap.RichNode<K,V>[] tab = map.table;
            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null)
                        current = tab[index++];
                    else {
                        V v = current.value;
                        current = current.next;
                        action.accept(v);
                        if (map.modCount != expectedModCount)
                            throw new ConcurrentModificationException();
                        return true;
                    }
                }
            }
            return false;
        }

        public int characteristics() {
            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0);
        }
    }

    static final class RichEntrySpliterator<K,V>
            extends RichHashMap.RichHashMapSpliterator<K,V>
            implements Spliterator<RichMap.RichEntry<K,V>> {
        RichEntrySpliterator(RichHashMap<K,V> m, int origin, int fence, int est,
                         int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public RichHashMap.RichEntrySpliterator<K,V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid || current != null) ? null :
                    new RichHashMap.RichEntrySpliterator<>(map, lo, index = mid, est >>>= 1,
                            expectedModCount);
        }

        public void forEachRemaining(Consumer<? super RichMap.RichEntry<K,V>> action) {
            int i, hi, mc;
            if (action == null)
                throw new NullPointerException();
            RichHashMap<K,V> m = map;
            RichHashMap.RichNode<K,V>[] tab = m.table;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = (tab == null) ? 0 : tab.length;
            }
            else
                mc = expectedModCount;
            if (tab != null && tab.length >= hi &&
                    (i = index) >= 0 && (i < (index = hi) || current != null)) {
                RichHashMap.RichNode<K,V> p = current;
                current = null;
                do {
                    if (p == null)
                        p = tab[i++];
                    else {
                        action.accept(p);
                        p = p.next;
                    }
                } while (p != null || i < hi);
                if (m.modCount != mc)
                    throw new ConcurrentModificationException();
            }
        }

        public boolean tryAdvance(Consumer<? super RichMap.RichEntry<K,V>> action) {
            int hi;
            if (action == null)
                throw new NullPointerException();
            RichHashMap.RichNode<K,V>[] tab = map.table;
            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null)
                        current = tab[index++];
                    else {
                        RichHashMap.RichNode<K,V> e = current;
                        current = current.next;
                        action.accept(e);
                        if (map.modCount != expectedModCount)
                            throw new ConcurrentModificationException();
                        return true;
                    }
                }
            }
            return false;
        }

        public int characteristics() {
            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0) |
                    Spliterator.DISTINCT;
        }
    }

    /**
     * Replaces all linked nodes in bin at index for given hash unless
     * table is too small, in which case resizes instead.
     */
    final void treeifyBin(RichHashMap.RichNode<K,V>[] tab, int hash) {
        int n, index; RichHashMap.RichNode<K,V> e;
        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
            resize();
        else if ((e = tab[index = (n - 1) & hash]) != null) {
            RichHashMap.RichTreeNode<K,V> hd = null, tl = null;
            do {
                RichHashMap.RichTreeNode<K,V> p = replacementTreeNode(e, null);
                if (tl == null)
                    hd = p;
                else {
                    p.prev = tl;
                    tl.next = p;
                }
                tl = p;
            } while ((e = e.next) != null);
            if ((tab[index] = hd) != null)
                hd.treeify(tab);
        }
    }

    // Callbacks to allow LinkedHashMap post-actions
    void afterNodeAccess(RichHashMap.RichNode<K,V> p) { }
    void afterNodeInsertion(boolean evict) { }
    void afterNodeRemoval(RichHashMap.RichNode<K,V> p) { }


    /* ------------------------------------------------------------ */
        // Tree bins

        /**
         * Entry for Tree bins. Extends LinkedHashMap.Entry (which in turn
         * extends Node) so can be used as extension of either regular or
         * linked node.
         */
        static class RichTreeNode<K, V> extends RichLinkedHashMap.RichEntry<K, V>
        {
            RichHashMap.RichTreeNode<K, V> parent;  // red-black tree links
            RichHashMap.RichTreeNode<K, V> left;
            RichHashMap.RichTreeNode<K, V> right;
            RichHashMap.RichTreeNode<K, V> prev;    // needed to unlink next upon deletion
            boolean red;

            RichTreeNode(int hash, K key, V val, RichHashMap.RichNode<K, V> next)
            {
                super(hash, key, val, next);
            }

            /**
             * Returns root of tree containing this node.
             */
            final RichHashMap.RichTreeNode<K, V> root()
            {
                for (RichHashMap.RichTreeNode<K, V> r = this, p; ; )
                {
                    if ((p = r.parent) == null)
                        return r;
                    r = p;
                }
            }

            /**
             * Ensures that the given root is the first node of its bin.
             */
            static <K, V> void moveRootToFront(RichHashMap.RichNode<K, V>[] tab, RichHashMap.RichTreeNode<K, V> root)
            {
                int n;
                if (root != null && tab != null && (n = tab.length) > 0)
                {
                    int index = (n - 1) & root.hash;
                    RichHashMap.RichTreeNode<K, V> first = (RichHashMap.RichTreeNode<K, V>) tab[index];
                    if (root != first) {
                        RichHashMap.RichNode<K, V> rn;
                        tab[index] = root;
                        RichHashMap.RichTreeNode<K, V> rp = root.prev;
                        if ((rn = root.next) != null)
                            ((RichHashMap.RichTreeNode<K, V>) rn).prev = rp;
                        if (rp != null)
                            rp.next = rn;
                        if (first != null)
                            first.prev = root;
                        root.next = first;
                        root.prev = null;
                    }
                    assert checkInvariants(root);
                }
            }

            /**
             * Finds the node starting at root p with the given hash and key.
             * The kc argument caches comparableClassFor(key) upon first use
             * comparing keys.
             */
            final RichHashMap.RichTreeNode<K, V> find(int h, Object k, Class<?> kc)
            {
                RichHashMap.RichTreeNode<K, V> p = this;
                do
                {
                    int ph, dir;
                    K pk;
                    RichHashMap.RichTreeNode<K, V> pl = p.left, pr = p.right, q;
                    if ((ph = p.hash) > h)
                        p = pl;
                    else if (ph < h)
                        p = pr;
                    else if ((pk = p.key) == k || (k != null && k.equals(pk)))
                        return p;
                    else if (pl == null)
                        p = pr;
                    else if (pr == null)
                        p = pl;
                    else if ((kc != null ||
                            (kc = comparableClassFor(k)) != null) &&
                            (dir = compareComparables(kc, k, pk)) != 0)
                        p = (dir < 0) ? pl : pr;
                    else if ((q = pr.find(h, k, kc)) != null)
                        return q;
                    else
                        p = pl;
                } while (p != null);
                return null;
            }

            /**
             * Calls find for root node.
             */
            final RichHashMap.RichTreeNode<K, V> getTreeNode(int h, Object k)
            {
                return ((parent != null) ? root() : this).find(h, k, null);
            }

            /**
             * Tie-breaking utility for ordering insertions when equal
             * hashCodes and non-comparable. We don't require a total
             * order, just a consistent insertion rule to maintain
             * equivalence across rebalancings. Tie-breaking further than
             * necessary simplifies testing a bit.
             */
            static int tieBreakOrder(Object a, Object b)
            {
                int d;
                if (a == null || b == null ||
                        (d = a.getClass().getName().
                                compareTo(b.getClass().getName())) == 0)
                    d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
                            -1 : 1);
                return d;
            }

            /**
             * Forms tree of the nodes linked from this node.
             */
            final void treeify(RichHashMap.RichNode<K, V>[] tab)
            {
                RichHashMap.RichTreeNode<K, V> root = null;
                for (RichHashMap.RichTreeNode<K, V> x = this, next; x != null; x = next)
                {
                    next = (RichHashMap.RichTreeNode<K, V>) x.next;
                    x.left = x.right = null;
                    if (root == null) {
                        x.parent = null;
                        x.red = false;
                        root = x;
                    } else {
                        K k = x.key;
                        int h = x.hash;
                        Class<?> kc = null;
                        for (RichHashMap.RichTreeNode<K, V> p = root; ; )
                        {
                            int dir, ph;
                            K pk = p.key;
                            if ((ph = p.hash) > h)
                                dir = -1;
                            else if (ph < h)
                                dir = 1;
                            else if ((kc == null &&
                                    (kc = comparableClassFor(k)) == null) ||
                                    (dir = compareComparables(kc, k, pk)) == 0)
                                dir = tieBreakOrder(k, pk);

                            RichHashMap.RichTreeNode<K, V> xp = p;
                            if ((p = (dir <= 0) ? p.left : p.right) == null)
                            {
                                x.parent = xp;
                                if (dir <= 0)
                                    xp.left = x;
                                else
                                    xp.right = x;
                                root = balanceInsertion(root, x);
                                break;
                            }
                        }
                    }
                }
                moveRootToFront(tab, root);
            }

            /**
             * Returns a list of non-TreeNodes replacing those linked from
             * this node.
             */
            final RichHashMap.RichNode<K, V> untreeify(RichHashMap<K, V> map)
            {
                RichHashMap.RichNode<K, V> hd = null, tl = null;
                for (RichHashMap.RichNode<K, V> q = this; q != null; q = q.next) {
                    RichHashMap.RichNode<K, V> p = map.replacementNode(q, null);
                    if (tl == null)
                        hd = p;
                    else
                        tl.next = p;
                    tl = p;
                }
                return hd;
            }

            /**
             * Tree version of putVal.
             */
            final RichHashMap.RichTreeNode<K, V> putTreeVal(RichHashMap<K, V> map, RichHashMap.RichNode<K, V>[] tab,
                                                        int h, K k, V v)
            {
                Class<?> kc = null;
                boolean searched = false;
                RichHashMap.RichTreeNode<K, V> root = (parent != null) ? root() : this;
                for (RichTreeNode<K, V> p = root; ; )
                {
                    int dir, ph;
                    K pk;
                    if ((ph = p.hash) > h)
                        dir = -1;
                    else if (ph < h)
                        dir = 1;
                    else if ((pk = p.key) == k || (k != null && k.equals(pk)))
                        return p;
                    else if ((kc == null &&
                            (kc = comparableClassFor(k)) == null) ||
                            (dir = compareComparables(kc, k, pk)) == 0) {
                        if (!searched) {
                            RichHashMap.RichTreeNode<K, V> q, ch;
                            searched = true;
                            if (((ch = p.left) != null &&
                                    (q = ch.find(h, k, kc)) != null) ||
                                    ((ch = p.right) != null &&
                                            (q = ch.find(h, k, kc)) != null))
                                return q;
                        }
                        dir = tieBreakOrder(k, pk);
                    }

                    RichHashMap.RichTreeNode<K, V> xp = p;
                    if ((p = (dir <= 0) ? p.left : p.right) == null) {
                        RichHashMap.RichNode<K, V> xpn = xp.next;
                        RichHashMap.RichTreeNode<K, V> x = map.newTreeNode(h, k, v, xpn);
                        if (dir <= 0)
                            xp.left = x;
                        else
                            xp.right = x;
                        xp.next = x;
                        x.parent = x.prev = xp;
                        if (xpn != null)
                            ((RichHashMap.RichTreeNode<K, V>) xpn).prev = x;
                        moveRootToFront(tab, balanceInsertion(root, x));
                        return null;
                    }
                }
            }

            /**
             * Removes the given node, that must be present before this call.
             * This is messier than typical red-black deletion code because we
             * cannot swap the contents of an interior node with a leaf
             * successor that is pinned by "next" pointers that are accessible
             * independently during traversal. So instead we swap the tree
             * linkages. If the current tree appears to have too few nodes,
             * the bin is converted back to a plain bin. (The test triggers
             * somewhere between 2 and 6 nodes, depending on tree structure).
             */
            final void removeTreeNode(RichHashMap<K, V> map, RichHashMap.RichNode<K, V>[] tab,
                                      boolean movable)
            {
                int n;
                if (tab == null || (n = tab.length) == 0)
                    return;
                int index = (n - 1) & hash;
                RichHashMap.RichTreeNode<K, V> first = (RichHashMap.RichTreeNode<K, V>) tab[index], root = first, rl;
                RichHashMap.RichTreeNode<K, V> succ = (RichHashMap.RichTreeNode<K, V>) next, pred = prev;
                if (pred == null)
                    tab[index] = first = succ;
                else
                    pred.next = succ;
                if (succ != null)
                    succ.prev = pred;
                if (first == null)
                    return;
                if (root.parent != null)
                    root = root.root();
                if (root == null
                        || (movable
                        && (root.right == null
                        || (rl = root.left) == null
                        || rl.left == null))) {
                    tab[index] = first.untreeify(map);  // too small
                    return;
                }
                RichHashMap.RichTreeNode<K, V> p = this, pl = left, pr = right, replacement;
                if (pl != null && pr != null) {
                    RichHashMap.RichTreeNode<K, V> s = pr, sl;
                    while ((sl = s.left) != null) // find successor
                        s = sl;
                    boolean c = s.red;
                    s.red = p.red;
                    p.red = c; // swap colors
                    RichHashMap.RichTreeNode<K, V> sr = s.right;
                    RichHashMap.RichTreeNode<K, V> pp = p.parent;
                    if (s == pr) { // p was s's direct parent
                        p.parent = s;
                        s.right = p;
                    } else {
                        RichHashMap.RichTreeNode<K, V> sp = s.parent;
                        if ((p.parent = sp) != null)
                        {
                            if (s == sp.left)
                                sp.left = p;
                            else
                                sp.right = p;
                        }
                        if ((s.right = pr) != null)
                            pr.parent = s;
                    }
                    p.left = null;
                    if ((p.right = sr) != null)
                        sr.parent = p;
                    if ((s.left = pl) != null)
                        pl.parent = s;
                    if ((s.parent = pp) == null)
                        root = s;
                    else if (p == pp.left)
                        pp.left = s;
                    else
                        pp.right = s;
                    if (sr != null)
                        replacement = sr;
                    else
                        replacement = p;
                } else if (pl != null)
                    replacement = pl;
                else if (pr != null)
                    replacement = pr;
                else
                    replacement = p;
                if (replacement != p) {
                    RichHashMap.RichTreeNode<K, V> pp = replacement.parent = p.parent;
                    if (pp == null)
                        (root = replacement).red = false;
                    else if (p == pp.left)
                        pp.left = replacement;
                    else
                        pp.right = replacement;
                    p.left = p.right = p.parent = null;
                }

                RichHashMap.RichTreeNode<K, V> r = p.red ? root : balanceDeletion(root, replacement);

                if (replacement == p)
                {  // detach
                    RichHashMap.RichTreeNode<K, V> pp = p.parent;
                    p.parent = null;
                    if (pp != null) {
                        if (p == pp.left)
                            pp.left = null;
                        else if (p == pp.right)
                            pp.right = null;
                    }
                }
                if (movable)
                    moveRootToFront(tab, r);
            }

            /**
             * Splits nodes in a tree bin into lower and upper tree bins,
             * or untreeifies if now too small. Called only from resize;
             * see above discussion about split bits and indices.
             *
             * @param map   the map
             * @param tab   the table for recording bin heads
             * @param index the index of the table being split
             * @param bit   the bit of hash to split on
             */
            final void split(RichHashMap<K, V> map, RichHashMap.RichNode<K, V>[] tab, int index, int bit)
            {
                RichHashMap.RichTreeNode<K, V> b = this;
                // Relink into lo and hi lists, preserving order
                RichHashMap.RichTreeNode<K, V> loHead = null, loTail = null;
                RichHashMap.RichTreeNode<K, V> hiHead = null, hiTail = null;
                int lc = 0, hc = 0;
                for (RichHashMap.RichTreeNode<K, V> e = b, next; e != null; e = next) {
                    next = (RichHashMap.RichTreeNode<K, V>) e.next;
                    e.next = null;
                    if ((e.hash & bit) == 0) {
                        if ((e.prev = loTail) == null)
                            loHead = e;
                        else
                            loTail.next = e;
                        loTail = e;
                        ++lc;
                    } else {
                        if ((e.prev = hiTail) == null)
                            hiHead = e;
                        else
                            hiTail.next = e;
                        hiTail = e;
                        ++hc;
                    }
                }

                if (loHead != null)
                {
                    if (lc <= UNTREEIFY_THRESHOLD)
                        tab[index] = loHead.untreeify(map);
                    else {
                        tab[index] = loHead;
                        if (hiHead != null) // (else is already treeified)
                            loHead.treeify(tab);
                    }
                }
                if (hiHead != null)
                {
                    if (hc <= UNTREEIFY_THRESHOLD)
                        tab[index + bit] = hiHead.untreeify(map);
                    else
                    {
                        tab[index + bit] = hiHead;
                        if (loHead != null)
                            hiHead.treeify(tab);
                    }
                }
            }
            /* ------------------------------------------------------------ */
            // Red-black tree methods, all adapted from CLR

            static <K,V> RichHashMap.RichTreeNode<K,V> rotateLeft(RichHashMap.RichTreeNode<K,V> root,
                                                                  RichHashMap.RichTreeNode<K,V> p) {
                RichHashMap.RichTreeNode<K,V> r, pp, rl;
                if (p != null && (r = p.right) != null) {
                    if ((rl = p.right = r.left) != null)
                        rl.parent = p;
                    if ((pp = r.parent = p.parent) == null)
                        (root = r).red = false;
                    else if (pp.left == p)
                        pp.left = r;
                    else
                        pp.right = r;
                    r.left = p;
                    p.parent = r;
                }
                return root;
            }

            static <K,V> RichHashMap.RichTreeNode<K,V> rotateRight(RichHashMap.RichTreeNode<K,V> root,
                                                                   RichHashMap.RichTreeNode<K,V> p) {
                RichHashMap.RichTreeNode<K,V> l, pp, lr;
                if (p != null && (l = p.left) != null) {
                    if ((lr = p.left = l.right) != null)
                        lr.parent = p;
                    if ((pp = l.parent = p.parent) == null)
                        (root = l).red = false;
                    else if (pp.right == p)
                        pp.right = l;
                    else
                        pp.left = l;
                    l.right = p;
                    p.parent = l;
                }
                return root;
            }

            static <K,V> RichHashMap.RichTreeNode<K,V> balanceInsertion(RichHashMap.RichTreeNode<K,V> root,
                                                                        RichHashMap.RichTreeNode<K,V> x) {
                x.red = true;
                for (RichHashMap.RichTreeNode<K,V> xp, xpp, xppl, xppr;;) {
                    if ((xp = x.parent) == null) {
                        x.red = false;
                        return x;
                    }
                    else if (!xp.red || (xpp = xp.parent) == null)
                        return root;
                    if (xp == (xppl = xpp.left)) {
                        if ((xppr = xpp.right) != null && xppr.red) {
                            xppr.red = false;
                            xp.red = false;
                            xpp.red = true;
                            x = xpp;
                        }
                        else {
                            if (x == xp.right) {
                                root = rotateLeft(root, x = xp);
                                xpp = (xp = x.parent) == null ? null : xp.parent;
                            }
                            if (xp != null) {
                                xp.red = false;
                                if (xpp != null) {
                                    xpp.red = true;
                                    root = rotateRight(root, xpp);
                                }
                            }
                        }
                    }
                    else {
                        if (xppl != null && xppl.red) {
                            xppl.red = false;
                            xp.red = false;
                            xpp.red = true;
                            x = xpp;
                        }
                        else {
                            if (x == xp.left) {
                                root = rotateRight(root, x = xp);
                                xpp = (xp = x.parent) == null ? null : xp.parent;
                            }
                            if (xp != null) {
                                xp.red = false;
                                if (xpp != null) {
                                    xpp.red = true;
                                    root = rotateLeft(root, xpp);
                                }
                            }
                        }
                    }
                }
            }

            static <K,V> RichHashMap.RichTreeNode<K,V> balanceDeletion(RichHashMap.RichTreeNode<K,V> root,
                                                                       RichHashMap.RichTreeNode<K,V> x) {
                for (RichHashMap.RichTreeNode<K,V> xp, xpl, xpr;;) {
                    if (x == null || x == root)
                        return root;
                    else if ((xp = x.parent) == null) {
                        x.red = false;
                        return x;
                    }
                    else if (x.red) {
                        x.red = false;
                        return root;
                    }
                    else if ((xpl = xp.left) == x) {
                        if ((xpr = xp.right) != null && xpr.red) {
                            xpr.red = false;
                            xp.red = true;
                            root = rotateLeft(root, xp);
                            xpr = (xp = x.parent) == null ? null : xp.right;
                        }
                        if (xpr == null)
                            x = xp;
                        else {
                            RichHashMap.RichTreeNode<K,V> sl = xpr.left, sr = xpr.right;
                            if ((sr == null || !sr.red) &&
                                    (sl == null || !sl.red)) {
                                xpr.red = true;
                                x = xp;
                            }
                            else {
                                if (sr == null || !sr.red) {
                                    if (sl != null)
                                        sl.red = false;
                                    xpr.red = true;
                                    root = rotateRight(root, xpr);
                                    xpr = (xp = x.parent) == null ?
                                            null : xp.right;
                                }
                                if (xpr != null) {
                                    xpr.red = (xp == null) ? false : xp.red;
                                    if ((sr = xpr.right) != null)
                                        sr.red = false;
                                }
                                if (xp != null) {
                                    xp.red = false;
                                    root = rotateLeft(root, xp);
                                }
                                x = root;
                            }
                        }
                    }
                    else { // symmetric
                        if (xpl != null && xpl.red) {
                            xpl.red = false;
                            xp.red = true;
                            root = rotateRight(root, xp);
                            xpl = (xp = x.parent) == null ? null : xp.left;
                        }
                        if (xpl == null)
                            x = xp;
                        else {
                            RichHashMap.RichTreeNode<K,V> sl = xpl.left, sr = xpl.right;
                            if ((sl == null || !sl.red) &&
                                    (sr == null || !sr.red)) {
                                xpl.red = true;
                                x = xp;
                            }
                            else {
                                if (sl == null || !sl.red) {
                                    if (sr != null)
                                        sr.red = false;
                                    xpl.red = true;
                                    root = rotateLeft(root, xpl);
                                    xpl = (xp = x.parent) == null ?
                                            null : xp.left;
                                }
                                if (xpl != null) {
                                    xpl.red = (xp == null) ? false : xp.red;
                                    if ((sl = xpl.left) != null)
                                        sl.red = false;
                                }
                                if (xp != null) {
                                    xp.red = false;
                                    root = rotateRight(root, xp);
                                }
                                x = root;
                            }
                        }
                    }
                }
            }

            /**
             * Recursive invariant check
             */
            static <K,V> boolean checkInvariants(RichHashMap.RichTreeNode<K,V> t) {
                RichHashMap.RichTreeNode<K,V> tp = t.parent, tl = t.left, tr = t.right,
                        tb = t.prev, tn = (RichHashMap.RichTreeNode<K,V>)t.next;
                if (tb != null && tb.next != t)
                    return false;
                if (tn != null && tn.prev != t)
                    return false;
                if (tp != null && t != tp.left && t != tp.right)
                    return false;
                if (tl != null && (tl.parent != t || tl.hash > t.hash))
                    return false;
                if (tr != null && (tr.parent != t || tr.hash < t.hash))
                    return false;
                if (t.red && tl != null && tl.red && tr != null && tr.red)
                    return false;
                if (tl != null && !checkInvariants(tl))
                    return false;
                if (tr != null && !checkInvariants(tr))
                    return false;
                return true;
            }
        }
    }