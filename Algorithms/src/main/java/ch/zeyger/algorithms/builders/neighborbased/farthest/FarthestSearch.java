package ch.zeyger.algorithms.builders.neighborbased.farthest;

import ch.zeyger.algorithms.builders.neighborbased.utils.NeighborhoodNode;
import ch.zeyger.algorithms.builders.neighborbased.NeighborSearch;
import ch.zeyger.algorithms.data.nodes.NodeND;

/**
 * Author:  Claudio Bonesana
 * Date:    14.02.2016
 * Project: Algorithms
 */
public class FarthestSearch implements NeighborSearch {

    @Override
    public boolean neighbour(NeighborhoodNode<NodeND> a, NeighborhoodNode<NodeND> b) {
        return a.distance > b.distance;
    }
}
