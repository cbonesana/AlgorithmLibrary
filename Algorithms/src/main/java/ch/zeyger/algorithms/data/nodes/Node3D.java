package ch.zeyger.algorithms.data.nodes;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public class Node3D extends Node2D {

    public Node3D(double x, double y, double z) {
        super(x, y);
        add("z", z);
    }

    public double z() {
        return get("z");
    }
}
