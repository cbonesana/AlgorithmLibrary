package ch.zeyger.algorithms.data.structures;

import ch.zeyger.algorithms.data.edges.Edge;
import ch.zeyger.algorithms.data.nodes.GenericNode;
import ch.zeyger.algorithms.data.nodes.NodeND;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:  Claudio Bonesana
 * Date:    28.02.2016
 * Project: Algorithms
 */
public class OrientedGraph extends GenericNode<NodeND> {

    protected Map<Pair<NodeND, NodeND>, Edge> edges = new HashMap<>();

    public OrientedGraph() {}

    public OrientedGraph(OrientedGraph another) {
        super(another);
        this.edges = new HashMap<>(another.edges.size());
        for (Map.Entry<Pair<NodeND, NodeND>, Edge> entry : another.edges.entrySet())
            edges.put(entry.getKey(), entry.getValue());
    }

    public double getCapacity(NodeND start, NodeND end) throws MissingEdgeException {
        return getEdge(start, end).getCapacity();
    }

    public Edge getEdge(NodeND start, NodeND end) throws MissingEdgeException {
        Pair<NodeND, NodeND> key = new Pair<>(start, end);
        Edge e = edges.get(key);
        if (e == null)
            throw new MissingEdgeException("The two nodes are not connected.");
        return e;
    }


}
