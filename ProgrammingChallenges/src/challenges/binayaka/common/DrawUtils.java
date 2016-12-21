package challenges.binayaka.common;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * This is a utility class, which will be used to draw common shapes
 * 
 * @author Binayaka
 *
 */
public class DrawUtils {

	private static DrawUtils instance = null;

	/**
	 * This will return the instance
	 * 
	 * @return
	 */
	public static DrawUtils getInstance() {
		if (instance == null) {
			instance = new DrawUtils();
		}
		return instance;
	}

	public void polygon(PApplet parent, float x, float y, float radius, int npoints) {
		float angle = PConstants.TWO_PI / npoints;
		parent.beginShape();
		for (float a = 0; a < PConstants.TWO_PI; a += angle) {
			float sx = x + PApplet.cos(a) * radius;
			float sy = y + PApplet.sin(a) * radius;
			parent.vertex(sx, sy);
		}
		parent.endShape(PConstants.CLOSE);
	}

}
