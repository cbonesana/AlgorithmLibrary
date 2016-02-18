package ch.zeyger.algorithms.opt;

import ch.zeyger.algorithms.TSP;
import ch.zeyger.algorithms.builders.random.RandomBuilder;
import ch.zeyger.algorithms.data.nodes.NodeND;
import ch.zeyger.algorithms.data.structures.Cycle;
import org.junit.Before;
import org.junit.Test;

/**
 * Author:  Claudio Bonesana
 * Date:    18.02.2016
 * Project: Algorithms
 */
public class Opt2Test extends TSP {

    Opt2 opt2;
    Cycle input;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        opt2 = new Opt2();
        input = new RandomBuilder(0).buildCycle(graphs.get("ch130.tsp"), 0);
    }

    @Test
    public void testComputeGain() throws Exception {
        double d1 = opt2.computeGain(10, 20, input);
        double d2 = opt2.computeGain(20, 30, input);
        double d3 = opt2.computeGain(40, 50, input);
        System.out.println(d1 + " " + d2 + " " + d3);
    }

    @Test
    public void testExchange() throws Exception {
        int x = 2, y = 5;

        NodeND i1 = input.get(x);
        NodeND j1 = input.get(y);

        opt2.exchange(x, y, input);

        NodeND i2 = input.get(x);
        NodeND j2 = input.get(y);

        assert (i1.equals(j2) && j1.equals(i2));
    }

    @Test
    public void testOpt2() throws Exception {
        opt2.opt(input, 100);
        System.out.println(input.getLength());
    }
}