package challenges.binayaka.attractors;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class CircleAttractor extends PApplet {
	List<Attractor> attractors = new ArrayList<>();
	List<Particle> particles = new ArrayList<>();
	float radius;

	@Override
	public void settings() {
		size(600, 600);
	}

	@Override
	public void setup() {
		radius = width / 2;
		float x, y;
		for (float angle = 0; angle < 2 * PI; angle += 0.02) {
			x = radius * PApplet.cos(angle);
			y = radius * PApplet.sin(angle);
			Attractor attractor = new Attractor(this, x, y);
			attractors.add(attractor);
		}

		for (int i = 0; i < 50; i++) {
			Particle particle = new Particle(this, random(width), random(height));
			particles.add(particle);
		}
	}

	@Override
	public void draw() {
		background(51);
		for (Attractor a : attractors) {
			a.applyForce(particles);
		}

		for (Particle p : particles) {
			p.update();
			p.show();
		}
	}

}
