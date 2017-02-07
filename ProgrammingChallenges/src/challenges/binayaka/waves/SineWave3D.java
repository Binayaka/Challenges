package challenges.binayaka.waves;

import processing.core.PApplet;

/**
 * This will simulate a sine wave in 3D
 * 
 * @author Binayaka
 *
 */
public class SineWave3D extends PApplet {
	Wave w1, w2, w3, w4;

	@Override
	public void settings() {
		size(800, 600, P3D);
	}

	@Override
	public void setup() {
		smooth();
		w1 = new Wave(this, 20, 40, 0, 0, 0);
		w2 = new Wave(this, 10, 20, PI / 4, PI / 3, PI / 6);
		w3 = new Wave(this, 50, 10, 0, (float) (PI / 3.5), PI);
		w4 = new Wave(this, 80, 60, PI / 7, 0, (float) (PI / 5.0));
	}

	@Override
	public void draw() {
		background(255);
		float rotY = map(mouseX, 0, height, 0, 180);
		float rotZ = map(mouseY, 0, width, -90, 90);
		translate(width / 2, height / 2, 100);
		rotateY(radians(rotY));
		rotateZ(radians(rotZ));
		w1.draw();
		w2.draw();
		w3.draw();
		w4.draw();
		noFill();
		//box(100);
	}

}
