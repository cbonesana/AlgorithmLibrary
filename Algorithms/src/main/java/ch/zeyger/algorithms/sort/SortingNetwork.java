package ch.zeyger.algorithms.sort;

import java.util.Collections;
import java.util.List;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public class SortingNetwork {

    public static <T extends Comparable<T>> void sort(List<T> list) {
        switch (list.size()) {
            case 2: sort2(list); break;
            case 3: sort3(list); break;
            case 4: sort4(list); break;
            default:
                Collections.sort(list);
        }
    }

    public static <T extends Comparable<T>> void sort2(List<T> list) {
        T p0 = list.get(0);
        T p1 = list.get(1);
        if (p0.compareTo(p1) == 1) {
            list.set(0, p1);
            list.set(1, p0);
        }
    }

    public static <T extends Comparable<T>> void sort3(List<T> list) {
        sort2(list);
        sort2(list);
        sort2(list);
    }

    public static <T extends Comparable<T>> void sort4(List<T> list) {
        sort2(list);
        sort2(list);
        sort2(list);
        sort2(list);
        sort2(list);
    }
}
