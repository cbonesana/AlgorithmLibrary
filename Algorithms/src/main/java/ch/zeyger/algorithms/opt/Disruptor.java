package ch.zeyger.algorithms.opt;

import ch.zeyger.algorithms.data.structures.Cycle;

/**
 * Author:  Claudio Bonesana
 * Date:    15.02.2016
 * Project: Algorithms
 */
public interface Disruptor {

    void disrupt(Cycle cycle);

    void disrupt(Cycle cycle, int times);
}
