/*! \addtogroup Examples Examples 
 * This module contains many examples of usage. 
 * @{ 
 */
package edu.mit.csail.sdg.squander.examples.javacol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.annotations.Requires;
import edu.mit.csail.sdg.annotations.Returns;
import edu.mit.csail.sdg.annotations.SpecField;
import edu.mit.csail.sdg.squander.Squander;
import edu.mit.csail.sdg.squander.annotations.Options;

/**
 * Specifies very basic operations on various Java collections.
 * 
 * @author Aleksandar Milicevic
 */
@SpecField("x : one java.util.Map<String, Integer>")
public class Collections {
    
    // ===================================================
    // -------------------- List -------------------------
    // ===================================================
    
    @Requires("idx >= 0 && idx < list.length")
    @Returns("list.elts[idx]")
    public static <E> E get(List<E> list, int idx) {
        return Squander.exe(null, list, idx);
    }
    
    @Requires("idx >= 0 && idx < list.length")
    @Ensures("list.elts = @old(list.elts) ++ (idx -> value)")
    @Modifies("list.elts")
    public static <E> void set(List<E> list, int idx, E value) {
        Squander.exe(null, list, idx, value);
    }
    
    @Ensures({
        "list.elts = @old(list.elts) + (@old(list.length) -> elem)",
        "list.length = @old(list.length) + 1"
    })
    @Modifies("list.elts, list.length")
    public static <E> void add(List<E> list, E elem) {
        Squander.exe(null, list, elem);
    }
    
    @Ensures("some list.prev[elem] ? return = list.prev[elem] : return = null")
    public static <E> E prev(List<E> list, E elem) {
        return Squander.exe(null, list, elem);
    }
    
    @Ensures("some list.next[elem] ? return = list.next[elem] : return = null")
    public static <E> E next(List<E> list, E elem) {
        return Squander.exe(null, list, elem);
    }

    @Requires("idx >= 0 && idx < list.length")
    @Ensures({
        "return = @old(list.elts[idx])",
        "all i : int | (i < idx => list.elts[i] = @old(list.elts[i])) && " +
                      "(i >= idx && i < @old(list.length) => list.elts[i] = @old(list.elts[i+1]))",
        "list.length = @old(list.length) - 1"
    })
    @Modifies("list.elts, list.length")
    @Options(ensureAllInts = false)
    public static <E> E remove(List<E> list, int idx) {
        return Squander.exe(null, list, idx);
    }

    // ===================================================
    // -------------------- Map --------------------------
    // ===================================================
    
    @Ensures("key in map.keys ? return = map.elts[key] : return = null")
    public static <K, V> V get(Map<K, V> map, K key) {
        return Squander.exe(null, map, key);
    }
    
    @Ensures({
        "map.elts = @old(map.elts) ++ (key -> value)",
        "key in @old(map.keys) ? return = @old(map.elts[key]) : return = null"
    })
    @Modifies("map.elts")
    public static <K, V> V put(Map<K, V> map, K key, V value) {
        return Squander.exe(null, map, key, value);
    }
    
    @Ensures({
        "map.elts = @old(map.elts) - (key -> Object)",
        "key in @old(map.keys) ? return = @old(map.elts[key]) : return = null"
    })
    @Modifies("map.elts")
    public static <K, V> V removeKey(Map<K, V> map, K key) {
        return Squander.exe(null, map, key);
    }
    
    // ===================================================
    // -------------------- Set --------------------------
    // ===================================================
    
    @Returns("key in s.elts")
    public static <E> boolean contains(Set<E> s, E key) {
        return Squander.exe(null, s, key);
    }
    
    @Ensures({
        "s.elts = @old(s.elts) + key",
        "key in @old(s.elts) ? return = false : return = true"
    })
    @Modifies("s.elts")
    public static <E> boolean add(Set<E> s, E key) {
        return Squander.exe(null, s, key);
    }
    
    @Ensures({
        "s.elts = @old(s.elts) - key",
        "key in @old(s.elts) ? return = true : return = false"
    })
    @Modifies("s.elts")
    public static <E> boolean remove(Set<E> s, E key) {
        return Squander.exe(null, s, key);
    }
    
    @Returns("#m<String, Integer>.elts")
    private int f(Map<String, Integer> m) {
        return Squander.exe(this, m);
    }
    
    public static void main(String[] args) {
        Map<String, Integer> m = new HashMap<String, Integer>();
        System.out.println(new Collections().f(m));
    }
    
}
/*! @} */
