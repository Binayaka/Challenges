package challenges.binayaka.circlePacking;

import challenges.binayaka.common.Drawable;
import processing.core.PApplet;

/**
 * This will be the actual object drawn on the screen
 * 
 * @author Binayaka
 *
 */
public class Circle implements Drawable {
	private final PApplet main;
	float x, y, r;
	boolean growing = true;
	private int color = -1;

	public Circle(PApplet main_, float x_, float y_) {
		main = main_;
		x = x_;
		y = y_;
		r = 1;
	}

	public void setColor(int colorVal) {
		color = colorVal;
	}

	@Override
	public void show() {
		if (color == -1) {
			main.stroke(255);
			main.strokeWeight(1);
			main.noFill();
		} else {
			main.fill(color);
		}
		main.ellipse(x, y, r * 2, r * 2);
	}

	/**
	 * This will grow the circle
	 */
	public void grow() {
		if (growing) {
			r = r + 1;
		}
	}

	/**
	 * This will check if the edges has been reached
	 * 
	 * @return
	 */
	public boolean edges() {
		boolean rightReached = Float.valueOf(x + r).compareTo(Float.valueOf(main.width)) > 0;
		boolean leftReached = Float.valueOf(x - r).compareTo(Float.valueOf(0)) < 0;
		boolean topReached = Float.valueOf(y + r).compareTo(Float.valueOf(main.height)) > 0;
		boolean bottomReached = Float.valueOf(y - r).compareTo(Float.valueOf(0)) < 0;
		if (rightReached || leftReached || topReached || bottomReached) {
			return true;
		}
		return false;
	}

	@Override
	public void update() {

	}

}
