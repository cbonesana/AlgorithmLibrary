package ch.zeyger.algorithms.builders.nn;

import ch.zeyger.algorithms.builders.Builders;
import ch.zeyger.algorithms.data.nodes.NodeND;
import ch.zeyger.algorithms.data.structures.Cycle;
import ch.zeyger.algorithms.data.structures.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public class NearestNeighbour implements Builders {

    /**
     * Set of the already visited node that will nod be included in the neighbourhood.
     */
    protected Set<NodeND> nodeVisited = new HashSet<>();

    /**
     * Given the input graph and the current node, build the neighbourhood around this one.
     * @param graph the input graph
     * @param current last node inserted in the cycle
     * @return a list of the neighbourhood nodes
     */
    private List<NeighbourNode<NodeND>> buildNeighbourhood(Graph graph, NodeND current) {
        List<NeighbourNode<NodeND>> neighbourhood = new ArrayList<>();

        for (int i = 0; i < graph.size(); i++){
            NodeND node = graph.get(i);
            if (!nodeVisited.contains(node)){
                NeighbourNode<NodeND> nNode = new NeighbourNode<>();
                nNode.node = node;
                nNode.distance = graph.distance(current, node);
                neighbourhood.add(nNode);
            }
        }

        return neighbourhood;
    }

    /**
     * Find the nearest neighbour inside the neighbourhood build around the current node.
     * @param neighbourhood the neighbourhood around the current node
     * @return the nearest neighbour to the current node
     */
    public NeighbourNode<NodeND> nearestSearch(List<NeighbourNode<NodeND>> neighbourhood) {
        NeighbourNode<NodeND> min = neighbourhood.get(0);

        if (neighbourhood.size() > 1) {
            for (NeighbourNode<NodeND> neighbour : neighbourhood) {
                if (neighbour.distance < min.distance)
                    min = neighbour;
            }
        }
        return min;
    }

    /**
     * Build a cycle based on the nearest neighbour algorithm.
     * The initial node will be the first node in the graph.
     * @param graph input graph
     * @return the found cycle
     */
    @Override
    public Cycle buildCycle(Graph graph) {
        return buildCycle(graph, 0);
    }

    /**
     * Build a cycle based on the nearest neighbour algorithm.
     * The initial node will be the given node.
     * @param graph input graph
     * @param start first node to use
     * @return the found cycle
     */
    @Override
    public Cycle buildCycle(Graph graph, int start){
        NodeND current = graph.get(start);  // chose a first arbitrary node
        Cycle cycle = new Cycle();
        nodeVisited.add(current);           // populate visited node set
        cycle.add(current);                 // first arbitrary chosen node is added to the cycle

        for (int j = 0; j < graph.size() -1; j++) {
            // find the shortest path between the current node and an unvisited node
            // the neighbourhood size reduces by 1 node each iteration
            // build the neighbourhood
            List<NeighbourNode<NodeND>> neighbourhood = buildNeighbourhood(graph, current);

            // search the nearest node inside the neighbourhood
            NeighbourNode<NodeND> nearest = nearestSearch(neighbourhood);

            current = nearest.node;     // set the current node as the next nearest one
            nodeVisited.add(current);   // set the node as visited
            cycle.add(current);         // add the node to the cycle
        }

        return cycle;
    }
}
