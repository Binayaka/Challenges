package challenges.binayaka.attractors;

import java.util.ArrayList;

import peasy.PeasyCam;
import processing.core.PApplet;
import processing.core.PVector;

public class RösslerAttractor extends PApplet {

	float x = 0.01f;
	float y = 0;
	float z = 0;
	float a = 10;
	float b = 28;
	float c = (float) (8.0 / 3.0);
	ArrayList<PVector> vectors = new ArrayList<>(3000);
	PeasyCam cam;

	@Override
	public void settings() {
		size(800, 600, P3D);
	}

	@Override
	public void setup() {
		colorMode(HSB);
		// colorMode(RGB);
		cam = new PeasyCam(this, 500);
	}

	@Override
	public void draw() {
		background(0);
		calculateVariables();
		drawAttractor();
	}

	private void drawAttractor() {
		translate(0, 0, 0);
		scale(5);
		noFill();
		beginShape();
		float hue = 0;
		for (PVector v : vectors) {
			stroke(hue, 255, 255);
			strokeWeight(0.5f);
			vertex(v.x, v.y, v.z);
			hue += 0.1;
			if (hue > 255) {
				hue = 0;
			}
		}
		endShape();
	}

	private void calculateVariables() {
		float dt = 0.01f;
		float dx = (-y - z) * dt;
		float dy = (x + a * y) * dt;
		float dz = (b + z * (x - c)) * dt;
		x = x + dx;
		y = y + dy;
		z = z + dz;
		vectors.add(new PVector(dx, dy, dz));
	}
}
