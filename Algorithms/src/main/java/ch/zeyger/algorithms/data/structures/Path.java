package ch.zeyger.algorithms.data.structures;

import ch.zeyger.algorithms.data.nodes.NodeND;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public class Path extends Graph {

    /**
     * A path represents a sequence of nodes and it stores the list of
     * the positions of every node.
     */
    protected List<Integer> path = new ArrayList<>();
    /**
     * Given a node, this map stores its position in the path.
     */
    protected Map<NodeND, Integer> nodeToPosition = new HashMap<>();
    /**
     * Given a position in the path, this map stores the relative node.
     */
    protected Map<Integer, NodeND> positionToNode = new HashMap<>();

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
        this.nodeToPosition = new HashMap<>(another.nodeToPosition.size());
        this.nodeToPosition.putAll(another.nodeToPosition);
        this.positionToNode = new HashMap<>(another.positionToNode.size());
        this.positionToNode.putAll(another.positionToNode);
    }

    @Override
    public void add(String name, int index /* a.k.a. position */, NodeND value) {
        super.add(name, index, value);
        nodeToPosition.put(value, index);
        positionToNode.put(index, value);
        path.add(index);
    }

    @Override
    public void add(int index, NodeND value) {
        add("" + index, index, value);
    }

    @Override
    public void add(String name, NodeND value) {
        super.add(name, elements.size(), value);
    }

    @Override
    public void add(NodeND value) {
        int index = elements.size();
        add("" + index, index, value);
    }

    /**
     * @param index the position of the value
     * @return the node in the path
     */
    @Override
    public NodeND get(int index) {
        return positionToNode.get(index);
    }

    /**
     * @param node the node to search
     * @return the position in the path of the node
     */
    public Integer positionOf(NodeND node) {
        return nodeToPosition.get(node);
    }

    /**
     * Given the name of the node, return its position on the path.
     * @param name the name of the value
     * @return the position in the path of the node
     */
    public Integer positionOf(String name) {
        NodeND node = super.get(name);
        return this.positionOf(node);
    }

    /**
     * Swaps the  positions of the two nodes in the path.
     * @param first first node
     * @param second second node
     */
    public void swap(NodeND first, NodeND second) {
        int p1 = positionOf(first);
        int p2 = positionOf(second);

        swap(p1, p2, first, second);
    }

    /**
     * Swaps the content of the two positions.
     * @param p1 position of first node
     * @param p2 position of second node
     */
    public void swap(int p1, int p2) {
        NodeND first = get(p1);
        NodeND second = get(p2);

        swap(p1, p2, first, second);
    }

    /**
     * The real swap method is protected to keep the internal maps coherent.
     * @param p1 position of first node
     * @param p2 position of second node
     * @param first first node
     * @param second second node
     */
    protected void swap(int p1, int p2, NodeND first, NodeND second) {
        nodeToPosition.put(first, p2);
        nodeToPosition.put(second, p1);

        positionToNode.put(p1, second);
        positionToNode.put(p2, first);

        path.set(p1, p2);
        path.set(p2, p1);
    }

    /**
     * @return the length of the path.
     */
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
