package challenges.binayaka.fractal;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class FractalSpirograph extends PApplet implements FractalConstants {

	ArrayList<PVector> path;
	float angle = 0;
	Orbit sun;
	Orbit end;

	@Override
	public void settings() {
		size(600, 600);
	}

	@Override
	public void setup() {
		path = new ArrayList<PVector>();
		sun = new Orbit(this, width / 2, height / 2, width / 4, 0, resolutionSmall);
		Orbit next = sun;
		for (int i = 0; i < 10; i++) {
			next = next.addChild();
		}
		end = next;
	}

	@Override
	public void draw() {
		background(51);

		for (int i = 0; i < resolutionSmall; i++) {
			Orbit next = sun;
			while (next != null) {
				next.update();
				// next.show();
				next = next.child;
			}
			PVector e = new PVector(end.x, end.y);
			if (!path.contains(null)) {
				path.add(e);
			}

		}

		Orbit next = sun;
		while (next != null) {
			next.show();
			next = next.child;
		}

		beginShape();
		stroke(255);
		noFill();
		for (PVector pos : path) {
			vertex(pos.x, pos.y);
		}
		endShape();
	}

}
