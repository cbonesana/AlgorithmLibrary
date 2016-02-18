package ch.zeyger.algorithms.utils;

import org.apache.commons.math3.util.Pair;

import java.util.Map;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public class PairMapUtils {

    /**
     * Given a map with a pair of elements as key, return the unique value, in other word
     * it does not matter if the pair is in [i,j] or [j,i] format.
     * @param map map where to extract the element
     * @param i first element of the pair
     * @param j second element of the pair
     * @param <T> type of the elements inside the pair
     * @return null if no key exists, otherwise the value associate to the unique pair
     * §       composed with the two elements
     */
    public static <T> Double getUniqueValue(Map<Pair<T,T>, Double> map, T i, T j) {
        Pair<T, T> key = new Pair<>(i,j);
        if (map.containsKey(key))
            return map.get(key);
        key = new Pair<>(j, i);      // the two nodes are exchanged
        if (map.containsKey(key))
            return map.get(key);
        return null;                 // same node or insistent node
    }

    /**
     * Given a map and a pair of elements as key, set the relative value in a unique way,
     * in other word it does not matter if the pair is in [i,j] or [j,i] format.
     * @param map map where to extract the element
     * @param i first element of the pair
     * @param j second element of the pair
     * @param value the new value for the pair
     * @param <T> type of the elements inside the pair
     */
    public static <T> void putUniqueValue(Map<Pair<T,T>, Double> map, T i, T j, double value) {
        Pair<T, T> key = new Pair<>(i, j);
        if (!map.containsKey(key)) {
            Pair<T, T> yek = new Pair<>(j, i);      // the two nodes are exchanged
            if (map.containsKey(yek))
                key = yek;
        }
        map.put(key, value);
    }

    /**
     * Given a map with a pair of elements as key, return the value. There is difference
     * between the order of the two elements in the key.
     * @param i first element of the pair
     * @param j second element of the pair
     * @param map map where to extract the element
     * @param <T> type of the elements inside the pair
     * @return null if the key does not exists, otherwise the value associate to the pair
     *         composed with the two elements
     */
    public static <T> Double getValue(Map<Pair<T,T>, Double> map, T i, T j) {
        Pair<T, T> key = new Pair<>(i, j);
        if (map.containsKey(key))
            return map.get(key);
        return null;
    }
}
