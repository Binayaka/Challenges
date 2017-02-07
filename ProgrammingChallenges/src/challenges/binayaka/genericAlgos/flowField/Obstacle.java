package challenges.binayaka.genericAlgos.flowField;

import java.awt.Rectangle;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

/**
 * This will represent an obstacle
 * 
 * @author Binayaka
 *
 */
public class Obstacle {
	private final Rectangle r;
	private final PApplet main;

	public Obstacle(PApplet core, int x, int y, int w, int h) {
		main = core;
		r = new Rectangle(x, y, w, h);
	}

	public Obstacle(PApplet core, Rectangle r_) {
		main = core;
		r = r_;
	}

	public void display() {
		main.stroke(0);
		main.fill(175);
		main.rectMode(PConstants.CORNER);
		main.rect(r.x, r.y, r.width, r.height);
	}

	public boolean contains(PVector spot) {
		if (r.contains((int) spot.x, (int) spot.y)) {
			return true;
		} else {
			return false;
		}
	}

	public Rectangle getR() {
		return r;
	}
}
