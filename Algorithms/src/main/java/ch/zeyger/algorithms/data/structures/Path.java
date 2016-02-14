package ch.zeyger.algorithms.data.structures;

import ch.zeyger.algorithms.data.nodes.NodeND;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public class Path extends Graph {

    protected List<Integer> path = new ArrayList<>();

    @Override
    public void add(NodeND value) {
        path.add(elements.size());
        super.add(value);
    }

    @Override
    public NodeND get(int index) {
        int indexInPath = path.get(index);
        if (indexInPath >= size())
            return null;
        return super.get(indexInPath);
    }

    /**
     * Get the index saved in the path at the given position.
     * @param pos the target position
     * @return the index saved at the current position
     */
    public int pos(int pos) {
        return path.get(pos);
    }

    /**
     * Set an index in the path at the given position
     * @param pos the destination position
     * @param index the index that will be saved at this position
     */
    public void pos(int pos, int index) {
        path.add(pos, index);
    }

    public double getLength() {
        double length = 0.0;

        for (int i = 0; i < path.size() - 1; i++) {
            NodeND current = get(i);
            NodeND next = get(i + 1);
            length += current.distance(next);
        }

        return length;
    }
}
