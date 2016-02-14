package ch.zeyger.algorithms.builders;

import ch.zeyger.algorithms.data.structures.Cycle;
import ch.zeyger.algorithms.data.structures.Graph;

/**
 * Author:  Claudio Bonesana
 * Date:    14.02.2016
 * Project: Algorithms
 */
public interface Builders {

    Cycle buildCycle(Graph graph);

    Cycle buildCycle(Graph graph, int start);
}
