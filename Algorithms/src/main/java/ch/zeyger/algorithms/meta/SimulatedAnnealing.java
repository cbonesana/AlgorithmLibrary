package ch.zeyger.algorithms.meta;

import ch.zeyger.algorithms.data.structures.Cycle;
import ch.zeyger.algorithms.opt.Disruptor;
import ch.zeyger.algorithms.opt.Opt2Disruptor;

import java.util.Random;

import static ch.zeyger.algorithms.utils.Timings.now;

/**
 * Author:  Claudio Bonesana
 * Date:    15.02.2016
 * Project: Algorithms
 *
 * The Simulated Annealing algorithm simulates the behaviour of hot metal that cool. During this
 * period of time the burning metal creates vortex of warm fluid that moves inside the material
 * until the substance solidify.
 */
public class SimulatedAnnealing {

    /** Amount of temperature saved each iteration. */
    protected double dT = 0.97;
    /** Initial temperature of the metal. */
    protected double T = 100;
    /** Minimal temperature at which the metal is solid. */
    protected double minT = 0.0;
    /** Minimum gain to accept a new solution. */
    protected double minGain = 0.0;

    /** Time limits in milliseconds */
    protected long stopTime = 180000;
    /** Random seed. */
    protected long seed;

    protected Random rand;

    protected Disruptor disruptor;

    /**
     * Creates an instance and initialize the random with no seed.
     */
    public SimulatedAnnealing() {
        rand = new Random();
    }

    /**
     * Creates an instance and initialize the random with the given seed.
     * @param seed random seed
     */
    public SimulatedAnnealing(long seed) {
        this.seed = seed;
        rand = new Random(seed);
    }

    /**
     * Set the quantity of temperature saved after an iteration.
     * @param dT if lesser than 1.0, the temperature decrease, otherwise it increases.
     */
    public void setdT(double dT) {
        this.dT = dT;
    }

    /**
     * Set initial temperature amount. Default value is 100.0.
     * @param t the new temperature
     */
    public void setT(double t) {
        T = t;
    }

    /**
     * Minimum temperature after the metal is considered solid. Default value is 0.0.
     * @param minT the new lower temperature
     */
    public void setMinT(double minT) {
        this.minT = minT;
    }

    /**
     * To be accepted a new solution must produce a gain.
     * @param minGain a gain is considered a negative value
     */
    public void setMinGain(double minGain) {
        this.minGain = minGain;
    }

    /**
     * Time limit.
     * @param stopTime in milliseconds.
     */
    public void setStopTime(long stopTime) {
        this.stopTime = stopTime;
    }

    /**
     * A disruptor is used to disrupt (perturbate) the solution randomly searching
     * for a better one.
     * @param disruptor the new disruptor object
     */
    public void setDisruptor(Disruptor disruptor) {
        this.disruptor = disruptor;
    }

    /**
     * Execute a simulated annealing
     * @param input an already existent cycle
     * @return the optimized solution
     */
    public Cycle metalAnnealing(Cycle input) {
        long stop = now() + stopTime;

        double dE;

        Cycle best = input;                     // initial solution
        Cycle curr = best;                      // current <- best;
        Cycle next;

        double fBest = best.getLength();        // length of initial solution

        if (disruptor == null)
            disruptor = new Opt2Disruptor();

        // we continue until the metal is cold or we hit the time limit
        while (T > minT && now() < stop) {
            int k = 0;
            int interrupt = input.size() * (rand.nextInt(100) +1);

            // we set an internal equilibrium prior to reduce the temperature
            while (k < interrupt && now() < stop) {
                next = new Cycle(curr);
                disruptor.disrupt(next);     // next take a new solution by perturbation

                double fCurr = curr.getLength();
                double fNext = next.getLength();

                dE = fNext - fCurr;             // dE < f(next) - f(curr)

                if (dE < minGain) {
                    // we have a gain in the solution
                    curr = next;

                    if (fNext < fBest){
                        // the new solution is alto better than the best solution
                        best = next;
                        fBest = fNext;
                    }
                } else {
                    // within a random choice, we keep a worst solution to expand the search space
                    double r = rand.nextDouble();
                    if (r < Math.exp(-dE/T)){
                        curr = next;
                    }
                }
                k++;
            }
            // lower temperature
            T *= dT;
        }

        return best;
    }
}
