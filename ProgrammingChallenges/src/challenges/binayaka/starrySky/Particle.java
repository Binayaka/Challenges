package challenges.binayaka.starrySky;

import challenges.binayaka.common.Drawable;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

public class Particle implements Drawable {
	PVector loc;
	PVector vel;
	PVector acc;
	float lifespan;
	PImage img;
	PApplet main;
	Comet comet;

	Particle(PApplet _main, PVector l, PImage img_, Comet _comet) {
		comet = _comet;
		main = _main;
		acc = new PVector(0, 0);
		float vx = (float) (main.randomGaussian() * 0.3);
		float vy = (float) (main.randomGaussian() * 0.3 - 1.0);
		vel = new PVector(vx, vy);
		loc = l.copy();
		lifespan = 100.0f;
		img = img_;
	}

	@Override
	public void show() {
		update();
		render();
	}

	// Method to apply a force vector to the Particle object
	// Note we are ignoring "mass" here
	void applyForce(PVector f) {
		acc.add(f);
	}

	// Method to update position
	@Override
	public void update() {
		vel.add(acc);
		loc.add(vel);
		lifespan -= 2.5;
		acc.mult(0); // clear Acceleration
	}

	// Method to display
	void render() {
		main.imageMode(PConstants.CENTER);
		main.tint(255, lifespan);
		float dist = PApplet.dist(loc.x, loc.y, comet.origin.x, comet.origin.y);
		int particleSize = PApplet.ceil(PApplet.map(dist, 0, main.width, 32, 2));
		if (particleSize <= 0) {
			particleSize = 1;
		}
		PImage copy = img.copy();
		copy.resize(particleSize, particleSize);
		main.image(copy, loc.x, loc.y);
		// Drawing a circle instead
		// fill(255,lifespan);
		// noStroke();
		// ellipse(loc.x,loc.y,img.width,img.height);
	}

	// Is the particle still useful?
	boolean isDead() {
		if (lifespan <= 0.0) {
			return true;
		} else {
			return false;
		}
	}

}
