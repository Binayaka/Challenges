package challenges.binayaka.genericAlgos.flowField;

import java.awt.Rectangle;
import java.util.ArrayList;

import processing.core.PApplet;

/**
 * This will be the actual runner program
 * 
 * @author Binayaka
 *
 */
public class EvolvingFlowField extends PApplet implements FlowFieldConstants {

	private int lifetime; // How long should each generation live
	/**
	 * Are we in debug state
	 */
	public boolean debug = false;

	private Population population; // Population
	private int lifecycle; // Timer for cycle of generation
	private int recordtime; // Fastest time to target
	private Obstacle target; // Target position
	private Obstacle start; // Start position

	// DNA needs one vector for every spot on the grid
	// (it's like a pixel array, but with vectors instead of colors)
	private int dnasize;

	// an array list to keep track of all the obstacles
	private ArrayList<Obstacle> obstacles;

	private Rectangle newObstacle = null;

	public Obstacle getTarget() {
		return target;
	}

	public Obstacle getStart() {
		return start;
	}

	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}

	public int getDnasize() {
		return dnasize;
	}

	public void settings() {
		size(640, 360);
	}

	public void setup() {
		dnasize = (width / gridScale) * (height / gridScale);
		lifetime = width / 3;

		// Initialize variables
		lifecycle = 0;
		recordtime = lifetime;
		target = new Obstacle(this, width - diam - diam / 2, height / 2 - diam / 2, diam, diam);
		start = new Obstacle(this, diam / 2, height / 2 - diam / 2, diam, diam);

		// Create a population with a mutation rate, and population max
		int popmax = 1000;
		float mutationRate = 0.02f;
		population = new Population(this, mutationRate, popmax);

		// Create the obstacle course
		obstacles = new ArrayList<Obstacle>();

		/*
		 * obstacles.add(new Obstacle(width/4,80,10,height-160));
		 * obstacles.add(new Obstacle(width/2,0,10,height/2-20));
		 * obstacles.add(new
		 * Obstacle(width/2,height-height/2+20,10,height/2-20));
		 * obstacles.add(new Obstacle(2*width/3,height/2-height/8,10,height/4));
		 */
	}

	public void draw() {
		background(255);

		// Draw the target positions
		target.display();

		// Draw the obstacles
		for (Obstacle obs : obstacles) {
			obs.display();
		}

		// If the generation hasn't ended yet
		if (lifecycle < lifetime) {
			population.live(obstacles);
			if ((population.targetReached()) && (lifecycle < recordtime)) {
				recordtime = lifecycle;
			}
			lifecycle++;
			// Otherwise a new generation
		} else {
			lifecycle = 0;
			population.calcFitness();
			population.naturalSelection();
			population.generate();
		}

		// Display some info
		textAlign(RIGHT);
		fill(0);
		text("Generation #:" + population.getGenerations(), width - 10, 18);
		text("Cycles left:" + ((lifetime - lifecycle)), width - 10, 36);
		text("Record cycles: " + recordtime, width - 10, 54);

		if (newObstacle != null) {
			rect(newObstacle.x, newObstacle.y, newObstacle.width, newObstacle.height);
		}

	}

	public void keyPressed() {
		if (key == 'd') {
			debug = !debug;
		}
	}

	public void mousePressed() {
		newObstacle = new Rectangle(mouseX, mouseY, 0, 0);
	}

	public void mouseDragged() {
		newObstacle.width = mouseX - newObstacle.x;
		newObstacle.height = mouseY - newObstacle.y;
	}

	public void mouseReleased() {
		obstacles.add(new Obstacle(this, newObstacle));
		newObstacle = null;
	}

}
