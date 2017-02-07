package challenges.binayaka.algorithmicBotany;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * This is based on the algorithmic botany example
 * @author Binayaka
 *
 */
public class Phyllotaxis extends PApplet {

	float n = 0;
	float c = 3;

	ArrayList<PVector> points = new ArrayList<PVector>();

	float start = 0;

	@Override
	public void settings() {
		size(400, 400);

	}

	public void setup() {
		colorMode(HSB, 360, 255, 255);
	}

	public void draw() {
		background(0);
		translate(width / 2, height / 2);
		//rotate((float) (n * 0.3));
		rotate(0.5f);
		for (int i = 0; i < n; i++) {
			float a = i * radians((float) 137.5);
			float r = c * sqrt(i);
			float x = r * cos(a);
			float y = r * sin(a);
			float hu = i + start;// sin(start + i * 0.5);
			hu = (float) (i / 3.0 % 360);
			fill(hu, 255, 255);
			noStroke();
			ellipse(x, y, 4, 4);
		}
		n += 5;
		start += 5;
	}

}
