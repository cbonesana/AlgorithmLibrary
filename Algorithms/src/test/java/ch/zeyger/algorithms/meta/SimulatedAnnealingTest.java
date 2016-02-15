package ch.zeyger.algorithms.meta;

import ch.zeyger.algorithms.TSP;
import ch.zeyger.algorithms.builders.random.RandomBuilder;
import ch.zeyger.algorithms.data.structures.Cycle;
import ch.zeyger.algorithms.data.structures.Graph;
import org.junit.Before;
import org.junit.Test;

/**
 * Author:  Claudio Bonesana
 * Date:    15.02.2016
 * Project: Algorithms
 */
public class SimulatedAnnealingTest extends TSP {

    Graph graph;
    Cycle input;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        graph = graphs.get("ch130.tsp");
        input = new RandomBuilder(0).buildCycle(graph);
    }

    @Test
    public void testSimulatedAnnealing() throws Exception {
        SimulatedAnnealing sa = new SimulatedAnnealing(0);
        Cycle output = sa.metalAnnealing(input);

        System.out.println(output.getLength());
    }
}