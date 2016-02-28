package ch.zeyger.algorithms.data.edges;

import ch.zeyger.algorithms.data.nodes.NodeND;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:  Claudio Bonesana
 * Date:    28.02.2016
 * Project: Algorithms
 */
public class HyperEdge {

    protected Map<String, NodeND> nodes = new HashMap<>();
    protected double capacity;

    public HyperEdge(double capacity) {
        this.capacity = capacity;
    }

    public void addNode(String name, NodeND node) {
        nodes.put(name, node);
    }

    public double getCapacity() {
        return capacity;
    }
}
