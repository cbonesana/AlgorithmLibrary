package ch.zeyger.algorithms.acs;

import ch.zeyger.algorithms.data.nodes.Node2D;
import ch.zeyger.algorithms.data.structures.Cycle;
import ch.zeyger.algorithms.data.structures.Graph;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

/**
 * Author:  Claudio Bonesana
 * Date:    14.02.2016
 * Project: Algorithms
 */
public class AntColonySystemTest {

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

    Graph graph;

    @Before
    public void setUp() throws Exception {

        graph = new Graph();
        URL res = this.getClass().getClassLoader().getResource("tsp/ch130.tsp");

        try (Scanner scanner = new Scanner(new File(res.getFile()))) {
            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); i++;

                if (i < 8)
                    continue;
                if (line.startsWith("EOF"))
                    break;

                String[] chunk = line.split(" ");
                int k = Integer.parseInt(chunk[0]);
                double x = Double.parseDouble(chunk[1]);
                double y = Double.parseDouble(chunk[2]);
                Node2D node = new Node2D(x, y);
                graph.add(k, node);
            }
        } catch (Exception ignored) {}
    }

    @Test
    public void testACS() throws Exception {
        AntColonySystem acs = new AntColonySystem(ants, alpha, ro, q0, q0Min, q0Step, threshold, maxBestCounter, maxLocalCounter, seed);
        Cycle cycle = acs.ACS(graph, -1, stopTime);

        System.out.println(cycle.getLength());
        assert (cycle.getLength() == 6110);
    }
}