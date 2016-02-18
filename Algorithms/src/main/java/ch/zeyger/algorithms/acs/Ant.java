package ch.zeyger.algorithms.acs;

import ch.zeyger.algorithms.data.nodes.NodeND;
import ch.zeyger.algorithms.data.structures.Cycle;
import ch.zeyger.algorithms.data.structures.Graph;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public class Ant {

    protected Random rand;

    protected Pheromone pheromone;
    protected Cycle solution;
    protected Graph graph;
    protected int graphSize;

    protected Set<NodeND> map;      // list of already visited nodes
    protected NodeND current;       // current visited node
    protected NodeND next;          // next visited node

    public Ant(Pheromone pheromone, Graph graph, Random rand) {
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
        graphSize = graph.size();
        map = new HashSet<>();

        solution = new Cycle();

        current = graph.get(startNode);

        solution.add(current);  // add the first city to the cycle
        map.add(current);       // mark the first city as visited
    }

    /**
     * Visit a node by randomly choose between following the best choice (Exploitation), or
     * by trying a random node (Exploration), laying pheromone in the meantime.
     * @param q0 Exploitation/Exploration parameter
     */
    private void visitNode(double q0) {

        double q = rand.nextDouble();
        NodeND r = current;
        NodeND chosenCity;

        // Compute maximum value
        int available_cities = graphSize - map.size();

        if (available_cities == 1)
            q0 = 1.0;

        if (q <= q0) {
            // Exploitation: find better node
            NodeND nodeMax = null;
            double max = 0;
            for (int i = 0; i < graphSize; i++){
                NodeND n = graph.get(i);

                if (!map.contains(n)) {
                    available_cities++;
                }
                if (!map.contains(n) && !n.equals(r)) {
                    double p = pheromone.get(r, n);
                    double d = graph.distance(r, n);
                    double v = p / (d * d); /* pow((1/d), beta);*/
                    if (v > max) {
                        nodeMax = n;
                        max = v;
                    }
                }
            }
            chosenCity = nodeMax;

        } else {
            // Exploration: take a random node
            double[] probability = new double[graphSize];
            double sum = 0;

            // compute the probability of each available node
            for (int i = 0; i < graphSize; i++){
                NodeND n = graph.get(i);

                if (!map.contains(n) && !n.equals(r)) {
                    double p = pheromone.get(r, n);
                    double d = graph.distance(r, n);
                    probability[i] = p / (d * d); /* pow((1/d), beta);*/
                    sum += probability[i];
                } else {
                    probability[i] = 0;
                }
            }

            // chose the most probable node
            double prob = rand.nextDouble() * sum;
            double prob_sum = 0;

            int cityToExplore = graphSize - 1;
            for (int i = 0; i < graphSize; i++){
                prob_sum += probability[i];

                if (prob < prob_sum) {
                    cityToExplore = i;
                    break;
                }
            }
            chosenCity = graph.get(cityToExplore);
        }

        map.add(chosenCity);
        next = chosenCity;
        solution.add(next);
    }

    /**
     * Local update of the pheromone.
     * @param t0 tau zero parameter
     * @param ro ro parameter (pheromone laying quantity)
     */
    private void updateTrail(double t0, double ro) {
        double p0 = pheromone.get(current, next);
        double p1 = p0 + ro * (t0 - p0);

//        pheromone.layDown(next, current, p0); TODO: check that the pheromone is not bidirectional
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
