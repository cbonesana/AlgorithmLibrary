package ch.zeyger.algorithms.data.nodes;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public class Node2D extends NodeND {

    protected int id;

    /**
     * Copy constructor
     * @param another an happy node to copy
     */
    public Node2D(Node2D another) {
        this(another.id(), another.x(), another.y());
    }

    public Node2D(int id, double x, double y) {
        this.id = id;
        add("x", x);
        add("y", y);
    }

    public int id() {
        return id;
    }

    public double x() {
        return get("x");
    }

    public double y() {
        return get("y");
    }

    @Override
    public int hashCode() {
        return super.hashCode() + id;
    }

    @Override
    public String toString() {
        return String.format("Node2D{id=%d, x=%f, y=%f}", id(), x(), y());
    }
}
