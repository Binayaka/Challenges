package challenges.binayaka.algorithmicBotany;

import challenges.binayaka.common.Drawable;
import processing.core.PApplet;

public class Petal implements Drawable {
	private final PApplet main;
	private final float startX;
	private final float startY;
	private final float angle;
	private final float petalLength;
	private final int generation;
	private final float growthFactor = 3f;
	private int color;

	public Petal(PApplet main_, float pX_, float pY_, float angle_, float petalLength_, int generation_) {
		main = main_;
		startX = pX_;
		startY = pY_;
		angle = angle_;
		petalLength = petalLength_;
		generation = generation_;
		color = -1;
		System.out.println("startX " + startX + "startY " + startY);
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	@Override
	public void show() {
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
		float currentPetalLength = petalLength + growthFactor * generation;
		float endX = startX + PApplet.cos(PApplet.radians(angle)) * currentPetalLength;
		float endY = startY + PApplet.sin(PApplet.radians(angle)) * currentPetalLength;

		// calculate end's top control point
		float endAngleTopX = endX + PApplet.cos(PApplet.radians(endAngleTop)) * bezierDiff;
		float endAngleTopY = endY + PApplet.sin(PApplet.radians(endAngleTop)) * bezierDiff;

		// calculate end's bottom control point
		float endAngleBottomX = endX + PApplet.cos(PApplet.radians(endAngleBottom)) * bezierDiff;
		float endAngleBottomY = endY + PApplet.sin(PApplet.radians(endAngleBottom)) * bezierDiff;
		// draw petal shape with points and control points
		if (color == -1) {
			main.noFill();
			main.stroke(255);
		} else {
			main.fill(color, color, color);
		}

		main.beginShape();
		main.vertex(startX, startY);
		main.bezierVertex(startAngleTopX, startAngleTopY, endAngleTopX, endAngleTopY, endX, endY);
		main.bezierVertex(endAngleBottomX, endAngleBottomY, startAngleBottomX, startAngleBottomY, startX, startY);
		main.endShape();
	}

	@Override
	public void update() {

	}

}
