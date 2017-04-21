package challenges.binayaka.fractal;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class FractalTusi extends PApplet implements FractalConstants {

	ArrayList<PVector> path;
	float angle = 0;
	Tusi sun;
	Tusi end;

	@Override
	public void settings() {
		size(600, 600);
	}

	@Override
	public void setup() {
		path = new ArrayList<PVector>();
		sun = new Tusi(this, 0, 0, width / 4, 0, tusiRep);
		Tusi next = sun;
		for (int i = 0; i < 1; i++) {
			next = next.addChild();
		}
		end = next;
	}

	@Override
	public void draw() {
		background(51);
		translate(width / 2, height / 2);
		for (int i = 0; i < tusiRep; i++) {
			Tusi next = sun;
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

		Tusi next = sun;
		while (next != null) {
			next.show();
			next = next.child;
		}

		beginShape();
		stroke(255);
		noFill();
		for (PVector pos : path) {
			vertex(pos.x, pos.y);
			// point(pos.x, pos.y);
		}
		endShape();
		if (frameCount % 10 == 0) {
			System.out.println(path.size());
		}
	}
}
