package challenges.binayaka.algorithmicBotany;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

/**
 * This will attempt to draw a flower
 * 
 * @author Binayaka
 *
 */
public class MainFlower extends PApplet {
	private Flower flower;

	@Override
	public void settings() {
		size(800, 800);
	}

	@Override
	public void setup() {
		// colorMode(HSB, 360, 255, 255);
		colorMode(HSB);
		flower = new Flower(this, new PVector(0, 0));
	}

	@Override
	public void keyPressed(KeyEvent event) {
		super.keyPressed(event);
		if (event.getKey() == 'A' || event.getKey() == 'a') {
			flower.addLayer();
		} else if (event.getKey() == 'Z' || event.getKey() == 'z') {
			flower.removeLastLayer();
		}
	}

	@Override
	public void draw() {
		background(0);
		noFill();
		stroke(255, 255, 255);
		line(width / 2, 0, width / 2, height);
		line(0, height / 2, width, width / 2);
		translate(width / 2, height / 2);
		ellipse(0, 0, 40, 40);
		flower.show();
	}
}
