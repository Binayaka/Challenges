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
	private static PApplet parent;

	/**
	 * This will return the instance
	 * 
	 * @return
	 */
	public static DrawUtils getInstance(PApplet core) {
		if (instance == null) {
			instance = new DrawUtils();
			parent = core;
		}
		return instance;
	}

	public void polygon(float x, float y, float radius, int npoints) {
		float angle = PConstants.TWO_PI / npoints;
		parent.beginShape();
		for (float a = 0; a < PConstants.TWO_PI; a += angle) {
			float sx = x + PApplet.cos(a) * radius;
			float sy = y + PApplet.sin(a) * radius;
			parent.vertex(sx, sy);
		}
		parent.endShape(PConstants.CLOSE);
	}

	public void drawCylinder(float topRadius, float bottomRadius, float tall, int sides) {
		float angle = 0;
		float angleIncrement = PConstants.TWO_PI / sides;
		parent.beginShape(PConstants.QUAD_STRIP);
		for (int i = 0; i < sides + 1; ++i) {
			parent.vertex(topRadius * PApplet.cos(angle), 0, topRadius * PApplet.sin(angle));
			parent.vertex(bottomRadius * PApplet.cos(angle), tall, bottomRadius * PApplet.sin(angle));
			angle += angleIncrement;
		}
		parent.endShape();

		// If it is not a cone, draw the circular top cap
		if (topRadius != 0) {
			angle = 0;
			parent.beginShape(PConstants.TRIANGLE_FAN);

			// Center point
			parent.vertex(0, 0, 0);
			for (int i = 0; i < sides + 1; i++) {
				parent.vertex(topRadius * PApplet.cos(angle), 0, topRadius * PApplet.sin(angle));
				angle += angleIncrement;
			}
			parent.endShape();
		}

		// If it is not a cone, draw the circular bottom cap
		if (bottomRadius != 0) {
			angle = 0;
			parent.beginShape(PConstants.TRIANGLE_FAN);

			// Center point
			parent.vertex(0, tall, 0);
			for (int i = 0; i < sides + 1; i++) {
				parent.vertex(bottomRadius * PApplet.cos(angle), tall, bottomRadius * PApplet.sin(angle));
				angle += angleIncrement;
			}
			parent.endShape();
		}
	}

	public void drawPetal(float startX, float startY, float angle, float petalLength) {
		// set offset for control points
		float bezierDiff = petalLength / 2;

		// set angle for start's control points
		float startAngleTop = angle - 45;
		float startAngleBottom = angle + 45;

		// set angle for end's control points
		float endAngleTop = angle - 135;
		float endAngleBottom = angle + 135;

		// calculate start's top control point
		float startAngleTopX = startX + PApplet.cos(PApplet.radians(startAngleTop)) * bezierDiff;
		float startAngleTopY = startY + PApplet.sin(PApplet.radians(startAngleTop)) * bezierDiff;

		// calculate start's bottom control point
		float startAngleBottomX = startX + PApplet.cos(PApplet.radians(startAngleBottom)) * bezierDiff;
		float startAngleBottomY = startY + PApplet.sin(PApplet.radians(startAngleBottom)) * bezierDiff;

		// calculate end point
		float endX = startX + PApplet.cos(PApplet.radians(angle)) * petalLength;
		float endY = startY + PApplet.sin(PApplet.radians(angle)) * petalLength;

		// calculate end's top control point
		float endAngleTopX = endX + PApplet.cos(PApplet.radians(endAngleTop)) * bezierDiff;
		float endAngleTopY = endY + PApplet.sin(PApplet.radians(endAngleTop)) * bezierDiff;

		// calculate end's bottom control point
		float endAngleBottomX = endX + PApplet.cos(PApplet.radians(endAngleBottom)) * bezierDiff;
		float endAngleBottomY = endY + PApplet.sin(PApplet.radians(endAngleBottom)) * bezierDiff;

		/*
		 * if (verbose) { // if true draw point and control points
		 * parent.noStroke(); parent.fill(255, 0, 0); parent.ellipse(startX,
		 * startY, controlPointDiameter, controlPointDiameter);
		 * parent.ellipse(startAngleTopX, startAngleTopY, controlPointDiameter,
		 * controlPointDiameter); parent.ellipse(startAngleBottomX,
		 * startAngleBottomY, controlPointDiameter, controlPointDiameter);
		 * parent.ellipse(endAngleTopX, endAngleTopY, controlPointDiameter,
		 * controlPointDiameter); parent.ellipse(endAngleBottomX,
		 * endAngleBottomY, controlPointDiameter, controlPointDiameter);
		 * parent.ellipse(endX, endY, controlPointDiameter,
		 * controlPointDiameter); parent.noFill(); parent.stroke(0); }
		 */
		// draw petal shape with points and control points
		parent.noFill();
		parent.stroke(255);
		parent.beginShape();
		parent.vertex(startX, startY);
		parent.bezierVertex(startAngleTopX, startAngleTopY, endAngleTopX, endAngleTopY, endX, endY);
		parent.bezierVertex(endAngleBottomX, endAngleBottomY, startAngleBottomX, startAngleBottomY, startX, startY);
		parent.endShape();
	}

}
