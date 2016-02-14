package ch.zeyger.algorithms.opt;

import ch.zeyger.algorithms.data.structures.Cycle;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public abstract class Opt {

    public abstract double computeGain(int i, int j, Cycle c);

    public abstract void exchange(int i, int j, Cycle cycle);

    /**
     * Perform a complete cycle optimization.
     * The minExchangeValue and minStopValue are both set to 0.0.
     * @param cycle cycle to optimize
     */
    public void opt(Cycle cycle) {
        opt(cycle, 0.0, 0.0);
    }

    /**
     * Execute a complete cycle optimization.
     * @param cycle cycle to optimize
     * @param minExchangeValue minimum gain value to perform an exchange
     * @param minStopValue minimum value to stop the optimization
     */
    public void opt(Cycle cycle, double minExchangeValue, double minStopValue)
    {
        double bestGain, gain;

        do{
            bestGain = 0;
            int bestI = 0;
            int bestJ = 0;

            for (int i = 0; i < cycle.size(); i++) {
                for (int j = i + 2; j < cycle.size(); j++) {
                    gain = computeGain(i, j, cycle);
                    if (gain < bestGain) {
                        bestGain = gain;
                        bestI = i;
                        bestJ = j;
                    }
                }
            }
            if (bestGain < minExchangeValue)
                exchange(bestI, bestJ, cycle);
        } while (bestGain < minStopValue);
    }
}
