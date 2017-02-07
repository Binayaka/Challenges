package challenges.binayaka.sound;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * It seems that Processing sound library is broken for Windows. Changing to
 * Minim library
 * 
 * @author Binayaka
 *
 */
public class BeatAnalyzer extends PApplet {

	Minim minim;
	AudioPlayer player;

	int Y_AXIS = 1;
	int X_AXIS = 2;
	int b1, b2, c1, c2;
	private BeatDetect beat;
	float bg = 0;

	@Override
	public void settings() {
		size(800, 600);
	}

	@Override
	public void setup() {
		minim = new Minim(this);
		player = minim.loadFile("music.mp3");
		// Define colors
		b1 = color(255);
		b2 = color(0);
		c1 = color(204, 102, 0);
		c2 = color(0, 102, 153);
		beat = new BeatDetect();
		player.play();
	}

	@Override
	public void draw() {
		// circleVisualization();
		beat.detect(player.mix);
		if (beat.isOnset() == true) {
			bg = 255;
		}
		background(bg);
		bg *= 0.95;
		stroke(255);
		for (int i = 0; i < player.bufferSize() - 1; i++) {
			float left1 = 50 + player.left.get(i) * 50;
			float left2 = 50 + player.left.get(i + 1) * 50;
			float right1 = 60 + player.right.get(i) * 50;
			float right2 = 60 + player.right.get(i + 1) * 50;
			line(i, left1, i + 1, left2);
			line(i, right1, i + 1, right2);
		}
	}

	public void circleVisualization() {
		PImage p = copy();
		tint(200, 0, 0, 100);
		image(p, -2, -2, width, height);
		stroke(255);

		strokeWeight(2);
		translate(width / 2, height / 2);
		float a = 0;
		float angle = (2 * PI) / 200;
		int step = player.bufferSize() / 200;
		for (int i = 0; i < player.bufferSize() - step; i += step) {
			float x = 100 + cos(a) * (40 * player.mix.get(i) + 60);
			float y = 100 + sin(a) * (40 * player.mix.get(i) + 60);
			float x2 = 100 + cos(a + angle) * (40 * player.mix.get(i + step) + 60);
			float y2 = 100 + sin(a + angle) * (40 * player.mix.get(i + step) + 60);
			line(x, y, x2, y2);
			a += angle;
		}
	}

	@Override
	public void stop() {
		player.close();
		minim.stop();
		super.stop();
	}

	public void setGradient(int x, int y, float w, float h, int c1, int c2, int axis) {
		if (axis == Y_AXIS) { // Top to bottom gradient
			for (int i = y; i <= y + h; i += 20) {
				float inter = map(i, y, y + h, 0, 2);
				int c = lerpColor(c1, c2, inter);
				fill(c);
				rect(x, i * 2, w, 10);
			}
		} else if (axis == X_AXIS) { // left to right
			for (int i = x; i <= x + h; i += 20) {
				float inter = map(i, x, x + h, 0, 2);
				int c = lerpColor(c1, c2, inter);
				fill(c);
				System.out.println("Point is " + i * 2 + "," + y);
				rect(i, y, w, 10);
			}
		}
	}
}
