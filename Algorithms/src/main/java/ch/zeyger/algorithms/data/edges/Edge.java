package ch.zeyger.algorithms.data.edges;

import ch.zeyger.algorithms.data.nodes.NodeND;

/**
 * Author:  Claudio Bonesana
 * Date:    28.02.2016
 * Project: Algorithms
 *
 * An edge is a structure that connect in an oriented a start node S
 * and an end node T with a given capacity C.
 */
public class Edge extends HyperEdge {

    public Edge(NodeND start, NodeND end, double capacity) {
        super(capacity);
        addNode("start", start);
        addNode("end", end);
    }

    public NodeND start() {
        return nodes.get("start");
    }

    public NodeND end() {
        return nodes.get("end");
    }
}
