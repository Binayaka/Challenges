package challenges.binayaka.common;

import processing.core.PApplet;

/**
 * This class is to be used for testing purposes
 * 
 * @author Binayaka
 *
 */
public class Test extends PApplet {

	int cols, rows;
	int cellWidth = 40;

	@Override
	public void settings() {
		size(800, 600);
	}

	@Override
	public void setup() {
		cols = floor(width / cellWidth);
		rows = floor(height / cellWidth);
	}

	@Override
	public void draw() {
		background(51);
		pushMatrix();
		float xCoord = (float) (width * 0.8);
		float yCoord = (float) (height * 0.5);
		float angle = (float) (frameCount / -100.0);
		translate(xCoord, yCoord);
		rotate(angle);
		noStroke();
		fill(255, 0, 255, 100);
		DrawUtils.getInstance(this).polygon(0, 0, 70, 6);
		popMatrix();
	}

}
