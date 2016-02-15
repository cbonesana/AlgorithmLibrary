package ch.zeyger.algorithms.data.structures;

import ch.zeyger.algorithms.data.nodes.NodeND;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
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

    @Override
    public NodeND get(int index) {
        int indexInPath = path.get(index % size()); // circularity
        return super.get(indexInPath);
    }

    @Override
    public double getLength() {
        NodeND first = get(0);
        NodeND last = get(size() -1);
        return super.getLength() + last.distance(first);
    }
}
