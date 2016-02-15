package ch.zeyger.algorithms.opt;

import ch.zeyger.algorithms.data.structures.Cycle;

import java.util.Random;

/**
 * Author:  Claudio Bonesana
 * Date:    15.02.2016
 * Project: Algorithms
 */
public class Opt2Disruptor extends Opt2 implements Disruptor {

    protected Random rand;
    protected long seed;

    protected Opt opt = new Opt2();

    /**
     * Empty constructor.
     */
    public Opt2Disruptor() {
        this.rand = new Random();
    }

    /**
     * Constructor with fixed seed random initialization.
     * @param seed random seed
     */
    public Opt2Disruptor(long seed) {
        this.seed = seed;
        this.rand = new Random(seed);
    }

    @Override
    public void disrupt(Cycle cycle) {
        int p1 = rand.nextInt(cycle.size());
        int p2 = rand.nextInt(cycle.size());

        while (p2 == p1 || p2 == p1 +1 || p2 == p1 -1)
            p2 = rand.nextInt(cycle.size());

        opt.exchange(p1, p2, cycle);
    }

    @Override
    public void disrupt(Cycle cycle, int times) {
        for (int i = 0; i < times; i++)
            disrupt(cycle);
    }
}
