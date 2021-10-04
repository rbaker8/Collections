package com.richabaker.collections.maps;


import com.richabaker.collections.sets.RichAbstractSet;
import com.richabaker.collections.sets.RichSet;

import java.util.*;

public abstract class RichAbstractMap<K,V> implements RichMap<K,V>
{

    /**
     * An Entry maintaining a key and a value.  The value may be
     * changed using the {@code setValue} method.  This class
     * facilitates the process of building custom map
     * implementations. For example, it may be convenient to return
     * arrays of {@code RichMapEntry} instances in method
     * {@code RichMap.entrySet().toArray}.
     *
     * @since 1.6
     */
            public static class RichMapEntry<K,V>
                    implements RichMap.RichEntry<K,V>
            {
                private final K key;
                private V value;

                /**
                 * Creates an entry representing a mapping from the specified
                 * key to the specified value.
                 *
                 * @param key   the key represented by this entry
                 * @param value the value represented by this entry
                 */
                public RichMapEntry(K key, V value)
                {
                    this.key = key;
                    this.value = value;
                }

                /**
                 * Creates an entry representing the same mapping as the
                 * specified entry.
                 *
                 * @param entry the entry to copy
                 */
                public RichMapEntry(RichMap.RichEntry<? extends K, ? extends V> entry)
                {
                    this.key = entry.getKey();
                    this.value = entry.getValue();
                }

                /**
                 * Returns the key corresponding to this entry.
                 *
                 * @return the key corresponding to this entry
                 */
                public K getKey()
                {
                    return key;
                }

                /**
                 * Returns the value corresponding to this entry.
                 *
                 * @return the value corresponding to this entry
                 */
                public V getValue()
                {
                    return value;
                }

                /**
                 * Replaces the value corresponding to this entry with the specified
                 * value.
                 *
                 * @param value new value to be stored in this entry
                 * @return the old value corresponding to the entry
                 */
                public V setValue(V value)
                {
                    V oldValue = this.value;
                    this.value = value;
                    return oldValue;
                }

                /**
                 * Compares the specified object with this entry for equality.
                 * Returns {@code true} if the given object is also a map entry and
                 * the two entries represent the same mapping.  More formally, two
                 * entries {@code e1} and {@code e2} represent the same mapping
                 * if<pre>
                 *   (e1.getKey()==null ?
                 *    e2.getKey()==null :
                 *    e1.getKey().equals(e2.getKey()))
                 *   &amp;&amp;
                 *   (e1.getValue()==null ?
                 *    e2.getValue()==null :
                 *    e1.getValue().equals(e2.getValue()))</pre>
                 * This ensures that the {@code equals} method works properly across
                 * different implementations of the {@code Map.Entry} interface.
                 *
                 * @param o object to be compared for equality with this map entry
                 * @return {@code true} if the specified object is equal to this map
                 * entry
                 * @see #hashCode
                 */
                public boolean equals(Object o)
                {
                    if (!(o instanceof RichMap.RichEntry))
                        return false;
                    RichMap.RichEntry<?, ?> e = (RichMap.RichEntry<?, ?>) o;
                    return eq(key, e.getKey()) && eq(value, e.getValue());
                }

                /**
                 * Returns the hash code value for this map entry.  The hash code
                 * of a map entry {@code e} is defined to be: <pre>
                 *   (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
                 *   (e.getValue()==null ? 0 : e.getValue().hashCode())</pre>
                 * This ensures that {@code e1.equals(e2)} implies that
                 * {@code e1.hashCode()==e2.hashCode()} for any two Entries
                 * {@code e1} and {@code e2}, as required by the general
                 * contract of {@link Object#hashCode}.
                 *
                 * @return the hash code value for this map entry
                 * @see #equals
                 */
                public int hashCode()
                {
                    return (key == null ? 0 : key.hashCode()) ^
                            (value == null ? 0 : value.hashCode());
                }

                /**
                 * Returns a String representation of this map entry.  This
                 * implementation returns the string representation of this
                 * entry's key followed by the equals character ("{@code =}")
                 * followed by the string representation of this entry's value.
                 *
                 * @return a String representation of this map entry
                 */
                public String toString()
                {
                    return key + "=" + value;
                }

                /*
                 * Utility method for RichMapEntry.
                 * Test for equality, checking for nulls.
                 *
                 * NB: Do not replace with Object.equals until JDK-8015417 is resolved.
                 */
                private static boolean eq(Object o1, Object o2)
                {
                    return o1 == null ? o2 == null : o1.equals(o2);
                }
        }

        /**
         * Each of these fields are initialized to contain an instance of the
         * appropriate view the first time this view is requested.  The views are
         * stateless, so there's no reason to create more than one of each.
         *
         * <p>Since there is no synchronization performed while accessing these fields,
         * it is expected that java.util.Map view classes using these fields have
         * no non-final fields (or any fields at all except for outer-this). Adhering
         * to this rule would make the races on these fields benign.
         *
         * <p>It is also imperative that implementations read the field only once,
         * as in:
         *
         * <pre> {@code
         * public Set<K> keySet() {
         *   Set<K> ks = keySet;  // single racy read
         *   if (ks == null) {
         *     ks = new KeySet();
         *     keySet = ks;
         *   }
         *   return ks;
         * }
         *}</pre>
         */
        transient RichSet<K> keySet;

        /**
         * {@inheritDoc}
         *
         * @implSpec
         * This implementation returns a set that subclasses {@link AbstractSet}.
         * The subclass's iterator method returns a "wrapper object" over this
         * map's {@code entrySet()} iterator.  The {@code size} method
         * delegates to this map's {@code size} method and the
         * {@code contains} method delegates to this map's
         * {@code containsKey} method.
         *
         * <p>The set is created the first time this method is called,
         * and returned in response to all subsequent calls.  No synchronization
         * is performed, so there is a slight chance that multiple calls to this
         * method will not all return the same set.
         */
        public RichSet<K> keySet()
        {
            RichSet<K> ks = keySet;
            if (ks == null) {
                ks = new RichAbstractSet<K>() {
                    public Iterator<K> iterator() {
                        return new Iterator<K>() {
                            private Iterator<RichMap.RichEntry<K,V>> i = entrySet().iterator();

                            public boolean hasNext() {
                                return i.hasNext();
                            }

                            public K next() {
                                return i.next().getKey();
                            }

                            public void remove() {
                                i.remove();
                            }
                        };
                    }

                    public int size() {
                        return RichAbstractMap.this.size();
                    }

                    public boolean isEmpty() {
                        return RichAbstractMap.this.isEmpty();
                    }

                    public void clear() {
                        RichAbstractMap.this.clear();
                    }

                    public boolean contains(Object k) {
                        return RichAbstractMap.this.containsKey(k);
                    }
                };
                keySet = ks;
            }
            return ks;
        }

        public abstract RichSet<RichMap.RichEntry<K,V>> entrySet();

    }
