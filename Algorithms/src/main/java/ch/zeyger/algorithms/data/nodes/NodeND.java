package ch.zeyger.algorithms.data.nodes;

import java.util.ArrayList;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public class NodeND extends GenericNode<Double> {

    public NodeND() {}

    /**
     * Copy constructor
     * @param another source node
     */
    public NodeND(NodeND another) {
        this.elements = new ArrayList<>(another.elements.size());
        // copy names and elements
        for (String key : another.names.keySet()) {
            int i = another.names.get(key);
            double d = another.elements.get(i);
            this.names.put(key, i);
            this.elements.add(i, d);
        }
    }

    /**
     * Compute the euclidean distance between this node and the given one. This node
     * is considered as the first node, the other will be the last node.
     * @param other the other node
     * @return the euclidean distance between the two nodes
     */
    public double distance(NodeND other) {
        double sum = 0.0;
        for (int i = 0; i < elements.size(); i++) {
            double dThis = get(i);
            double dOther = other.get(i);
            double diff = dOther - dThis;
            sum += diff * diff;
        }
        return Math.sqrt(sum);
    }
}
