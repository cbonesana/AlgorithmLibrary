package ch.zeyger.algorithms.builders.neighborbased;

import ch.zeyger.algorithms.builders.neighborbased.utils.NeighborhoodNode;
import ch.zeyger.algorithms.data.nodes.NodeND;
import ch.zeyger.algorithms.data.structures.Graph;

import java.util.List;
import java.util.Set;

/**
 * Author:  Claudio Bonesana
 * Date:    14.02.2016
 * Project: Algorithms
 */
public interface NeighborBuilder {

    List<NeighborhoodNode<NodeND>> neighbourhood(Graph graph, Set<NodeND> nodeVisited, NodeND current);
}
