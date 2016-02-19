package ch.zeyger.algorithms.acs;

import ch.zeyger.algorithms.data.nodes.NodeND;
import ch.zeyger.algorithms.data.structures.Cycle;
import ch.zeyger.algorithms.data.structures.Graph;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.*;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public class Ant {

    protected RandomDataGenerator rand;

    protected Pheromone pheromone;
    protected Cycle solution;
    protected Graph graph;

    protected Set<NodeND> map;      // list of available nodes
    protected NodeND current;       // current visited node
    protected NodeND next;          // next visited node

    public Ant(Pheromone pheromone, Graph graph, RandomDataGenerator rand) {
        this.pheromone = pheromone;
        this.graph = graph;
        this.rand = rand;
    }

    /**
     * Initialize the ant on a given starting node, cleaning the previous solution.
     * @param startNode The starting node
     */
    public void initialize(int startNode) {
        // clean the previous solution
        map = new HashSet<>();
        solution = new Cycle();
        current = graph.get(startNode);
        map.addAll(graph.elements());
        map.remove(current);
        solution.add(current);
    }

    /**
     * Visit a node by randomly choose between following the best choice (Exploitation), or
     * by trying a random node (Exploration), laying pheromone in the meantime.
     * @param q0 Exploitation/Exploration parameter
     */
    private void visitNode(double q0) {
        double q = rand.nextUniform(0.0, 1.0);
        NodeND r = current;
        NodeND chosenNode;

        // Compute maximum value
        if (q <= q0) {
            // Exploitation: find better node
            chosenNode = exploitation(r);
        } else {
            // Exploration: take a random node
            chosenNode = exploration(r);
        }

        map.remove(chosenNode);
        next = chosenNode;
        solution.add(next);
    }

    /**
     * Chose a random node between the remain nodes.
     * @param r the current node
     * @return a nice random node
     */
    protected NodeND exploration(NodeND r) {
        NodeND chosenNode = null;
        Map<NodeND, Double> probabilities = new HashMap<>();
        double sum = 0;

        // compute the probability of each available node
        for (NodeND n : map) {
            double probability;
            double p = pheromone.get(r, n);
            double d = graph.distance(r, n);
            probability = p / (d * d); /* pow((1/d), beta);*/
            sum += probability;
            probabilities.put(n, probability);
        }

        // chose the most probable node
        double prob = rand.nextUniform(0.0, sum);
        double prob_sum = 0;

        for (NodeND n : map) {
            prob_sum += probabilities.get(n);
            if (prob < prob_sum) {
                chosenNode = n;
                break;
            }
        }

        return chosenNode;
    }

    /**
     * Find the better node for the given one, between the remain nodes.
     * @param r the current node
     * @return the better node
     */
    protected NodeND exploitation(NodeND r) {
        NodeND chosenNode = null;
        double max = 0;
        for (NodeND n : map) {
            double p = pheromone.get(r, n);
            double d = graph.distance(r, n);
            double v = p / (d * d); /* pow((1/d), beta);*/
            if (v > max) {
                chosenNode = n;
                max = v;
            }
        }

        return chosenNode;
    }

    /**
     * Local update of the pheromone.
     * @param t0 tau zero parameter
     * @param ro ro parameter (pheromone laying quantity)
     */
    private void updateTrail(double t0, double ro) {
        double p0 = pheromone.get(current, next);
        double p1 = p0 + ro * (t0 - p0);

        pheromone.layDown(current, next, p1);

        current = next;
        next = null;
    }

    /**
     * Move the ant on the current city by using the Exploitation/Exploration mechanism.
     * @param q0 Exploitation/Exploration parameter
     * @param t0 tau zero parameter
     * @param ro ro parameter (pheromone laying quantity)
     */
    public void generateSolution(double q0, double t0, double ro) {
        visitNode(q0);
        updateTrail(t0, ro);
    }

    /**
     * @return the current node where the ant is
     */
    public NodeND getCurrentNode() {
        return current;
    }
}
