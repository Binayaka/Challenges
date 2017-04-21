package challenges.binayaka.illusions;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class Stage extends PApplet implements StageConstants {

	@Override
	public void settings() {
		size(600, 600);
	}

	@Override
	public void setup() {

	}

	@Override
	public void draw() {
		background(0);
		translate(width / 2, height / 2);
		noFill();
		stroke(255);
		ellipse(0, 0, radius, radius);
		calculatePoints();
		noLoop();
	}

	public void calculatePoints() {
		List<PVector> list = new ArrayList<>();
		int[] red__ = new int[] { 000, 255, 000, 000, 255, 255, 000 };
		int[] green = new int[] { 000, 000, 255, 000, 255, 000, 255 };
		int[] blue_ = new int[] { 000, 000, 000, 255, 000, 255, 255 };
		// start from 0,0
		for (int i = 0; i <= 360; i += 60) {
			float angle = radians(i);
			float x = radius / 2 * cos(angle);
			float y = radius / 2 * sin(angle);
			System.out.println("Point is {" + x + " , " + y + "}");
			// stroke(255, 0, 0);
			// point(x, y);
			list.add(new PVector(x, y));
		}

		for (int i = 0; i < list.size(); i++) {
			PVector vector = list.get(i);
			pushMatrix();
			translate(vector.x, vector.y);
			fill(255);
			text(i, vector.x, vector.y);
			fill(red__[i], green[i], blue_[i]);
			ellipse(0, 0, smallerRadius, smallerRadius);
			popMatrix();
		}
		System.out.println("Length is :: " + list.size());
	}

}
