package ch.zeyger.algorithms.builders.neighborbased.utils;

import ch.zeyger.algorithms.data.nodes.NodeND;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 *
 * Support node that wraps a {@link NodeND} node and adds a distance measurement field.
 */
public class NeighborhoodNode<T extends NodeND> {

    /**
     * The real node.
     */
    public T node;
    /**
     * The distance from this node.
     */
    public double distance;

    /**
     * Empty Constructor.
     */
    public NeighborhoodNode() {}

    /**
     * Constructs this Neighbour node initializing the fields with the given parameters.
     * @param node node to wrap around
     * @param distance distance inside the neighbourhood relative to the starting point
     */
    public NeighborhoodNode(T node, double distance) {
        this.node = node;
        this.distance = distance;
    }
}
