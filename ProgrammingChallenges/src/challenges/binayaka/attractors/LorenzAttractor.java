package challenges.binayaka.attractors;

import java.util.ArrayList;

import peasy.PeasyCam;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * This will draw the lorenz attractor
 * 
 * @author Binayaka
 *
 */
public class LorenzAttractor extends PApplet {

	float x = 0.01f;
	float y = 0;
	float z = 0;
	float sigma = 10;
	float rho = 28;
	float beta = (float) (8.0 / 3.0);
	ArrayList<PVector> vectors = new ArrayList<>(3000);
	PeasyCam cam;
	boolean drawSine;

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
	public void mousePressed() {
		super.mousePressed();
		drawSine = !drawSine;
	}

	@Override
	public void draw() {
		background(0);
		calculateVariables();
		//calculateWave();
		drawAttractor();
	}

/*	private void calculateWave() {
		ArrayList<PVector> temp = new ArrayList<>(vectors.size());
		for (PVector v : vectors) {
			temp.add(v);
			if (drawSine) {
				addSineWaveBehaviour(temp);
			}
		}
		vectors.clear();
		vectors.addAll(temp);
	}*/

	/*private void addSineWaveBehaviour(ArrayList<PVector> temp) {
		ArrayList<PVector> newTemp = new ArrayList<>(temp.size());
		for (PVector t : temp) {
			
		}
		temp.clear();
		temp.addAll(newTemp);
	}*/

	private void drawAttractor() {
		// translate(width / 2, height / 2);
		translate(0, 0, 0);
		scale(5);
		noFill();
		beginShape();
		float hue = 0;
		for (PVector v : vectors) {
			// PVector color = v.copy().normalize().mult(255);
			// color = PVector.mult(PVector.random3D(), 255);
			stroke(hue, 255, 255);
			// stroke(abs(color.x) % 255, abs(color.y) % 255, abs(color.z) %
			// 255);
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
		float dx = (sigma * (y - x)) * dt;
		float dy = ((rho - z) * x - y) * dt;
		float dz = (x * y - beta * z) * dt;
		x = x + dx;
		y = y + dy;
		z = z + dz;
		vectors.add(new PVector(dx, dy, dz));
	}
}
