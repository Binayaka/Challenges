package challenges.binayaka.starrySky;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Comet {
	ArrayList<Particle> particles; // An arraylist for all the particles
	PVector origin; // An origin point for where particles are birthed
	PImage img;
	PApplet main;

	Comet(PApplet _main, int num, PVector v, PImage img_) {
		main = _main;
		particles = new ArrayList<Particle>(); // Initialize the arraylist
		origin = v.copy(); // Store the origin point
		img = img_;
		for (int i = 0; i < num; i++) {
			particles.add(new Particle(main, origin, img, this));
			// Add "num" amount of particles to the arraylist
		}
	}

	public void show() {
		for (int i = particles.size() - 1; i >= 0; i--) {
			Particle p = particles.get(i);
			p.show();
			if (p.isDead()) {
				particles.remove(i);
			}
		}
	}

	// Method to add a force vector to all particles currently in the system
	public void applyForce(PVector dir) {
		// apply the force to the opp direction
		PVector opp = new PVector(-dir.x, -dir.y);
		// Enhanced loop!!!
		for (Particle p : particles) {
			p.applyForce(opp);
		}
		origin.add(dir);
	}

	void addParticle() {
		particles.add(new Particle(main, origin, img, this));
	}

	public boolean isDead() {
		if (origin.x > main.width || origin.y > (main.height - 100)) {
			return true;
		}
		return false;
	}
}
