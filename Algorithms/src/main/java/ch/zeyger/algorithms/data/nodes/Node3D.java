package ch.zeyger.algorithms.data.nodes;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public class Node3D extends Node2D {

    /**
     * Copy constructor.
     * @param another an happy little node to copy
     */
    public Node3D(Node3D another) {
        this(another.id(), another.x(), another.y(), another.z());
    }

    public Node3D(int id, double x, double y, double z) {
        super(id, x, y);
        add("z", z);
    }

    public double z() {
        return get("z");
    }

    @Override
    public String toString() {
        return String.format("Node3D{id=%d, x=%f, y=%f, z=%f}", id(), x(), y(), z());
    }
}
