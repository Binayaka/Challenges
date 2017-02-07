package challenges.binayaka.waves;

import processing.core.PApplet;
import processing.core.PConstants;

class Wave {
	float amp, p;
	float ax, ay, az;
	float x1 = 0;
	final PApplet main;

	public Wave(PApplet core, float amplitude, float pitch, float angX, float angY, float angZ) {
		main = core;
		amp = amplitude;
		p = pitch;
		ax = angX;
		ay = angY;
		az = angZ;
	}

	public void draw() {
		main.pushMatrix();
		main.rotateX(ax);
		main.rotateY(ay);
		main.rotateZ(az);
		for (float i = x1; i < x1 + 50; i += 2 / amp) {
			main.point(i, PApplet.sin(PConstants.TWO_PI * i / p) * amp);
		}
		x1++;
		main.popMatrix();
	}
}