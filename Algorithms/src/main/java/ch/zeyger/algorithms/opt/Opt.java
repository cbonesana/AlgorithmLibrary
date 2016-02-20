package ch.zeyger.algorithms.opt;

import ch.zeyger.algorithms.data.structures.Cycle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public abstract class Opt {

    // gains are negative because we reduce the cycle length
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



    private class Exchange implements Comparable<Exchange> {
        public int i, j;
        public double gain;

        public Exchange(int i, int j, double gain) {
            this.i = i;
            this.j = j;
            this.gain = gain;
        }

        @Override
        public int compareTo(Exchange o) {
            return Double.compare(gain, o.gain);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Exchange)) return false;

            Exchange exchange = (Exchange) o;

            return i == exchange.i && j == exchange.j;

        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + j;
            return result;
        }
    }

    Map<Exchange, Integer> tabuExchanges = new HashMap<>();


    /**
     * Perform the search for the maximum gain and, if it is acceptable, swap
     * the two nodes
     * @param cycle the cycle to optimize
     * @return the value gained with this update
     */
    public double update(Cycle cycle) {
        double bestGain = 0;
//        int bestI = -1;
//        int bestJ = -1;

        PriorityQueue<Exchange> exchanges = new PriorityQueue<>();

        for (int i = 0; i < cycle.size(); i++) {
            for (int j = i + 2; j < cycle.size(); j++) {
                double gain = computeGain(i, j, cycle);
                if (gain < 0.0) // we have an effective gain
                    exchanges.add(new Exchange(i,j,gain));
//                if (gain < bestGain) {
//                    bestGain = gain;
//                    bestI = i;
//                    bestJ = j;
//                }
            }
        }

        // System.out.println("Exchanges: " + exchanges.size());
        if (exchanges.isEmpty())
            return 0.0;

        Exchange e = exchanges.poll();
        for (int i = 0; i < exchanges.size(); i++) {
            if (!tabuExchanges.containsKey(e)) {
                exchange(e.i, e.j, cycle);
                tabuExchanges.put(e, 10);
                break;
            } /*else {
                System.out.println(e.i + " " + e.j + " is tabu for " + tabuExchanges.get(e));
            }*/
            e = exchanges.poll();
        }

        return e.gain;
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
            // System.out.println(i);
            bestGain = update(cycle);
            if (bestGain >= minStopValue)
                break;
            cleanup();
        }
    }

    private void cleanup() {
        // System.out.print("Tabu Map: " + tabuExchanges.size() + " -> ");

        for (Iterator<Map.Entry<Exchange, Integer>> it = tabuExchanges.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Exchange, Integer> entry = it.next();
            int waiting = entry.getValue() -1;
            if (waiting == 0)
                it.remove();
            else
                entry.setValue(waiting);
        }
        // System.out.println(tabuExchanges.size());
    }
}
