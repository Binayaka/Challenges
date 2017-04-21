package challenges.binayaka.fractal;

import challenges.binayaka.common.Utils;
import processing.core.PApplet;

/**
 * This will generate the mandelbrot fractal set
 * 
 * @author Binayaka
 *
 */
public class Mandelbrot extends PApplet {

	Float INF = Float.valueOf((float) 16.00);

	@Override
	public void settings() {
		size(800, 600);
	}

	@Override
	public void setup() {
		colorMode(RGB, 1);
		// Make sure we can write to the pixels[] array.
		// Only need to do this once since we don't do any other drawing.
		loadPixels();
	}

	@Override
	public void draw() {
		background(255);
		// Establish a range of values on the complex plane
		// A different range will allow us to "zoom" in or out on the fractal

		// It all starts with the width, try higher or lower values
		float w = (float) 4;
		float h = (w * height) / width;

		// Start at negative half the width and height
		float xmin = -w / 2;
		float ymin = -h / 2;

		// Maximum number of iterations for each point on the complex plane
		int maxiterations = 100;

		// x goes from xmin to xmax
		float xmax = xmin + w;
		// y goes from ymin to ymax
		float ymax = ymin + h;

		// Calculate amount we increment x,y for each pixel
		float dx = (xmax - xmin) / (width);
		float dy = (ymax - ymin) / (height);

		// Start y
		float y = ymin;
		for (int j = 0; j < height; j++) {
			// Start x
			float x = xmin;
			float minVal = Float.MIN_VALUE;
			float maxVal = Float.MAX_VALUE;
			for (int i = 0; i < width; i++) {

				// Now we test, as we iterate z = z^2 + cm does z tend towards
				// infinity?
				float a = x;
				float b = y;
				int n = 0;
				Float addedVal = 0f;
				while (n < maxiterations) {
					float aa = a * a;
					float bb = b * b;
					float twoab = (float) (2.0 * a * b);
					a = aa - bb + x;
					b = twoab + y;
					// Infinity in our finite world is simple, let's just
					// consider it 16
					addedVal = Float.valueOf(a * a + b * b);
					if (addedVal.compareTo(INF) > 0) {
						break;
					}
					n++;
				}
				
				if(addedVal > minVal){
					minVal = addedVal;
				}
				
				if(addedVal < maxVal){
					maxVal = addedVal;
				}

				// We color each pixel based on how long it takes to get to
				// infinity
				// If we never got there, let's pick the color black
				if (n == maxiterations) {
					pixels[i + j * width] = color(0,0,0);
				} else {
					// Gosh, we could make fancy colors here if we wanted
					// pixels[i + j * width] = color(sqrt((n) / maxiterations));
					int val = (int) Utils.map(addedVal,340,16,0,10);
					//pixels[i + j * width] = color(255,255,255);
					pixels[i + j * width] = color(val);
				}
				x += dx;
			}
			System.out.println("MinVal :: " + minVal + ", MaxVal :: " + maxVal);
			y += dy;
		}
		updatePixels();
		noLoop();
		// println(frameRate);
	}

}
