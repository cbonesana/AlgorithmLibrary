package ch.zeyger.algorithms;

import ch.zeyger.algorithms.data.nodes.Node2D;
import ch.zeyger.algorithms.data.structures.Graph;
import org.junit.Before;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

/**
 * Author:  Claudio Bonesana
 * Date:    14.02.2016
 * Project: Algorithms
 */
public class TSP {

    public Graph graph;

    @Before
    public void setUp() throws Exception {

        graph = new Graph();
//        URL res = this.getClass().getClassLoader().getResource("tsp/ch130.tsp");
        URL res = this.getClass().getClassLoader().getResource("tsp/ed_rares_2.tsp");

        if (res == null) return;

        try (Scanner scanner = new Scanner(new File(res.getFile()))) {
            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); i++;

                if (i < 8) continue;
                if (line.startsWith("EOF")) break;

                String[] chunk = line.split(" ");
                int k = Integer.parseInt(chunk[0]);
                double x = Double.parseDouble(chunk[1]);
                double y = Double.parseDouble(chunk[2]);
//                double z = Double.parseDouble(chunk[3]);
//                double l = Double.parseDouble(chunk[4]);
//                if (l > 5000) continue;
//                Node3D node = new Node3D(k, x, y, z);
                Node2D node = new Node2D(k, x, y);
                graph.add(k, node);
            }
            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
