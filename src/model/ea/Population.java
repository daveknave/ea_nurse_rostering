package model.ea;

import java.util.*;

/**
 * This class represents a Population (a pool of individuals) for the evolutionary algorithm.
 */
public class Population {
    /**
     * List of Individual instances in this population pool.
     */
    private List<Individual> pool = new ArrayList<Individual>();

    /**
     * The overall fitness value of this population
     */
    private double overallFitness = 0;

    /**
     * Last global identifier.
     */
    private static long lastId = 0;

    /**
     * Identifier for this instance.
     */
    private long id = lastId++;

    /**
     * Returns the identifier of this instance.
     * @return Identifier
     */
    public long getId() {
        return id;
    }

    /**
     * Default constructor.
     */
    public Population() {}

    /**
     * Returns a deep copy of this instance.
     * @param population Population instance to copy
     * @return Population instance as deep copy
     */
    public static Population copy(Population population) {
        Population copyInstance = new Population();
        // deep copy old generation
        for (Individual individual: population.pool) {
            copyInstance.pool.add(Individual.copy(individual));
        }

        // copy overall fitness, if we have a specific value
        copyInstance.overallFitness = population.overallFitness;

        return copyInstance;
    }

    /**
     * Returns the pool of this population.
     * @return List of Individual instances
     */
    public List<Individual> getPool() {
        return pool;
    }

    /**
     * Returns the overall fitness of this population. The lower the value, the better.
     * @return Overall fitness.
     */
    public double getOverallFitness() {
        // if overall fitness value is not calculated, benchmark this population now
        if (overallFitness == 0) {
            benchmark();
        }

        return overallFitness;
    }

    /**
     * Adds an Individual instance to the pool list.
     * @param individual Individual instance
     */
    public void addIndividualToPool(Individual individual) {
        if (individual.isFeasible()) {
            pool.add(individual);
        }
    }

    /**
     * Adds a list of Individual instance to the pool list.
     * @param individuals List of Individual instances
     */
    void addIndividualsToPool(List<Individual> individuals) {
        for (Individual individual: individuals) {
            addIndividualToPool(individual);
        }
    }

    /**
     * Calculates the fitness of every individual.
     */
    void benchmark() {
        overallFitness = 0;
        for (Individual individual: pool) {
            overallFitness += individual.getFitness();
        }
    }

    /**
     * Sorts this population by fitness and returns itself.
     * @return Population this
     */
    public Population sortByFitness() {
        Collections.sort(pool, new Comparator<Individual>() {
            @Override
            public int compare(Individual individualA, Individual individualB) {
                return individualA.getFitness() < individualB.getFitness() ? -1
                        : individualA.getFitness() == individualB.getFitness() ? 0
                        : 1;
            }
        });
        return this;
    }

    /**
     * Returns the individual with the best fitness from this population.
     * @return Best individual
     */
    public Individual getBestIndividual() {
        return sortByFitness().pool.get(0);
    }
}
