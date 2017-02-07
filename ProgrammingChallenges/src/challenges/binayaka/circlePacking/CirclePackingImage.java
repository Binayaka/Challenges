package challenges.binayaka.circlePacking;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class CirclePackingImage extends PApplet {

	private static final int MAX_ATTEMPTS_PER_FRAME = 100;
	private static final int CIRCLES_PER_FRAME = 10;
	private static final int PIXEL_WIDTH = 2;
	ArrayList<Circle> circles = new ArrayList<>();
	// ArrayList<PVector> spots = new ArrayList<>();
	PImage img;

	@Override
	public void settings() {
		size(800, 450);
	}

	@Override
	public void setup() {
		background(0);
		img = loadImage("cube.png");
		img.loadPixels();
		/*
		 * for (int x = 0; x < img.width; x++) { for (int y = 0; y < img.height;
		 * y++) { int index = x + y * img.width; int color = img.pixels[index];
		 * float bright = brightness(color); if (bright > 150) { spots.add(new
		 * PVector(x, y)); } } }
		 */
		// System.out.println(spots.size());
	}

	@Override
	public void draw() {
		background(0);
		addCircles();
		for (Circle circle : circles) {
			if (circle.growing) {
				if (circle.edges()) {
					circle.growing = false;
				} else {
					for (Circle other : circles) {
						if (circle != other) {
							float d = dist(circle.x, circle.y, other.x, other.y);
							if ((d - PIXEL_WIDTH) < (circle.r + other.r)) {
								circle.growing = false;
								break;
							}
						}
					}
				}
			}
			circle.show();
			circle.grow();
		}
	}

	private void addCircles() {
		int count = 0;
		int attempts = 0;
		while (count < CIRCLES_PER_FRAME) {
			Circle newCircle = addNewCircle();
			if (newCircle != null) {
				circles.add(newCircle);
				count++;
			}
			attempts++;
			if (attempts > MAX_ATTEMPTS_PER_FRAME) {
				/*
				 * System.out.println("Finished!"); noLoop();
				 */
				circles.clear();
				break;
			}
		}

	}

	/**
	 * This will add a new circle on the screen
	 */
	private Circle addNewCircle() {
		/*
		 * int index = (int) random(0, spots.size()); PVector spot =
		 * spots.get(index); float x = spot.x; float y = spot.y;
		 */

		float x = random(width);
		float y = random(height);

		boolean valid = true;
		for (Circle c : circles) {
			float d = dist(x, y, c.x, c.y);
			if (d < c.r + PIXEL_WIDTH) {
				valid = false;
			}
		}
		if (valid) {
			Circle circle = new Circle(this, x, y);
			circle.setColor(img.pixels[(int) (x + y * img.width)]);
			return circle;
		}
		return null;
	}

}
