package ch.zeyger.algorithms.builders.neighborbased;

import ch.zeyger.algorithms.TSP;
import ch.zeyger.algorithms.builders.neighborbased.farthest.FarthestNeighbor;
import ch.zeyger.algorithms.builders.neighborbased.nearest.NearestNeighbor;
import ch.zeyger.algorithms.data.structures.Cycle;
import ch.zeyger.algorithms.opt.Opt2;
import org.junit.After;
import org.junit.Test;

/**
 * Author:  Claudio Bonesana
 * Date:    14.02.2016
 * Project: Algorithms
 */
public class NeighborhoodTest extends TSP {

    Cycle cycle;

    @After
    public void tearDown() throws Exception {
        System.out.println(cycle.getLength());

        for (int i=0; i<graph.size(); i++) {
            System.out.println(cycle.get(i).get(0) + " " + cycle.get(i).get(1) + " " + cycle.get(i).get(2));
        }
    }

    @Test
    public void TestRandomNeighbor() {
//        BuilderNeighbor nb = new BuilderNeighbor(new NearestSearch(), (graph1, nodeVisited, current) -> {
//            List<NeighborhoodNode<NodeND>> neighbourhood = new ArrayList<>();
//            for (int i = 0; i < graph1.size(); i++){
//                NodeND node = graph1.get(i);
//                double d = graph1.distance(current, node);
//                if (!nodeVisited.contains(node) && d < 10000000.0){
//                    NeighborhoodNode<NodeND> nNode = new NeighborhoodNode<>();
//                    nNode.node = node;
//                    nNode.distance = d;
//                    neighbourhood.add(nNode);
//                }
//            }
//
//            return neighbourhood;
//        });
        NearestNeighbor nb = new NearestNeighbor();
        cycle = nb.buildCycle(graph, 0);
        new Opt2().opt(cycle);
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