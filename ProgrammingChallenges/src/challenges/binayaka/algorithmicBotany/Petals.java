package challenges.binayaka.algorithmicBotany;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

/**
 * This will try to draw petals
 * 
 * @author Binayaka
 * 
 */
public class Petals extends PApplet {

	private Flower flower;
	public static final float PETAL_WIDTH = 10;

	@Override
	public void settings() {
		size(600, 600);
	}

	@Override
	public void setup() {
		PVector root = new PVector(0, 0);
		flower = new Flower(this,root, 50f, 20f);
		flower.addPetalLayer();
		//flower.addPetalLayer();
		//flower.addPetalLayer();
	}

	@Override
	public void keyPressed(KeyEvent event) {
		super.keyPressed(event);
		if (event.getKey() == 'A' || event.getKey() == 'a') {
			flower.addPetalLayer();
		}
	}

	@Override
	public void draw() {
		background(0);
		translate(width / 2, height / 2);
		noFill();
		stroke(255);
		flower.show();
	}

}
