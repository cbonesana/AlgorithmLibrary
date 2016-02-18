package ch.zeyger.algorithms.data.structures;

import ch.zeyger.algorithms.data.nodes.GenericNode;
import ch.zeyger.algorithms.data.nodes.NodeND;
import org.apache.commons.math3.util.Pair;

import java.util.HashMap;
import java.util.Map;

import static ch.zeyger.algorithms.utils.PairMapUtils.getUniqueValue;
import static ch.zeyger.algorithms.utils.PairMapUtils.putUniqueValue;

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

    /**
     * Empty constructor.
     */
    public Graph() {}

    /**
     * Copy constructor.
     * @param another the object to copy from
     */
    public Graph(Graph another) {
        super(another);
        this.distances = new HashMap<>(another.distances.size());
        for (Map.Entry<Pair<NodeND, NodeND>, Double> entry : another.distances.entrySet()) {
            distances.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Get the distance between two nodes. If the distance is not cached in the distance matrix,
     * computes the distances between the two nodes and stores it.
     * @param i the first node
     * @param j the second node
     * @return 0.0 if the two nodes are the sames or if they are not in the graph, otherwise the distance.
     */
    public double distance(NodeND i, NodeND j) {
        if (i.equals(j))
            return 0.0;
        Double d = getUniqueValue(distances, i, j);
        if (d == null) {
            d = i.distance(j);
            putUniqueValue(distances, i, j, d);
        }
        return d;
    }
}
