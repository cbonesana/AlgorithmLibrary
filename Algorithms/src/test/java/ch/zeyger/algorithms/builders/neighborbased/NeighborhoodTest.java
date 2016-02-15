package ch.zeyger.algorithms.builders.neighborbased;

import ch.zeyger.algorithms.TSP;
import ch.zeyger.algorithms.builders.neighborbased.farthest.FarthestNeighbor;
import ch.zeyger.algorithms.builders.neighborbased.nearest.NearestNeighbor;
import ch.zeyger.algorithms.data.structures.Cycle;
import ch.zeyger.algorithms.data.structures.Graph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Author:  Claudio Bonesana
 * Date:    14.02.2016
 * Project: Algorithms
 */
public class NeighborhoodTest extends TSP {

    public Cycle cycle;
    public Graph graph;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        graph = graphs.get("plain3.tsp");
    }

    @After
    public void tearDown() throws Exception {
        assert (cycle.getLength() == best);
        System.out.println(cycle.getLength());

        for (int i=0; i<graph.size(); i++) {
            System.out.println(cycle.get(i).get(0) + " " + cycle.get(i).get(1));
        }
    }

    @Test
    public void TestNearestNeighbor() {
        NearestNeighbor nn = new NearestNeighbor();
        cycle = nn.buildCycle(graph, 0);
    }

    @Test
    public void TestFarthestNeighbor() {
        FarthestNeighbor fn = new FarthestNeighbor();
        cycle = fn.buildCycle(graph, 0);

    }
}