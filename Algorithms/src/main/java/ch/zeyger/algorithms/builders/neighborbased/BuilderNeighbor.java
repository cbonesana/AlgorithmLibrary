package ch.zeyger.algorithms.builders.neighborbased;

import ch.zeyger.algorithms.builders.Builders;
import ch.zeyger.algorithms.builders.neighborbased.utils.NeighborhoodNode;
import ch.zeyger.algorithms.data.nodes.NodeND;
import ch.zeyger.algorithms.data.structures.Cycle;
import ch.zeyger.algorithms.data.structures.Graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author:  Claudio Bonesana
 * Date:    14.02.2016
 * Project: Algorithms
 */
public class BuilderNeighbor implements Builders {

    /**
     * Set of the already visited node that will nod be included in the neighbourhood.
     */
    protected Set<NodeND> nodeVisited = new HashSet<>();
    protected NeighborSearch search;
    protected NeighborBuilder build;

    public BuilderNeighbor(NeighborSearch search, NeighborBuilder build) {
        this.search = search;
        this.build = build;
    }

    /**
     * Find the nearest neighbour inside the neighbourhood neighbourhood around the current node.
     * @param neighbourhood the neighbourhood around the current node
     * @return the nearest neighbour to the current node
     */
    public NeighborhoodNode<NodeND> findInNeighbourhood(List<NeighborhoodNode<NodeND>> neighbourhood) {
        NeighborhoodNode<NodeND> target = neighbourhood.get(0);

        if (neighbourhood.size() > 1) {
            for (NeighborhoodNode<NodeND> neighbour : neighbourhood) {
                if (search.neighbour(neighbour, target))
                    target = neighbour;
            }
        }
        return target;
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
            // neighbourhood the neighbourhood
            List<NeighborhoodNode<NodeND>> neighbourhood = build.neighbourhood(graph, nodeVisited, current);

            // search the nearest node inside the neighbourhood
            NeighborhoodNode<NodeND> nearest = findInNeighbourhood(neighbourhood);

            current = nearest.node;     // set the current node as the next nearest one
            nodeVisited.add(current);   // set the node as visited
            cycle.add(current);         // add the node to the cycle
        }

        return cycle;
    }
}
