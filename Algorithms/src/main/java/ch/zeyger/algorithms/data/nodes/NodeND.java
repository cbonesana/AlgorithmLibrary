package ch.zeyger.algorithms.data.nodes;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public class NodeND extends GenericNode<Double> {

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
