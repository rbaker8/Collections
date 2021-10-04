package com.richabaker.collections.maps;

import java.util.HashMap;

public class RichLinkedHashMap
{
    static class RichEntry<K,V> extends RichHashMap.RichNode<K,V>
    {
        RichLinkedHashMap.RichEntry<K,V> before, after;
        RichEntry(int hash, K key, V value, RichHashMap.RichNode<K,V> next)
        {
            super(hash, key, value, next);
        }
    }
}
