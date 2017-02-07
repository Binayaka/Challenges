package challenges.binayaka.tusiCouple;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * This will run the Tusi Couple
 * 
 * @author Binayaka
 *
 */
public class TusiCouple extends PApplet {
	Circle main;
	Circle child;
	float angle = 0;

	@Override
	public void settings() {
		size(800, 800);
	}

	@Override
	public void setup() {
		colorMode(RGB);
		main = new Circle(this, 0, 0);
		main.setRadius(200f);
		child = new Circle(this, 0, -100);
		child.setRadius(100f);
		main.clearPoints();
	}

	@Override
	public void draw() {
		background(0);
		noFill();
		stroke(255, 255, 255);
		line(width / 2, 0, width / 2, height);
		line(0, height / 2, width, width / 2);
		translate(width / 2, height / 2);
		angle++;
		angle = angle % 360;
		main.show();
		child.show();
		PVector pos = getNewPosition();
		child.setPosition(pos);
	}

	private PVector getNewPosition() {
		float newX, newY;
		newX = child.getRadius() * PApplet.sin(PApplet.radians(angle));
		newY = child.getRadius() * PApplet.cos(PApplet.radians(angle));
		return new PVector(newX, newY);
	}

}
