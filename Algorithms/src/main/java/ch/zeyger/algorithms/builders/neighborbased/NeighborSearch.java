package ch.zeyger.algorithms.builders.neighborbased;

import ch.zeyger.algorithms.builders.neighborbased.utils.NeighborhoodNode;
import ch.zeyger.algorithms.data.nodes.NodeND;

/**
 * Author:  Claudio Bonesana
 * Date:    14.02.2016
 * Project: Algorithms
 */
public interface NeighborSearch {

    boolean neighbour(NeighborhoodNode<NodeND> a, NeighborhoodNode<NodeND> b);

}
