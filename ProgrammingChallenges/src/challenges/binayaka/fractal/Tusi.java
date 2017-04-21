package challenges.binayaka.fractal;

import challenges.binayaka.common.Drawable;
import processing.core.PApplet;
import processing.core.PConstants;

public class Tusi implements Drawable, FractalConstants {

	float x;
	float y;
	float r;
	int n;
	Tusi parent;
	Tusi child;
	float speed;
	float angle;
	final PApplet main;
	final int resolve;

	public Tusi(PApplet core, float x_, float y_, float r_, int n_, int res_) {
		this(core, x_, y_, r_, n_, null, res_);
	}

	public Tusi(PApplet core, float x_, float y_, float r_, int n_, Tusi p, int res_) {
		resolve = res_;
		main = core;
		x = x_;
		y = y_;
		r = r_;
		n = n_;
		// speed = (PApplet.radians(PApplet.pow(k, n - 1))) / res_;
		speed = 0.2f;
		// System.out.println("Speed is " + speed);
		parent = p;
		child = null;
		angle = -PConstants.PI / 2;
	}

	public Tusi addChild() {
		// child = new Tusi(main, x, y, r /PApplet.PI, n + 1, this, resolve);
		// child = new Tusi(main, x, y, r /2 , n + 1, this, resolve);
		// child = new Tusi(main, x, y, 3 * r / 5, n + 1, this, resolve);
		child = new Tusi(main, x, y, r / PApplet.sqrt(2), n + 1, this, resolve);
		return child;
	}

	@Override
	public void update() {
		if (parent != null) {
			angle += speed;
			/*
			 * float rsum = r + parent.r; x = parent.x + rsum *
			 * PApplet.cos(angle); y = parent.y + rsum * PApplet.sin(angle);
			 */
			float a = parent.r;
			float b = r;
			float n = a / b;
			x = (a * ((n - 1) * PApplet.cos(angle) - PApplet.cos((n - 1) * angle)) / n);
			y = (a * ((n - 1) * PApplet.sin(angle) + PApplet.sin((n - 1) * angle)) / n);
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
