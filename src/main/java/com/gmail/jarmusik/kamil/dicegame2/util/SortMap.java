/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.jarmusik.kamil.dicegame2.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kamil-Tomasz
 */
public class SortMap {
    
    private static <K, V> List<Map.Entry<K,V>> entrySortedByValue(Map<K, V> map, Comparator<V> comparator) {
        List<Map.Entry<K,V>> sortedEntries = new ArrayList<>(map.entrySet());
        Collections.sort(sortedEntries, (e1, e2) -> {
            return comparator.compare(e1.getValue(), e2.getValue());
        });
        return sortedEntries;
    }
    
    private static <K, V> List<Map.Entry<K,V>> entrySortedByKey(Map<K, V> map, Comparator<K> comparator) {
        List<Map.Entry<K,V>> sortedEntries = new ArrayList<>(map.entrySet());
        Collections.sort(sortedEntries, (e1, e2) -> {
            return comparator.compare(e1.getKey(), e2.getKey());
        });
        return sortedEntries;
    }
    
    public static <K, V> List<K> sortByValueToKeyList(Map<K, V> map, Comparator<V> comparator) {
        List<K> sortedKeys = new ArrayList<>();        
        entrySortedByValue(map, comparator).forEach((sortedEntry) -> {
            sortedKeys.add(sortedEntry.getKey());
        });
        return sortedKeys;
    }
    
    public static <K, V> List<V> sortByKeyToValueList(Map<K, V> map, Comparator<K> comparator) {
        List<V> sortedKeys = new ArrayList<>();        
        entrySortedByKey(map, comparator).forEach((sortedEntry) -> {
            sortedKeys.add(sortedEntry.getValue());
        });
        return sortedKeys;
    }
    
}
