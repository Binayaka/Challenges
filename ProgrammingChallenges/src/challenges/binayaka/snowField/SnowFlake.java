package challenges.binayaka.snowField;

import challenges.binayaka.common.Drawable;
import processing.core.PApplet;
import processing.core.PVector;

public class SnowFlake implements Drawable {
	Horizon main;
	PVector pos;
	PVector velocity;
	PVector acc;
	int radius = 100;
	int minSpeed = 1;
	int maxSpeed = 3;
	int xSign = 1;
	int ySign = 1;
	int minSize = 2;
	int maxSize = 9;
	float maxDist;

	public SnowFlake(Horizon horizon) {
		main = horizon;
		PVector centre = main.getCentre();
		calculatePosition(centre);
		calculateSpeed();
		calculateAcceleration();
		maxDist = main.getCentre().x; // because this is the centre, this is
										// the max difference possible
	}

	private void calculateAcceleration() {
		calculateRandomness();
		float accel = main.random(0, 1);
		float acce = PApplet.map(accel, 0f, 1f, 0.2f, 0.6f);
		float xAcc = xSign * acce;
		float yAcc = ySign * acce;
		acc = new PVector(xAcc, yAcc);
	}

	private void calculateSpeed() {
		calculateRandomness();
		float speed = main.random(minSpeed, maxSpeed);
		float xSp = (xSign * speed);
		float ySp = (ySign * speed);
		velocity = new PVector(xSp, ySp);
	}

	private void calculatePosition(PVector centre) {
		calculateRandomness();
		float rad = main.random(10, radius);
		float xPos = centre.x + (xSign * rad);
		float yPos = centre.y + (ySign * rad);
		pos = new PVector(xPos, yPos);
	}

	private void calculateRandomness() {
		float xRandom = main.random(0, 1);
		float yRandom = main.random(0, 1);
		if (xRandom < 0.5) {
			xSign = -1;
		} else {
			xSign = 1;
		}
		if (yRandom < 0.5) {
			ySign = -1;
		} else {
			ySign = 1;
		}
	}

	@Override
	public void show() {
		main.noStroke();
		float dist = PVector.dist(pos, main.getCentre());
		float size = PApplet.map(dist, 0, maxDist, minSize, maxSize);
		main.ellipse(pos.x, pos.y, size, size);
	}

	@Override
	public void update() {
		pos.add(velocity);
		velocity.add(acc);
		calculateAcceleration();
	}

	public boolean isOutOfRange() {
		if (pos.x <= 0 || pos.x >= main.width || pos.y <= 0 || pos.y >= main.height) {
			return true;
		}
		return false;
	}

}
