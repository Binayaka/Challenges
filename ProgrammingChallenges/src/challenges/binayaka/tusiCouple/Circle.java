package challenges.binayaka.tusiCouple;

import challenges.binayaka.common.Drawable;
import processing.core.PApplet;
import processing.core.PVector;

public class Circle implements Drawable {
	private final PApplet main;
	private float centerX, centerY;
	private float radius = 200f;
	private final PVector pos;
	private PVector[] points;

	public Circle(PApplet main_, float i, float j) {
		main = main_;
		centerX = i;
		centerY = j;
		pos = new PVector(i, j);
		points = new PVector[8];
		calculatePoints(radius);
	}

	public PVector getPosition() {
		return pos;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
		calculatePoints(radius);
	}

	private void calculatePoints(float radius) {
		for (int counter = 0; counter < 8; counter++) {
			float angle = 45 * counter;
			float converted = PApplet.radians(angle);
			float x = /* centerX + */ radius * PApplet.sin(converted);
			float y = /* centerY + */ radius * PApplet.cos(converted);
			points[counter] = new PVector(x, y);
		}
	}

	@Override
	public void show() {
		main.pushMatrix();
		main.translate(centerX, centerY);
		main.noFill();
		main.stroke(255);
		main.ellipse(0, 0, radius * 2, radius * 2); // radius is 200
		main.popMatrix();
		if (points != null) {
			for (int counter = 0; counter < points.length; counter++) {
				main.pushMatrix();
				PVector point = points[counter];
				main.translate(centerX + point.x, centerY + point.y);
				main.fill(255, 255, 60);
				main.ellipse(0, 0, 10, 10);
				main.popMatrix();
			}
		}

		drawExtraCircles();
	}

	private void drawExtraCircles() {
		if (points != null) {
			for (int counter = 0; counter < points.length; counter++) {
				main.pushMatrix();
				main.translate(centerX, centerY);
				main.noFill();
				main.stroke(200, 120, 0);
				main.line(0, 0, points[counter].x, points[counter].y);
				main.popMatrix();
			}
		}
	}

	public void clearPoints() {
		points = null;
	}

	public void setPosition(PVector pos) {
		centerX = pos.x;
		centerY = pos.y;
		calculatePoints(radius);
	}

	@Override
	public void update() {

	}

}
