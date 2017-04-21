package challenges.binayaka.matrix;

import challenges.binayaka.common.Drawable;
import processing.core.PApplet;

public class Symbol implements Drawable, MatrixDisplayConstants {

	private final PApplet main;
	private float x, y;
	float speed;
	private String value;

	public Symbol(PApplet core, float x_, float y_, float speed_) {
		main = core;
		x = x_;
		y = y_;
		speed = speed_;
		value = "";
	}

	@Override
	public void show() {
		main.text(value, x, y);
	}

	@Override
	public void update() {
		int val = PApplet.round(main.random(2, 20));
		if (main.frameCount % val == 0) {
			setToRandomValue();
		}
		if (y >= main.height) {
			y = 0;
		} else {
			y += speed;
		}
	}

	private void setToRandomValue() {
		/*
		 * int val = 0x30A0 + PApplet.round(main.random(0, 96)); value =
		 * Utils.fromCharCode(val);
		 */
		int val = PApplet.round(main.random(65, 92));
		value = Character.toString((char) val);
	}

}
