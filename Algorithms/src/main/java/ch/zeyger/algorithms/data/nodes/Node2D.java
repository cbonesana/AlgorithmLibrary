package ch.zeyger.algorithms.data.nodes;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public class Node2D extends NodeND {

    public Node2D(double x, double y) {
        add("x", x);
        add("y", y);
    }

    public double x() {
        return get("x");
    }

    public double y() {
        return get("y");
    }
}
