package ch.zeyger.algorithms.data.structures;

import ch.zeyger.algorithms.data.nodes.GenericNode;
import ch.zeyger.algorithms.data.nodes.NodeND;
import org.apache.commons.math3.util.Pair;

import java.util.HashMap;
import java.util.Map;

import static ch.zeyger.algorithms.utils.PairMapUtils.getUniqueValue;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public class Graph extends GenericNode<NodeND> {

    /**
     * Slack matrix of distances between each node.
     * The size of this matrix is (n^2)/2 -n
     */
    protected Map<Pair<NodeND, NodeND>, Double> distances = new HashMap<>();

    @Override
    public void add(String name, int index, NodeND value) {
        super.add(name, index, value);

        for (NodeND n : elements) {
            Pair<NodeND, NodeND> key = new Pair<>(value, n);
            double distance = value.distance(n);

            distances.put(key, distance);
        }
    }

    /**
     * Get the distance between two nodes.
     * @param i the first node
     * @param j the second node
     * @return 0.0 if the two nodes are the sames or if they are not in the graph, otherwise the distance.
     */
    public double distance(NodeND i, NodeND j) {
        if (i.equals(j))
            return 0.0;
        Double d = getUniqueValue(distances, i, j);
        if (d == null)
            return 0.0;
        return d;
    }
}
