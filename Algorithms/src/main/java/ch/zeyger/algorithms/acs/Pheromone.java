package ch.zeyger.algorithms.acs;

import ch.zeyger.algorithms.data.nodes.NodeND;
import ch.zeyger.algorithms.utils.PairMapUtils;
import org.apache.commons.math3.util.Pair;

import java.util.HashMap;
import java.util.Map;

import static ch.zeyger.algorithms.utils.PairMapUtils.*;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 *
 * Representation of the pheromonic matrix.
 */
public class Pheromone {

    protected Map<Pair<NodeND, NodeND>, Double> pheromone = new HashMap<>();

    protected double t0;

    public Pheromone(double t0) {
        this.t0 = t0;
    }

    /**
     * Get the value of pheromone deposited on the map.
     * @param i first element
     * @param j second element
     * @return the value associated with the key [first,second] element
     */
    public double get(NodeND i, NodeND j) {
        Double v = getUniqueValue(pheromone, i, j);
        if (v == null) {
            v = t0;     // initialization on demand
            PairMapUtils.setUniqueValue(pheromone, i, j, v);
        }
        return v;
    }

    public void set(NodeND i, NodeND j, double value) {
        setUniqueValue(pheromone, i, j, value);
    }

    /**
     * Add pheromone to the map.
     * @param i source element
     * @param j destination element
     * @param value value to add
     */
    public void layDown(NodeND i, NodeND j, double value) {
        Double currentValue = getUniqueValue(pheromone, i, j);
        if (currentValue == null)
            currentValue = value + t0;
        else
            currentValue += value;
        setUniqueValue(pheromone, i, j, currentValue);
    }

    /**
     * Remove a percentage of pheromone from the map globally.
     * @param quantity the quantity of pheromone to remove
     */
    public void pullUp(double quantity) {
        for (Pair<NodeND, NodeND> key : pheromone.keySet()) {
            double update = pheromone.get(key) - quantity;
            pheromone.put(key, update);
        }
    }
}
