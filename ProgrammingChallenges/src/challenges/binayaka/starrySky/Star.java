package challenges.binayaka.starrySky;

import processing.core.PApplet;
import processing.core.PVector;

public class Star implements SpaceObject {

	// max size will be 3 pixels
	private PVector pos;
	private PApplet main;
	private float size;
	private int sign = 1;

	public Star(PApplet _main, float x, float y) {
		main = _main;
		pos = new PVector(x, y);
		float val = main.random(MIN_PLANET_SIZE, MAX_PLANET_SIZE);
		size = PApplet.map(val, 0, 1, MIN_PLANET_SIZE, MAX_PLANET_SIZE);
	}

	@Override
	public void show() {
		main.pushMatrix();
		main.noStroke();
		float brightness = PApplet.map(size, MIN_STAR_SIZE, MAX_STAR_SIZE, STAR_MIN_BRIGHTNESS, STAR_MAX_BRIGHTNESS);
		main.fill(brightness);
		main.translate(pos.x, pos.y);
		main.ellipse(0, 0, size, size);
		main.popMatrix();
	}

	@Override
	public void update() {
		if (size < MIN_STAR_SIZE) {
			sign = 1;
		} else if (size > MAX_STAR_SIZE) {
			sign = -1;
		}
		size += (sign * GROWTH_FACTOR);
	}

}
