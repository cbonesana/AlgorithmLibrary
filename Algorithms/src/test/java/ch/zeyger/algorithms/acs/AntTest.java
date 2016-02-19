package ch.zeyger.algorithms.acs;

import ch.zeyger.algorithms.TSP;
import ch.zeyger.algorithms.builders.random.RandomBuilder;
import ch.zeyger.algorithms.data.structures.Cycle;
import ch.zeyger.algorithms.data.structures.Graph;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.Before;
import org.junit.Test;

/**
 * Author:  Claudio Bonesana
 * Date:    19.02.2016
 * Project: Algorithms
 */
public class AntTest extends TSP {

    Ant ant;
    Pheromone pheromone;
    Graph graph;
    Cycle input;

    double t0;
    double ro = 0.1;
    double q0 = 0.5;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        graph = graphs.get("berlin52.tsp");
        input = new RandomBuilder(0).buildCycle(graph);
        t0 = input.getLength() / input.size();
        pheromone = new Pheromone(t0);
        RandomDataGenerator rand = new RandomDataGenerator();
        rand.reSeed(0);
        ant = new Ant(pheromone, graph, rand);
    }

    @Test
    public void testAnt() throws Exception {
        for (int k = 0; k < 100; k++) {
            ant.initialize(0);
            for (int i=0; i < graph.size() -1; i++)
                ant.generateSolution(q0, t0, ro);

            pheromone.pullUp(1);

            double l = ant.solution.getLength();
            System.out.println(String.format("%3d %8.4f", k, l));
        }
    }
}