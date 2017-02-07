package challenges.binayaka.genericAlgos.flowField;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

/**
 * This will be dependent on the DNA and its fitness
 * 
 * @author Binayaka
 *
 */
public class Rocket implements FlowFieldConstants {
	// All of our physics stuff
	private PVector position;
	private PVector velocity;
	private PVector acceleration;
	private float r;
	private float recordDist;

	private float fitness;
	private DNA dna;

	private boolean stopped; // Am I stuck?
	private boolean dead; // Did I hit an obstacle?

	private int finish; // What was my finish? (first, second, etc. . . )
	private final EvolvingFlowField main;

	// constructor
	public Rocket(EvolvingFlowField core, PVector l, DNA dna_) {
		main = core;
		acceleration = new PVector();
		velocity = new PVector();
		position = l.copy();
		r = 2;
		dna = dna_;
		stopped = false;
		finish = INIT_MAX_FINISH_VAL; // Some high number to begin with
		recordDist = main.width;
	}

	// FITNESS FUNCTION
	// distance = distance from target
	// finish = what order did i finish (first, second, etc. . .)
	// f(distance,finish) = (1.0f / finish^1.5) * (1.0f / distance^6);
	// a lower finish is rewarded (exponentially) and/or shorter distance to
	// target (exponentially)
	public void calcFitness() {
		float d = recordDist;
		if (d < diam / 2) {
			d = 1.0f;
		}
		// Reward finishing faster and getting closer
		fitness = (1.0f / PApplet.pow(finish, (float) 1.5)) * (1 / (PApplet.pow(d, 6)));
		// if (dead) fitness = 0;
	}

	public void setFinish(int f) {
		finish = f;
	}

	// Run in relation to all the obstacles
	// If I'm stuck, don't bother updating or checking for intersection
	public void run(ArrayList<Obstacle> o) {
		if (!stopped) {
			update();
			// If I hit an edge or an obstacle
			if ((borders()) || (obstacles(o))) {
				stopped = true;
				setDead(true);
			}
		}
		// Draw me!
		display();
	}

	// Did I hit an edge?
	boolean borders() {
		if ((position.x < 0) || (position.y < 0) || (position.x > main.width) || (position.y > main.height)) {
			return true;
		} else {
			return false;
		}
	}

	// Did I make it to the target?
	public boolean finished() {
		float d = PApplet.dist(position.x, position.y, main.getTarget().getR().x, main.getTarget().getR().y);
		if (d < recordDist) {
			recordDist = d;
		}
		if (main.getTarget().contains(position)) {
			stopped = true;
			return true;
		}
		return false;
	}

	// Did I hit an obstacle?
	public boolean obstacles(ArrayList<Obstacle> o) {
		for (Obstacle obs : o) {
			if (obs.contains(position)) {
				return true;
			}
		}
		return false;
	}

	public void update() {
		if (!finished()) {
			// Where are we? Our position will tell us what steering vector to
			// look up in our DNA;
			int x = (int) position.x / gridScale;
			int y = (int) position.y / gridScale;
			x = PApplet.constrain(x, 0, main.width / gridScale - 1);
			// Make sure we are not off the edge
			y = PApplet.constrain(y, 0, main.height / gridScale - 1);
			// Make sure we are not off the edge

			// Get the steering vector out of our genes in the right spot
			// A little Reynolds steering here
			PVector desired = dna.genes[x + y * (main.width / gridScale)].copy();
			desired.mult(maxspeed);
			PVector steer = PVector.sub(desired, velocity);
			acceleration.add(steer);
			acceleration.limit(maxforce);

			velocity.add(acceleration);
			velocity.limit(maxspeed);
			position.add(velocity);
			acceleration.mult(0);
		}
	}

	public void display() {
		// fill(0,150);
		// stroke(0);
		// ellipse(position.x,position.y,r,r);
		float theta = velocity.heading() + PConstants.PI / 2;
		main.fill(200, 100);
		main.stroke(0);
		main.pushMatrix();
		main.translate(position.x, position.y);
		main.rotate(theta);
		main.beginShape(PConstants.TRIANGLES);
		main.vertex(0, -r * 2);
		main.vertex(-r, r * 2);
		main.vertex(r, r * 2);
		main.endShape();
		main.popMatrix();
	}

	public void highlight() {
		main.stroke(0);
		main.line(position.x, position.y, main.getTarget().getR().x, main.getTarget().getR().y);
		main.fill(255, 0, 0, 100);
		main.ellipse(position.x, position.y, 16, 16);
	}

	public float getFitness() {
		return fitness;
	}

	public DNA getDNA() {
		return dna;
	}

	public float getRecordDist() {
		return recordDist;
	}

	public boolean stopped() {
		return stopped;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
}
