package challenges.binayaka.perlin;

import processing.core.PApplet;
import processing.event.MouseEvent;

/**
 * This will be used to simulate a 3D terrain
 * 
 * @author Binayaka
 *
 */
public class Perlin3D extends PApplet {
	int cols, rows;
	int scl = 20;
	int w = 2000;
	int h = 1600;

	// for my configuration, left button is 37 and right button is 39
	int L_MOUSE_BUTTON = 37;
	int R_MOUSE_BUTTON = 39;

	float flying = 0;

	float[][] terrain;

	@Override
	public void settings() {
		size(800, 600, P3D);
	}

	@Override
	public void setup() {
		cols = w / scl;
		rows = h / scl;
		terrain = new float[cols][rows];
	}

	@Override
	public void mousePressed(MouseEvent event) {
		super.mousePressed(event);
		if (event.getButton() == L_MOUSE_BUTTON) {
			flying -= 0.5;
		} else if (event.getButton() == R_MOUSE_BUTTON) {
			flying += 0.1;
		}
		System.out.println("flying speed is now " + flying);
	}

	@Override
	public void draw() {

		flying -= 0.1;

		float yoff = flying;
		for (int y = 0; y < rows; y++) {
			float xoff = 0;
			for (int x = 0; x < cols; x++) {
				terrain[x][y] = map(noise(xoff, yoff), 0, 1, -100, 100);
				xoff += 0.2;
			}
			yoff += 0.2;
		}

		background(0);
		translate(width / 2, height / 2);
		rotateX(PI / 3);
		translate(-w / 2, -h / 2);
		for (int y = 0; y < rows - 1; y++) {
			float fadeAlpha = map(y, 0, rows, 255, 0);
			stroke(255, fadeAlpha);
			fill(10, fadeAlpha);
			beginShape(TRIANGLE_STRIP);
			for (int x = 0; x < cols; x++) {
				vertex(x * scl, y * scl, terrain[x][y]);
				vertex(x * scl, (y + 1) * scl, terrain[x][y + 1]);
			}
			endShape();
		}
	}

}
