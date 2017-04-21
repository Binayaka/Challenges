package challenges.binayaka.attractors;

import challenges.binayaka.common.Drawable;
import processing.core.PApplet;
import processing.core.PVector;

public class Particle implements Drawable {

	final PApplet main;
	PVector pos;
	private PVector velocity;
	private PVector acceleration;
	private final static int PARTICLE_SIZE = 5;

	public Particle(PApplet core, float x, float y) {
		main = core;
		pos = new PVector(x, y);
		velocity = new PVector(main.random(-0.5f, 0.5f), main.random(-0.5f, 0.5f));
		acceleration = new PVector(0.1f, 0.5f);
	}

	@Override
	public void show() {
		main.ellipse(pos.x, pos.y, PARTICLE_SIZE, PARTICLE_SIZE);
	}

	@Override
	public void update() {
		pos.add(velocity);
		velocity.add(acceleration);
		acceleration.mult(0); // clear out acceleration
	}

	public PVector getVelocity() {
		return velocity;
	}

	public PVector getAcceleration() {
		return acceleration;
	}

}
