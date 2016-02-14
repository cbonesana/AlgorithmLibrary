package ch.zeyger.algorithms.acs;

import ch.zeyger.algorithms.builders.Builders;
import ch.zeyger.algorithms.builders.nn.NearestNeighbour;
import ch.zeyger.algorithms.data.nodes.NodeND;
import ch.zeyger.algorithms.data.structures.Cycle;
import ch.zeyger.algorithms.data.structures.Graph;
import ch.zeyger.algorithms.opt.Opt;
import ch.zeyger.algorithms.opt.Opt2;

import java.util.Random;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 */
public class AntColonySystem {

    protected Random rand;

    protected Cycle bestSolution;             // best solution in absolute
    protected Cycle localSolution;            // best solution at each iteration

    protected Pheromone pheromone;

    protected Ant[] ants;

    protected Builders buildAlgorithm = null;
    protected Opt optimizationAlgorithm = null;

    protected int antNumber;
    protected double alpha;
    protected double ro;
    protected double q0;
    protected double q0Min;
    protected double q0Step;
    protected double t0;
    protected double threshold;
    protected int maxBestCounter;
    protected int maxLocalCounter;

    /**
     * Initialize the Ant Colony System with some parameters.
     * @param antNumber number of ant to use
     * @param alpha the amount of pheromone that an ant can lay down
     * @param ro the amount of pheromone that evaporate per time unit
     * @param q0 between 0 and 1, set the probability to choose between Exploitation and Exploration
     * @param q0Min the minimum value of q0
     * @param q0Step decrement value
     * @param threshold minimum fraction of pheromone
     * @param maxBestCounter counter used to stop the best solution to lay pheromone
     * @param maxLocalCounter counter used to stop the local solution to lay pheromone
     * @param seed random seed
     */
    public AntColonySystem(int antNumber, double alpha, double ro, double q0, double q0Min, double q0Step, double threshold, int maxBestCounter, int maxLocalCounter, long seed) {
        this.antNumber = antNumber;
        this.alpha = alpha;
        this.ro = ro;
        this.q0 = q0;
        this.q0Min = q0Min;
        this.q0Step = q0Step;
        this.threshold = threshold;
        this.maxBestCounter = maxBestCounter;
        this.maxLocalCounter = maxLocalCounter;

        rand = new Random(seed);
    }

    /**
     * A build algorithm is needed to build the initial solution. If no builder is assigned, a
     * {@link NearestNeighbour} algorithm will be used.
     * @param buildAlgorithm the builder to use
     */
    public void setBuildAlgorithm(Builders buildAlgorithm) {
        this.buildAlgorithm = buildAlgorithm;
    }

    /**
     * An optimization algorithm is needed to optimize the found solutions. If no optimizer is assigned a
     * {@link Opt2} optimization algorithm will be used.
     * @param optimizationAlgorithm the optimization algorithm to use
     */
    public void setOptimizationAlgorithm(Opt optimizationAlgorithm) {
        this.optimizationAlgorithm = optimizationAlgorithm;
    }

