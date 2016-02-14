package ch.zeyger.algorithms.builders.neighborbased.nearest;

import ch.zeyger.algorithms.builders.neighborbased.BuilderNeighbor;
import ch.zeyger.algorithms.builders.neighborbased.NeighborhoodBuilder;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public class NearestNeighbor extends BuilderNeighbor {

    public NearestNeighbor() {
        super(new NearestSearch(), new NeighborhoodBuilder());
    }
}
