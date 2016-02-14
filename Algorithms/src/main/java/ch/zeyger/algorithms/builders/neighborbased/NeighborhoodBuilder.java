package ch.zeyger.algorithms.builders.neighborbased;

import ch.zeyger.algorithms.builders.neighborbased.utils.NeighborhoodNode;
import ch.zeyger.algorithms.data.nodes.NodeND;
import ch.zeyger.algorithms.data.structures.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Author:  Claudio Bonesana
 * Date:    14.02.2016
 * Project: Algorithms
 */
public class NeighborhoodBuilder implements NeighborBuilder {

    /**
     * Given the input graph and the current node, neighbourhood the neighbourhood around this one.
     * @param graph the input graph
     * @param current last node inserted in the cycle
     * @return a list of the neighbourhood nodes
     */
    @Override
    public List<NeighborhoodNode<NodeND>> neighbourhood(Graph graph, Set<NodeND> nodeVisited, NodeND current) {
        List<NeighborhoodNode<NodeND>> neighbourhood = new ArrayList<>();

        for (int i = 0; i < graph.size(); i++){
            NodeND node = graph.get(i);
            if (!nodeVisited.contains(node)){
                NeighborhoodNode<NodeND> nNode = new NeighborhoodNode<>();
                nNode.node = node;
                nNode.distance = graph.distance(current, node);
                neighbourhood.add(nNode);
            }
        }

        return neighbourhood;
    }
}
