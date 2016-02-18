package ch.zeyger.algorithms.opt;

import ch.zeyger.algorithms.data.structures.Cycle;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public abstract class Opt {

    protected double minExchangeValue;
    protected double minStopValue;

    /**
     * @param minExchangeValue minimum gain value to perform an exchange
     */
    public void setMinExchangeValue(double minExchangeValue) {
        this.minExchangeValue = minExchangeValue;
    }

    /**
     * @param minStopValue minimum value to stop the optimization
     */
    public void setMinStopValue(double minStopValue) {
        this.minStopValue = minStopValue;
    }

    /**
     * Compute the gain between two nodes of a cycle in the given positions.
     * @param i first node position in the cycle
     * @param j second node position in the cycle
     * @param c the cycle to optimize
     * @return the gain if the two node are swapped
     */
    public abstract double computeGain(int i, int j, Cycle c);

    /**
     * Exchange two nodes given they positions in the cycle.
     * @param i first node
     * @param j second node
     * @param cycle the cycle to optimize
     */
    public abstract void exchange(int i, int j, Cycle cycle);

    /**
     * Perform the search for the maximum gain and, if it is acceptable, swap
     * the two nodes
     * @param cycle the cycle to optimize
     * @return the value gained with this update
     */
    public double update(Cycle cycle) {
        double bestGain = 0;
        int bestI = 0;
        int bestJ = 0;

        for (int i = 0; i < cycle.size(); i++) {
            for (int j = i + 2; j < cycle.size(); j++) {
                double gain = computeGain(i, j, cycle);
                if (gain < bestGain) {
                    bestGain = gain;
                    bestI = i;
                    bestJ = j;
                }
            }
        }
        if (bestGain < minExchangeValue)
            exchange(bestI, bestJ, cycle);
        return bestGain;
    }

    /**
     * Execute a complete cycle optimization.
     * @param cycle cycle to optimize
     */
    public void opt(Cycle cycle) {
        double bestGain;

        do{
            bestGain = update(cycle);
        } while (bestGain < minStopValue);
    }

    /**
     * Perform a 2-opt optimization with a limited amount of maximum steps.
     * @param cycle cycle to optimize
     * @param iterations number of steps to do
     */
    public void opt(Cycle cycle, int iterations) {
        double bestGain;
        for (int i = 0; i < iterations; i++) {
            bestGain = update(cycle);
            if (bestGain < minStopValue)
                break;
        }
    }
}
