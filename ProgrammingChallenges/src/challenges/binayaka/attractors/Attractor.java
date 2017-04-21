package challenges.binayaka.attractors;

import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class Attractor {

	final PApplet main;
	final float posX, posY;
	private PVector position;
	float attractorDistance = 50f;
	float captureDistance = 5f;

	public Attractor(PApplet core, float x, float y) {
		main = core;
		posX = x;
		posY = y;
		position = new PVector(x, y);
	}

	public void applyForce(List<Particle> particles) {
		for (Particle p : particles) {
			if (PApplet.dist(position.x, position.y, p.pos.x, p.pos.y) < attractorDistance) {
				PVector force = PVector.sub(p.pos, this.position);
				float ds = force.magSq();
				float dist = PApplet.dist(this.position.x, this.position.y, p.pos.x, p.pos.y);
				ds = ds / (dist * dist);
				ds = PApplet.constrain(ds, 0.1f, 0.6f);
				force.setMag(ds);
				p.getAcceleration().add(force);
			}

			if (PApplet.dist(position.x, position.y, p.pos.x, p.pos.y) <= captureDistance) {
				p.getAcceleration().mult(0);
				p.getVelocity().mult(0);
			}
		}
	}

}
