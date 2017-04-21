package challenges.binayaka.particleGarden;

import challenges.binayaka.common.Drawable;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * This will specify an attractor point
 * 
 * @author Binayaka
 *
 */
public class Attractor implements Drawable {
	final PVector pos;
	private final PApplet main;

	public Attractor(PApplet core, float x, float y) {
		pos = new PVector(x, y);
		main = core;
	}

	@Override
	public void show() {
		main.point(pos.x, pos.y);
	}

	@Override
	public void update() {

	}
}
