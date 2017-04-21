package challenges.binayaka.fractal;

import challenges.binayaka.common.Drawable;
import processing.core.PApplet;
import processing.core.PConstants;

public class Orbit implements Drawable, FractalConstants {
	float x;
	float y;
	float r;
	int n;
	Orbit parent;
	Orbit child;
	float speed;
	float angle;
	final PApplet main;
	final int resolve;

	public Orbit(PApplet core, float x_, float y_, float r_, int n_, int res_) {
		this(core, x_, y_, r_, n_, null, res_);
	}

	public Orbit(PApplet core, float x_, float y_, float r_, int n_, Orbit p, int res_) {
		resolve = res_;
		main = core;
		x = x_;
		y = y_;
		r = r_;
		n = n_;
		speed = (PApplet.radians(PApplet.pow(k, n - 1))) / res_;
		parent = p;
		child = null;
		angle = -PConstants.PI / 2;
	}

	public Orbit addChild() {
		float newr = (float) (r / 3.0);
		float newx = x + r + newr;
		float newy = y;
		child = new Orbit(main, newx, newy, newr, n + 1, this, resolve);
		return child;
	}

	@Override
	public void update() {
		if (parent != null) {
			angle += speed;
			float rsum = r + parent.r;
			x = parent.x + rsum * PApplet.cos(angle);
			y = parent.y + rsum * PApplet.sin(angle);
		}
		// ellipse(x2, y2, r2*2, r2*2);
	}

	@Override
	public void show() {
		main.stroke(255, 100);
		main.strokeWeight(1);
		main.noFill();
		if (parent != null) {
			main.line(parent.x, parent.y, x, y);
		}
		main.ellipse(x, y, r * 2, r * 2);
	}
}
