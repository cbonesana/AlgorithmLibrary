package ch.zeyger.algorithms.acs;

import ch.zeyger.algorithms.TSP;
import ch.zeyger.algorithms.data.structures.Cycle;
import ch.zeyger.algorithms.data.structures.Graph;
import org.junit.Before;
import org.junit.Test;

/**
 * Author:  Claudio Bonesana
 * Date:    14.02.2016
 * Project: Algorithms
 */
public class AntColonySystemTest extends TSP {

    int ants            = 2; // 4;
    int stopTime        = 180;   // 3 minutes
    int maxBestCounter  = 30;
    int maxLocalCounter = 100;
    long seed           = 42;
    double alpha        = 0.1;
    double ro           = 0.1;
    double q0           = 0.95;
    double q0Min        = 0.4;
    double q0Step       = 0.005;
    double threshold    = 10.0;

    Graph graph;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        graph = graphs.get("berlin52.tsp");
    }

    @Test
    public void testACS() throws Exception {
        AntColonySystem acs = new AntColonySystem(ants, alpha, ro, q0, q0Min, q0Step, threshold, maxBestCounter, maxLocalCounter, seed);
        Cycle cycle = acs.ACS(graph, -1, stopTime);

        System.out.println(cycle.getLength());
        assert (cycle.getLength() == 6110);
    }
}