    /**
     * Find a cycle in a graph using the Ant Colony System algorithm.
     * @param graph input graph
     * @param startNode starting node, random if equals to -1
     * @param stopTime running time in seconds
     * @return a cycle over all the nodes in the input graph
     */
    public Cycle ACS(
            Graph graph,
            int startNode,
            int stopTime // default: 180
    ) {
        Ant localBestAnt;        // best ant at each iteration

        long start = System.currentTimeMillis();

        int size = graph.size();

        double val;
        double bestLength;              // length of best solution
        double localMinLength;          // length of the best local solution
        double q0Effective = q0;        // q0 used by ants, it will be reduced if necessary
        boolean bestFound;

        /*
         * The following two parameters are used to avoid that an ant can be stuck on the pheromone. When
         * these counters reach the value maxBestCounter and maxLocalCounter, the ants do not lay down more
         * pheromone, increasing the evaporation and allowing to find strange new worlds and boldly go
         * where no ants has gone before.
         */
        int steadyBestCounter = 0;
        int steadyLocalCounter = 0;

        if (buildAlgorithm == null) buildAlgorithm = new NearestNeighbour();
        if (optimizationAlgorithm == null) optimizationAlgorithm = new Opt2();

        // build an initial solution
        if (startNode == -1) startNode = rand.nextInt(size); // random start

        bestSolution = buildAlgorithm.buildCycle(graph, startNode);
        optimizationAlgorithm.opt(bestSolution);

        bestLength = bestSolution.getLength();

        // setup pheromone parameters
        t0 = 1 / (bestLength * size);
        pheromone = new Pheromone(t0);

        // hatch ants
        ants = new Ant[antNumber];
        for (int i = 0; i < antNumber; i++) {
            ants[i] = new Ant(pheromone, graph, rand);
            ants[i].setOptimizationAlgorithm(optimizationAlgorithm);
        }

        do {
            // place the ants on random nodes
            for (Ant ant : ants)
                ant.initialize(rand.nextInt(size));

            // each ant build a solution adding node after node
            for (int i=0; i < graph.size() -1; i++) {
                for (Ant ant : ants)
                    ant.generateSolution(q0Effective, t0, ro);
            }

            // compute the length of the solutions generated by each ant
            localMinLength = Double.MAX_VALUE;
            localBestAnt = ants[0];
            bestFound = false;
            for (Ant ant : ants) {
                ant.completeTour();             // optimize the found solution
                val = ant.solution.getLength(); // get the optimized solution

                if (val < localMinLength) {
                    // keep the best local solution
                    localMinLength = val;
                    localBestAnt = ant;
                    steadyLocalCounter = 0;
                } else {
                    // count the number of solutions that are worst or equal the local solution
                    steadyLocalCounter++;
                }
            }

            // update the local solution
            localSolution = localBestAnt.solution;

            if(localMinLength < bestLength)  {
                // update the best solution
                bestFound = true;
                bestLength = localMinLength;
                bestSolution = localBestAnt.solution;
                steadyBestCounter = 0;
            } else {
                // count how many times the best solution has been found
                steadyBestCounter++;
            }

            // reduce all the pheromone: t(r,s) <- (1 - alpha) * t(r,s) + alpha * 0
            for (int i=0; i<size; i++) {
                for (int j=i + 1; j<size; j++) {
                    NodeND ni = graph.get(i);
                    NodeND nj = graph.get(i);
                    double currentPheromone = pheromone.get(ni, nj);
                    double t0Threshold = t0 / threshold;

                    if (currentPheromone < t0Threshold) {
                        pheromone.set(ni, nj, t0Threshold);
                    } else {
                        double value = (1 - alpha) * currentPheromone;
                        pheromone.set(ni, nj, value);
                    }
                    pheromone.set(nj, ni, currentPheromone);
                }
            }

            for (int i=0; i<size; i++) {
                NodeND r, s;

                /*
                 * To avoid the overload of pheromone, if the best solution has been found many times the quantity
                 * of pheromone of this solution will we incremented
                 */
                if(steadyBestCounter < maxBestCounter) {
                    r = bestSolution.get(i);
                    s = bestSolution.get(i + 1);
                    pheromone.layDown(r, s, alpha / bestLength);
                }

                /*
                 * To avoid the overload of the best solution, the pheromone of the best local solution will be
                 * incremented if it is different from the best solution and if the solution has been found less
                 * than maxLocalCounter times. If the ants does not found any solution before maxLocalCounter,
                 * then there will be no pheromone deposit.
                 * This procedure helps the exploitation of the local search
                 */
                if((localMinLength != bestLength) && (steadyLocalCounter < maxLocalCounter)) {
                    r = localSolution.get(i);
                    s = localSolution.get(i + 1);
                    pheromone.layDown(r, s, alpha / localMinLength);
                    pheromone.set(s, r, pheromone.get(r, s));
                }
            }

            if(bestFound) {
                q0Effective = q0;              // keep q0 if a better solution has been found...
            } else {
                if(q0Effective > q0Min) {
                    q0Effective -= q0Step;    // ... otherwise decrement it (increasing Exploration)
                }
            }

        } while (System.currentTimeMillis() < start + stopTime * 1000);

        return bestSolution;
    }
}
