package ch.zeyger.algorithms.opt;

import ch.zeyger.algorithms.data.structures.Cycle;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 *
 * Class used to perform a 2-optimizationAlgorithm on a cycle.
 */
public class Opt2 extends Opt {

    /**
     * Function to compute the gain of the exchange of two indices.
     * @param i first index
     * @param j second index
     * @param cycle the cycle to work on
     * @return the gain after the changes
     */
    @Override
    public double computeGain(int i, int j, Cycle cycle) {
        return cycle.distance(cycle.get(i), cycle.get(j))
                +cycle.distance(cycle.get(i + 1), cycle.get(j + 1))
                -cycle.distance(cycle.get(i), cycle.get(i + 1))
                -cycle.distance(cycle.get(j), cycle.get(j + 1));
    }

    /**
     * Function to Exchange two given nodes and neighbourhood the new optimized tour.
     * @param i first index
     * @param j second index
     */
    @Override
    public void exchange(int i, int j, Cycle cycle) {
        int b, c;

        // b should always be the smallest one
        if(i<j) {
            b = i;
            c = j;
        } else {
            b = j;
            c = i;
        }

        for (int n = 0; n < (c - b +1) / 2; n++) {
            int dst = c - n;
            int src = b + n;
            cycle.swap(src, dst);
        }
    }
}
