package challenges.binayaka.fractal;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

public class FractalSpirographBigger extends PApplet implements FractalConstants {

	ArrayList<PVector> path;
	PGraphics pg;
	float angle = 0;
	Orbit sun;
	Orbit end;

	@Override
	public void settings() {
		size(600, 150);
	}

	@Override
	public void setup() {
		path = new ArrayList<PVector>();
		pg = createGraphics(size, size, JAVA2D);
		path = new ArrayList<PVector>();
		sun = new Orbit(this, pg.width / 2, pg.height / 2, pg.width / 4, 0, resolution);
		Orbit next = sun;
		for (int i = 0; i < circles; i++) {
			next = next.addChild();
		}
		end = next;
	}

	@Override
	public void draw() {
		background(51);
		if (sun.child.angle >= HALF_PI * 3) {
			pg.beginDraw();
			pg.background(255);
			pg.stroke(0);
			pg.strokeWeight(weight);
			pg.noFill();
			pg.beginShape();
			for (PVector pos : path) {
				pg.vertex(pos.x, pos.y);
			}
			pg.endShape();
			pg.endDraw();
			pg.save("render/render.png");
			noLoop();
			System.out.println("Completed!");
		}

		for (int i = 0; i < resolution; i++) {
			Orbit next = sun;
			while (next != null) {
				next.update();
				// next.show();
				next = next.child;
			}
			path.add(new PVector(end.x, end.y));
		}

		Orbit next = sun;
		while (next != null) {
			next = next.child;
		}

		String percentage = str(((sun.child.angle + HALF_PI) / TWO_PI) * 100) + "%";
		textSize(90);
		text(percentage, 30, 110);
	}

}
