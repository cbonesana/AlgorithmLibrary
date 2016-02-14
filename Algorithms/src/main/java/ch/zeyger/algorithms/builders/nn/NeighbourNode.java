package ch.zeyger.algorithms.builders.nn;

import ch.zeyger.algorithms.data.nodes.NodeND;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public class NeighbourNode<T extends NodeND> {

    /**
     * The real node.
     */
    public T node;
    /**
     * The distance from this node.
     */
    public double distance;
}
