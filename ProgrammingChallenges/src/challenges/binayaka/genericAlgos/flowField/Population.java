package challenges.binayaka.genericAlgos.flowField;

import java.util.ArrayList;

import processing.core.PVector;

/**
 * A class to describe a population of "creatures"
 * 
 * @author Binayaka
 *
 */
public class Population implements FlowFieldConstants {
	float mutationRate; // Mutation rate
	Rocket[] population; // Array to hold the current population
	ArrayList<Rocket> darwin; // ArrayList which we will use for our "mating
								// pool"
	int generations; // Number of generations

	int order; // Keep track of the order of creature's finishing the maze

	private final EvolvingFlowField main;

	// Initialize the population
	public Population(EvolvingFlowField core, float m, int num) {
		main = core;
		mutationRate = m;
		population = new Rocket[num];
		darwin = new ArrayList<Rocket>();
		generations = 0;
		// make a new set of creatures
		for (int i = 0; i < population.length; i++) {
			PVector position = new PVector(main.getStart().getR().x + main.getStart().getR().width / 2,
					main.getStart().getR().y + main.getStart().getR().height / 2);
			population[i] = new Rocket(core, position, new DNA(main, main.getDnasize()));
		}
		order = 1; // The first one to finish will be #1
	}

	public void live(ArrayList<Obstacle> o) {
		// For every creature

		float record = 100000;
		int closest = 0;

		for (int i = 0; i < population.length; i++) {
			// If it finishes, mark it down as done!
			if ((population[i].finished())) {
				population[i].setFinish(order);
				order++;
			}
			// Run it
			population[i].run(o);

			if (population[i].getRecordDist() < record) {
				// && !population[i].dead) {
				record = population[i].getRecordDist();
				closest = i;
			}
		}

		population[closest].highlight();
		// Drawing one example of the DNA
		if (main.debug) {
			population[closest].getDNA().debugDraw();
		}
	}

	// Did anything finish?
	public boolean targetReached() {
		for (int i = 0; i < population.length; i++) {
			if (population[i].finished())
				return true;
		}
		return false;
	}

	// Calculate fitness for each creature
	public void calcFitness() {
		for (int i = 0; i < population.length; i++) {
			population[i].calcFitness();
		}
		order = 1; // Hmmm, awkward place for this, we have to reset this for
					// the next generation
	}

	// Generate a mating pool
	@SuppressWarnings("unused")
	public void naturalSelection() {
		// Clear the ArrayList
		darwin.clear();

		// Calculate total fitness of whole population
		float totalFitness = getTotalFitness();
		float avgFitness = totalFitness / population.length;

		// Calculate normalized fitness for each member of the population
		// Based on normalized fitness, each member will get added to the mating
		// pool a certain number of times a la roulette wheel
		// A higher fitness = more entries to mating pool = more likely to be
		// picked as a parent
		// A lower fitness = fewer entries to mating pool = less likely to be
		// picked as a parent
		int count = 0;
		for (int i = 0; i < population.length; i++) {
			float fitness = population[i].getFitness();
			// if (fitness > avgFitness) {
			count++;
			float fitnessNormal = fitness / totalFitness;
			int n = (int) (fitnessNormal * 50000); // Arbitrary multiplier,
													// consider mapping fix
			for (int j = 0; j < n; j++) {
				darwin.add(population[i]);
			}
			// }
		}
		// println("Total: " + count + " " + population.length);
	}

	// Making the next generation
	public void generate() {
		// Refill the population with children from the mating pool
		for (int i = 0; i < population.length; i++) {
			int m = (int) main.random(darwin.size());
			int d = (int) main.random(darwin.size());
			// Pick two parents
			Rocket mom = darwin.get(m);
			Rocket dad = darwin.get(d);
			// Get their genes
			DNA momgenes = mom.getDNA();
			DNA dadgenes = dad.getDNA();
			// Mate their genes
			DNA child = momgenes.crossover(dadgenes);
			// Mutate their genes
			child.mutate(mutationRate);
			// Fill the new population with the new child
			PVector position = new PVector(main.getStart().getR().x + main.getStart().getR().width / 2,
					main.getStart().getR().y + main.getStart().getR().height / 2);
			population[i] = new Rocket(main, position, child);
		}
		generations++;
	}

	public int getGenerations() {
		return generations;
	}

	// compute total fitness for the population
	public float getTotalFitness() {
		float total = 0;
		for (int i = 0; i < population.length; i++) {
			total += population[i].getFitness();
		}
		return total;
	}
}
