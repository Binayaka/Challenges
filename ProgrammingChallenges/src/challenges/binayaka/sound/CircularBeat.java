package challenges.binayaka.sound;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;

public class CircularBeat extends PApplet {

	Minim minim;
	AudioPlayer player;

	int Y_AXIS = 1;
	int X_AXIS = 2;
	private BeatDetect beat;
	float bg = 0;
	float radius = 150;
	float extra = 0;

	@Override
	public void settings() {
		size(800, 600);
	}

	@Override
	public void setup() {

		minim = new Minim(this);
		player = minim.loadFile("music.mp3");
		beat = new BeatDetect();
		player.play();

	}

	@Override
	public void draw() {
		beat.detect(player.mix);
		background(0);
		translate(width / 2, height / 2);
		noFill();
		beginShape();
		stroke(255);
		if (beat.isOnset()) {
			extra = 30;
		}
		extra *= 0.95f;
		for (float i = 0; i < 360; i += 0.01f) {
			float x = (radius+extra) * sin(radians(i));
			float y = (radius+extra) * cos(radians(i));
			vertex(x, y);
		}
		endShape(CLOSE);
	}

}
