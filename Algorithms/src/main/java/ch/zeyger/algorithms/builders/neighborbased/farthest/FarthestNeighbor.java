package ch.zeyger.algorithms.builders.neighborbased.farthest;

import ch.zeyger.algorithms.builders.neighborbased.BuilderNeighbor;
import ch.zeyger.algorithms.builders.neighborbased.NeighborhoodBuilder;

/**
 * Author:  Claudio Bonesana
 * Date:    14.02.2016
 * Project: Algorithms
 */
public class FarthestNeighbor extends BuilderNeighbor {

    public FarthestNeighbor() {
        super(new FarthestSearch(), new NeighborhoodBuilder());
    }
}
