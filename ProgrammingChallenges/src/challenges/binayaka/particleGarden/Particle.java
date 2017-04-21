package challenges.binayaka.particleGarden;

import challenges.binayaka.common.Drawable;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * This will be the individual particles that we see on the screen
 * 
 * @author Binayaka
 *
 */
public class Particle implements Drawable {
	private final PApplet main;
	PVector pos;
	private PVector velocity;
	private PVector acceleration;
	private final static int PARTICLE_SIZE = 5;
	private static int ID_GEN = 0;
	private int id;
	private PVector prevAcc;

	public Particle(PApplet core) {
		main = core;
		generatePositionVelocityAndAcceleration();
		id = ID_GEN++;
		ID_GEN = ID_GEN % Integer.MAX_VALUE;
	}

	private void generatePositionVelocityAndAcceleration() {
		pos = new PVector(main.random(main.width), main.random(main.height));
		velocity = new PVector(main.random(-0.5f, 0.5f), main.random(-0.5f, 0.5f));
		acceleration = new PVector(0.1f, 0.5f);
		acceleration.limit(4f);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Particle other = (Particle) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public void show() {
		main.ellipse(pos.x, pos.y, PARTICLE_SIZE, PARTICLE_SIZE);
	}

	@Override
	public void update() {
		/*velocity.limit(5f);
		acceleration.limit(2f);*/
		pos.add(velocity);
		velocity.add(acceleration);
		prevAcc = acceleration.copy();
		acceleration.mult(0); // clear out acceleration
		if (hasBecomeLost()) {
			generatePositionVelocityAndAcceleration();
			id = ID_GEN++;
			ID_GEN = ID_GEN % Integer.MAX_VALUE;
		}
	}

	/**
	 * If this particle has
	 * 
	 * @return
	 */
	public boolean hasBecomeLost() {
		if ((pos.x < 0 || pos.x > main.width) || (pos.y < 0 || pos.y > main.height)) {
			return true;
		} else {
			return false;
		}
	}

	public void applyForce(PVector force) {
		acceleration.add(force);
	}

	public PVector getVelocity() {
		return velocity;
	}

	/**
	 * This will attract the particle
	 * 
	 * @param target
	 */
	public void attracted(PVector target) {
		PVector force = PVector.sub(target, this.pos);
		float ds = force.magSq();
		float dist = PApplet.dist(this.pos.x, this.pos.y, target.x, target.y);
		ds = ds / (dist * dist);
		ds = PApplet.constrain(ds, 0.2f, 0.5f);
		force.setMag(ds);
		this.acceleration.add(force);
	}
	
	/**
	 * This will repulse the particle
	 * 
	 * @param target
	 */
	public void repulse(PVector target) {
		PVector force = PVector.sub(target, this.pos);
		float ds = force.magSq();
		float dist = PApplet.dist(this.pos.x, this.pos.y, target.x, target.y);
		ds = ds / (dist * dist);
		//ds = PApplet.constrain(ds, 0.2f, 0.5f);
		force.setMag(ds * -1);
		this.acceleration.add(force);
	}

	public void displayStats() {
		System.out.println("Velocity is " + velocity + ", acceleration is " + prevAcc);
		prevAcc = null;
	}

}
