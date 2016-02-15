package ch.zeyger.algorithms.data.structures;

import ch.zeyger.algorithms.data.nodes.NodeND;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public class Path extends Graph {

    protected List<Integer> path = new ArrayList<>();

    /**
     * Empty constructor.
     */
    public Path() {}

    /**
     * Copy constructor.
     * @param another the path to copy
     */
    public Path(Path another) {
        super(another);
        this.path = new ArrayList<>(another.path.size());
        this.path.addAll(another.path.stream().collect(Collectors.toList()));
    }

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
     * Swaps the content of the two positions.
     * @param p1 first node
     * @param p2 second node
     */
    public void swap(int p1, int p2) {
        int i1 = pos(p1);
        int i2 = pos(p2);

        path.set(p1, i2);
        path.set(p2, i1);
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
