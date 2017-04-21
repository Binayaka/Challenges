package challenges.binayaka.trees.fractalTrees;

import challenges.binayaka.common.Drawable;
import processing.core.PApplet;
import processing.core.PVector;

public class Branch implements Drawable {
	PApplet main;
	PVector begin, end;
	boolean finished = false;
	int sign = 1;
	float biggerVal = 0;
	float smallerVal = 0;
	float startX;
	float startY;

	public Branch(PApplet _main, PVector _begin, PVector _end) {
		main = _main;
		begin = _begin;
		end = _end;
		this.finished = false;
		if (this.begin.x == this.end.x) {
			biggerVal = this.begin.y > this.end.y ? this.begin.y : this.end.y;
			smallerVal = this.begin.y < this.end.y ? this.begin.y : this.end.y;
			startX = smallerVal;
			startY = smallerVal;
		} else if (this.begin.y == this.end.y) {
			biggerVal = this.begin.x > this.end.x ? this.begin.x : this.end.x;
			smallerVal = this.begin.x < this.end.x ? this.begin.x : this.end.x;
			startX = smallerVal;
			startY = smallerVal;
		} else {
			if (this.begin.x > this.end.x) {
				startX = this.begin.x;
				biggerVal = this.end.x;
			} else {
				startX = this.end.x;
				biggerVal = this.begin.x;
			}
			if (this.begin.y > this.end.y) {
				startY = this.begin.y;
				biggerVal = this.end.y;
			} else {
				startY = this.end.y;
				biggerVal = this.begin.y;
			}
		}

	}

	public void jitter() {
		this.end.x += main.random(-1, 1);
		this.end.y += main.random(-1, 1);
	}

	@Override
	public void show() {
		main.stroke(255);
		main.line(this.begin.x, this.begin.y, this.end.x, this.end.y);
	}

	@Override
	public void update() {
		if (this.startX >= this.biggerVal) {
			return;
		}
		if (this.begin.x == this.end.x) {
			main.ellipse(this.begin.x, this.startX, 10, 10);
		} else if (this.begin.y == this.end.y) {
			main.ellipse(this.startX, this.begin.y, 10, 10);
		} else {
			float m = ((this.end.y - this.begin.y) / (this.end.x - this.begin.x));
			float c = this.begin.y - (this.begin.x * m);
			float y = m * this.startX + c;
			main.ellipse(this.startX, y, 10, 10);
		}
		this.startX += 0.5f;
	}

	public Branch branchA() {
		PVector dir = PVector.sub(this.end, this.begin);
		dir.rotate(PApplet.PI / 6);
		dir.mult(0.67f);
		PVector newEnd = PVector.add(this.end, dir);
		Branch b = new Branch(main, this.end, newEnd);
		return b;
	}

	public Branch branchB() {
		PVector dir = PVector.sub(this.end, this.begin);
		dir.rotate(-PApplet.PI / 4);
		dir.mult(0.67f);
		PVector newEnd = PVector.add(this.end, dir);
		Branch b = new Branch(main, this.end, newEnd);
		return b;
	}
}
