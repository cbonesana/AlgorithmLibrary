package ch.zeyger.algorithms.builders.random;

import ch.zeyger.algorithms.builders.Builders;
import ch.zeyger.algorithms.data.structures.Cycle;
import ch.zeyger.algorithms.data.structures.Graph;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Author:  Claudio Bonesana
 * Date:    15.02.2016
 * Project: Algorithms
 */
public class RandomBuilder implements Builders {

    protected Random rand;
    protected long seed;

    public RandomBuilder(long seed) {
        this.seed = seed;
        rand = new Random(seed);
    }

    @Override
    public Cycle buildCycle(Graph graph) {
        return buildCycle(graph, rand.nextInt(graph.size()));
    }

    @Override
    public Cycle buildCycle(Graph graph, int start) {
        Cycle cycle = new Cycle();
        cycle.add(graph.get(start));

        Set<Integer> chosen = new HashSet<>();
        chosen.add(start);

        for (int i = 1; i < graph.size(); i++) {
            int next;
            do {
                next = rand.nextInt(graph.size());
            } while (chosen.contains(next));
            cycle.add(graph.get(next));
            chosen.add(next);
        }

        return cycle;
    }
}
