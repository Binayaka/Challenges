package challenges.binayaka.starrySky;

import processing.core.PApplet;
import processing.core.PVector;

public class Planet implements SpaceObject {

	// max size will be 3 pixels
	private PVector pos;
	private PApplet main;
	private float size;

	public Planet(PApplet _main, float x, float y) {
		main = _main;
		pos = new PVector(x, y);
		float val = main.random(MIN_PLANET_SIZE, MAX_PLANET_SIZE);
		size = PApplet.map(val, 0, 1, MIN_PLANET_SIZE, MAX_PLANET_SIZE);
	}

	@Override
	public void show() {
		main.pushMatrix();
		main.noStroke();
		main.fill(PLANET_BRIGHTNESS);
		main.translate(pos.x, pos.y);
		main.ellipse(0, 0, size, size);
		main.popMatrix();
	}

	@Override
	public void update() {

	}

}
