package ch.zeyger.algorithms.data.structures;

import ch.zeyger.algorithms.data.nodes.NodeND;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 *
 * This class guarantees the circularity of the cycle, to do so each index greater than
 * the number of elements is reduced to become circular (i.e., we always use the module
 * of the given index).
 */
public class Cycle extends Path {

    /**
     * Empty constructor.
     */
    public Cycle() {}

    /**
     * Copy constructor.
     * @param another the path to copy
     */
    public Cycle(Cycle another) {
        super(another);
    }

    /**
     * @param index the position in the cycle
     * @return the index MOD the size of elements
     */
    protected int circular(int index) {
        int size = size();
        if (index >= size && size > 0) index %= size;
        return index;
    }

    @Override
    public void add(String name, int index, NodeND value) {
        super.add(name, index, value);
    }

    @Override
    public void add(int index, NodeND value) {
        super.add(index, value);
    }

    @Override
    public NodeND get(int index) {
        return super.get(circular(index)); // circularity
    }

    @Override
    public void swap(int p1, int p2) {
        super.swap(circular(p1), circular(p2));
    }

    @Override
    public double getLength() {
        NodeND first = get(0);
        NodeND last = get(size() - 1);
        return super.getLength() + last.distance(first);
    }
}
