package challenges.binayaka.butterflyWings;

import processing.core.PApplet;

/**
 * This will try to draw a butterly's wings
 * 
 * @author Binayaka
 *
 */
public class ButterflyWings extends PApplet {

	float yoff = 0f;
	float rotVal = 0;

	@Override
	public void settings() {
		size(800, 600);
	}

	@Override
	public void setup() {

	}

	@Override
	public void draw() {
		background(51);
		translate(width / 2, height / 2);
		rotate(PI / 2 + rotVal % 360);

		stroke(255);
		fill(255, 50);
		strokeWeight(1);

		float da = PI / 200;
		float dx = 0.05f;
		float xoff = 0;
		beginShape();
		for (float a = -PI / 2; a <= 3 * PI / 2; a += da) {
			float n = noise(xoff, yoff);
			float r = sin(2 * a) * map(n, 0, 1, 50, 300);
			float x = r * cos(a);
			float y = sin(yoff) * r * sin(a);
			if (a < PI / 2) {
				xoff += dx;
			} else {
				xoff -= dx;
			}
			// point(x, y);
			vertex(x, y);
		}
		endShape();

		yoff += 0.01;
		rotVal += 0.01;
	}
}
