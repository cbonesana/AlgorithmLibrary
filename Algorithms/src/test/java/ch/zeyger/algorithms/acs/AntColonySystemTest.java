package ch.zeyger.algorithms.acs;

import ch.zeyger.algorithms.TSP;
import ch.zeyger.algorithms.data.structures.Cycle;
import org.junit.Test;

/**
 * Author:  Claudio Bonesana
 * Date:    14.02.2016
 * Project: Algorithms
 */
public class AntColonySystemTest extends TSP {

    int ants            = 4;
    int stopTime        = 180;
    int maxBestCounter  = 30;
    int maxLocalCounter = 100;
    long seed           = 42;
    double alpha        = 0.1;
    double ro           = 0.1;
    double q0           = 0.95;
    double q0Min        = 0.4;
    double q0Step       = 0.005;
    double threshold    = 10.0;

    @Test
    public void testACS() throws Exception {
        AntColonySystem acs = new AntColonySystem(ants, alpha, ro, q0, q0Min, q0Step, threshold, maxBestCounter, maxLocalCounter, seed);
        Cycle cycle = acs.ACS(graph, -1, stopTime);

        System.out.println(cycle.getLength());
        assert (cycle.getLength() == 6110);
    }
}